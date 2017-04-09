package shangchuan.com.oec.ui.user.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.CharactersTokenBean;
import shangchuan.com.oec.model.bean.MySelfInfo;
import shangchuan.com.oec.model.bean.OrganizeInfoBean;
import shangchuan.com.oec.present.OrganizationListPresent;
import shangchuan.com.oec.present.contact.OrganizationListContract;
import shangchuan.com.oec.ui.user.adapter.OrganizationListAdapter;
import shangchuan.com.oec.util.ToastUtil;

public class SwitchOrganizationActivity extends BaseActivity<OrganizationListPresent> implements OrganizationListContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_img)
    ImageView toolbarSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.sure)
    Button btnSure;
    private LinearLayoutManager layoutManager;
    private List<OrganizeInfoBean> mList;
    private OrganizationListAdapter adapter;
    private ProgressDialog progressDialog;
    private int mpos=0;
    private int tenantId;
    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_switch_organization;
    }

    @Override
    protected void initEventData() {
        toolbarTitle.setText("机构切换");
        toolbarSearch.setImageResource(R.drawable.home_icon_news_search);
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        mList=new ArrayList<>();
        layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter=new OrganizationListAdapter(mList,this);
        mRecyclerView.setAdapter(adapter);
        //获取机构列表
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("加载中");
        progressDialog.show();
        mPresent.getOzList();
        adapter.setItemOnclickListener(new OrganizationListAdapter.ItemOnclickListener() {
            @Override
            public void OnItemclickListener(int position) {
                //显示打钩的icon，待优化，应该将这些代码放在adapter中
                if(position!=mpos) {
                    View selectView = layoutManager.getChildAt(position);
                    ImageView imgSTag = (ImageView) selectView.findViewById(R.id.select_tag);
                    imgSTag.setVisibility(View.VISIBLE);
                    View mView = layoutManager.getChildAt(mpos);
                    ImageView imgTag = (ImageView) mView.findViewById(R.id.select_tag);
                    imgTag.setVisibility(View.INVISIBLE);
                    mpos=position;
                }
                tenantId=mList.get(position).getId();
            }
        });
    }

    @Override
    protected void initInject() {
      getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
       progressDialog.dismiss();
        ToastUtil.show(msg);
    }
    @OnClick(R.id.sure)
    void beSure(View v){
        progressDialog.show();
        mPresent.enterTenant(MySelfInfo.getInstance().getUserId(),tenantId);

    }

    @Override
    public void showTenantsResult(List<OrganizeInfoBean> beansList) {
        progressDialog.dismiss();
        if(beansList==null) return;
        mList.addAll(beansList);
        adapter.notifyDataSetChanged();
        //遍历找出用户之前选择的机构ID
        for(int i=0;i<beansList.size();i++){
            if(beansList.get(i).getId()== MySelfInfo.getInstance().getTenantId()){
                tenantId=beansList.get(i).getId();
                mpos=i;
            }
        }
    }

    @Override
    public void showCharacterInfo(CharactersTokenBean bean) {
        progressDialog.dismiss();
        Intent intent=new Intent();
        intent.putExtra(Constants.USER_NICK_NAME,bean.getUserinfo().getLoginUserNickName());
        intent.putExtra(Constants.USER_AVATER,bean.getUserinfo().getLoginAvatar());
        intent.putExtra(Constants.CURRENT_TENANT_NAME,bean.getUserinfo().getLoginTenantName());
        setResult(RESULT_OK,intent);
        finish();

    }
}
