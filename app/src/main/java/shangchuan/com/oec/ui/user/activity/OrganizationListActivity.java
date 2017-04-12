package shangchuan.com.oec.ui.user.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.CharactersTokenBean;
import shangchuan.com.oec.model.bean.OrganizeInfoBean;
import shangchuan.com.oec.present.OrganizationListPresent;
import shangchuan.com.oec.present.contact.OrganizationListContract;
import shangchuan.com.oec.ui.MainActivity;
import shangchuan.com.oec.ui.user.adapter.OrganizationListAdapter;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.SharePreferenceUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;


public class OrganizationListActivity extends BaseActivity<OrganizationListPresent> implements OrganizationListContract.View {
  @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
  @BindView(R.id.sure)
  Button btnSure;
  private LinearLayoutManager layoutManager;
   private List<OrganizeInfoBean> mList;
  private OrganizationListAdapter adapter;

  private int mpos=0;
  private int tenantId;

  @Override
    protected int getResourcesLayout() {
        return R.layout.activity_organization_list;
    }

    @Override
    protected void initEventData(){
        toolbarTitle.setText("选择机构");
      mList=new ArrayList<>();
      layoutManager=new LinearLayoutManager(this);
      mRecyclerView.setLayoutManager(layoutManager);
     mRecyclerView.addItemDecoration(new DividerDecoration(this));
      adapter=new OrganizationListAdapter(mList,this);
      mRecyclerView.setAdapter(adapter);
      //获取机构列表
      LoadingView.showProgress(this);
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
              mpos = position;
            }else {
              View selectView = layoutManager.getChildAt(position);
              ImageView imgSTag = (ImageView) selectView.findViewById(R.id.select_tag);
              imgSTag.setVisibility(View.VISIBLE);
            }
        tenantId=mList.get(position).getId();
        }
      });

    }
  //进入机构
      @OnClick(R.id.sure)
      void enterTenant(View view ){
        if(tenantId==0){
          ToastUtil.shortShow("请选择机构");
          return;
        }
        //将机构ID存入sharedpreference;
        SharePreferenceUtil.setTenantId(tenantId);
        //发进入机构请求
        LoadingView.showProgress(this);
        mPresent.enterTenant(SharePreferenceUtil.getUserId(),tenantId);
        LogUtil.i("tenantId="+tenantId);
        btnSure.setEnabled(false);


      }

    @Override
    protected void initInject() {
      getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
     LoadingView.dismissProgress();
      ToastUtil.show(msg);
      btnSure.setEnabled(true);
    }

  @Override
  public void showTenantsResult(List<OrganizeInfoBean> beansList) {
    LoadingView.dismissProgress();
    if(beansList==null) return;
    mList.addAll(beansList);
    adapter.notifyDataSetChanged();
  }


  @Override
  public void showCharacterInfo(CharactersTokenBean bean) {
        LoadingView.dismissProgress();
        btnSure.setEnabled(true);
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }
}
