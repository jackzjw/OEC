package shangchuan.com.oec.ui.team.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.base.SimpleActivity;
import shangchuan.com.oec.component.RxBus;
import shangchuan.com.oec.model.bean.GroupBasicBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.ui.team.adapter.GroupFirstAdapter;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.SaveToken;

public class GroupListActivity extends SimpleActivity implements BaseView{
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_right_title)
    TextView toolbarSure;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    private GroupFirstAdapter adapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_group_list;
    }

    @Override
    protected void initEventAndData() {
        toolbarTitle.setText("选择部门");
        toolbarSure.setText("确定");
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        LoadingView.showProgress(this);
        getActivityComponent().getHttpHelper().getApiSevice().getOrGroupList(SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<GroupBasicBean>>scheduleRxHelper())
                .compose(RxUtil.<GroupBasicBean>handleResult()).subscribe(new CommonSubscriber<GroupBasicBean>(this) {
            @Override
            public void onNext(GroupBasicBean bean) {
                LoadingView.dismissProgress();
                mRecyclerView.addItemDecoration(new DividerDecoration(GroupListActivity.this));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(GroupListActivity.this));
                adapter=new GroupFirstAdapter(GroupListActivity.this,bean.getGroup_list());
                mRecyclerView.setAdapter(adapter);
            }
        });
        toolbarSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将选择部门传回给前一个页面
                RxBus.getDefault().post(adapter.getSelectDept());
                finish();
            }
        });
    }

    @Override
    public void showError(String msg) {
        ToastUtil.shortShow(msg);
        LoadingView.dismissProgress();
    }
}
