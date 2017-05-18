package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.SimpleActivity;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.ResultBean;
import shangchuan.com.oec.model.bean.UserInfoBean;
import shangchuan.com.oec.present.contact.OnItemClickListener;
import shangchuan.com.oec.ui.apply.adapter.ProjectMemAdapter;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.SaveToken;

public class ProjectMemberListActivity extends SimpleActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_right_title)
    TextView toolbarSure;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    private int mType;
    private String projectId;
    private ProjectMemAdapter adapter;

    public static Intent getInstance(Context context,String id,int type){
        //type=0;展示成员列表，type=1
        Intent intent=new Intent(context,ProjectMemberListActivity.class);
        intent.putExtra("projectid",id);
        intent.putExtra("type",type);
        return intent;
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_approver;
    }


    @Override
    protected void initEventAndData() {
        toolbarTitle.setText("成员列表");
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        mType=getIntent().getIntExtra("type",-1);
        projectId=getIntent().getStringExtra("projectid");
        LoadingView.showProgress(this);
        Subscription subscription=getActivityComponent().getHttpHelper().getApiSevice().projectMemberList(projectId, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ResultBean<List<UserInfoBean>>>>scheduleRxHelper())
                .compose(RxUtil.<ResultBean<List<UserInfoBean>>>handleResult()).subscribe(new CommonSubscriber<ResultBean<List<UserInfoBean>>>(this) {
            @Override
            public void onNext(final ResultBean<List<UserInfoBean>> bean) {
                LoadingView.dismissProgress();
                mRecyclerView.setLayoutManager( new LinearLayoutManager(ProjectMemberListActivity.this));
                mRecyclerView.addItemDecoration(new DividerDecoration(ProjectMemberListActivity.this));
                adapter=new ProjectMemAdapter(ProjectMemberListActivity.this,bean.getResult(),mType);
                mRecyclerView.setAdapter(adapter);
                if(mType==1){
                    adapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            Intent intent=new Intent();
                            intent.putExtra("userid",bean.getResult().get(position).getUserId()+"");
                            intent.putExtra("username",bean.getResult().get(position).getUserNickName());
                             setResult(RESULT_OK,intent);
                            finish();
                        }
                    });
                }
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
