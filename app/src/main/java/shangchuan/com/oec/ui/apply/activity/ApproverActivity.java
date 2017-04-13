package shangchuan.com.oec.ui.apply.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.OrgListBean;
import shangchuan.com.oec.model.bean.SelectOwnerBean;
import shangchuan.com.oec.present.ApproverPresent;
import shangchuan.com.oec.present.contact.ApproverContract;
import shangchuan.com.oec.ui.apply.adapter.SelectApproveAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

public class ApproverActivity extends BaseActivity<ApproverPresent> implements ApproverContract.View {

   @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_right_title)
    TextView toolbarSure;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    private SelectApproveAdapter adapter;


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_approver;
    }

    @Override
    protected void initEventData() {
       toolbarTitle.setText("选择审批人");
        toolbarSure.setText("确定");
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        LoadingView.showProgress(this);
        mPresent.getOrgList();
         toolbarSure.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 HashMap<Integer,String> map=adapter.getSelectedOwner();
                 ArrayList<SelectOwnerBean> ownerBeanList=new ArrayList<SelectOwnerBean>();
           for(Map.Entry<Integer,String> entry:map.entrySet()){
               SelectOwnerBean bean=new SelectOwnerBean();
               bean.setId(entry.getKey());
               bean.setOwnerName(entry.getValue());
               ownerBeanList.add(bean);
           }
                 Intent intent=new Intent();
                 intent.putExtra("ownerlist",ownerBeanList);
                 setResult(RESULT_OK,intent);
                 finish();

             }
         });
    }

    @Override
    protected void initInject() {
      getActivityComponent().inject(this);
    }

    @Override
    public void showContent(List<OrgListBean> bean) {
        LoadingView.dismissProgress();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new SelectApproveAdapter(this,bean);
        mRecyclerView.addItemDecoration(new DividerDecoration(this));
        mRecyclerView.setAdapter(adapter);


    }

    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.show(msg);

    }
}
