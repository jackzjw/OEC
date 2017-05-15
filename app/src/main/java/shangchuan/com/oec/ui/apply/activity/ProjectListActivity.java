package shangchuan.com.oec.ui.apply.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.ProjectListBean;
import shangchuan.com.oec.present.ProjectListPresent;
import shangchuan.com.oec.present.contact.ProjectListContract;
import shangchuan.com.oec.ui.apply.adapter.ProjectListAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

public class ProjectListActivity extends BaseActivity<ProjectListPresent> implements ProjectListContract.View {

    @BindView(R.id.rel_mytask)
    RelativeLayout mRelMyTask;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    private ProjectListAdapter adapter ;

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_project_list;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("项目");
        initToolBar(mToolbar);
       mRelMyTask.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(ProjectListActivity.this,MyTaskActivity.class));
           }
       });
        LoadingView.showProgress(this);
        mPresent.getProjectList(2);
    }

    @Override
    protected void initInject() {
           getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.shortShow(msg);
    }

    @Override
    public void showContent(List<ProjectListBean> bean) {
        LoadingView.dismissProgress();
         mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
         mRecyclerView.addItemDecoration(new DividerDecoration(this));
         adapter=new ProjectListAdapter(this,bean);
        mRecyclerView.setAdapter(adapter);
    }
}
