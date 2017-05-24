package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import rx.Subscription;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.SimpleActivity;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.ProjectDocumentBean;
import shangchuan.com.oec.model.bean.ResultBean;
import shangchuan.com.oec.ui.apply.adapter.ProjectDocAdapter;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.SaveToken;

public class ProjectDocumentActivity extends SimpleActivity {

    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    public static Intent getInstance(Context context,String id){
        Intent intent=new Intent(context,ProjectDocumentActivity.class);
        intent.putExtra("id",id);
        return intent;
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_project_document;
    }

    @Override
    protected void initEventAndData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("项目文档");
        initToolBar(mToolbar);
           String projectid=getIntent().getStringExtra("id");
        LoadingView.showProgress(this);
        Subscription subscription=getActivityComponent().getHttpHelper().getApiSevice()
                .projectDocumentList(projectid,"0",20, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ResultBean<OaBasicItemBean<ProjectDocumentBean>>>>scheduleRxHelper())
                .compose(RxUtil.<ResultBean<OaBasicItemBean<ProjectDocumentBean>>>handleResult()).subscribe(new CommonSubscriber<ResultBean<OaBasicItemBean<ProjectDocumentBean>>>(this) {
                    @Override
                    public void onNext(ResultBean<OaBasicItemBean<ProjectDocumentBean>> bean) {
                        LoadingView.dismissProgress();
                        if(bean.getResult().getItems().isEmpty()){
                          ToastUtil.shortShow("项目文档为空");
                          return;
                      }
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(ProjectDocumentActivity.this));
                        mRecyclerView.addItemDecoration(new DividerDecoration(ProjectDocumentActivity.this));
                        mRecyclerView.setAdapter(new ProjectDocAdapter(ProjectDocumentActivity.this,bean.getResult().getItems()));
                    }
                });
        add(subscription);
    }

    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.shortShow(msg);
    }
}
