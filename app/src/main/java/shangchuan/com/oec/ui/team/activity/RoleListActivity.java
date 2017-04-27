package shangchuan.com.oec.ui.team.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.base.SimpleActivity;
import shangchuan.com.oec.component.RxBus;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.RoleListBean;
import shangchuan.com.oec.model.event.RoleSelectEvent;
import shangchuan.com.oec.ui.team.adapter.RoleListAdapter;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.SaveToken;

public class RoleListActivity extends SimpleActivity implements BaseView {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_right_title)
    TextView toolbarSure;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    private RoleListAdapter adapter;
    private CompositeSubscription mSubscription;


    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.shortShow(msg);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_role_list;
    }

    @Override
    protected void initEventAndData() {
        toolbarTitle.setText("选择角色");
        toolbarSure.setText("确定");
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        LoadingView.showProgress(this);
        Subscription subscription = getActivityComponent().getHttpHelper().getApiSevice()
                .getRoleList(SaveToken.mToken).compose(RxUtil.<HttpDataResult<OaBasicItemBean<RoleListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<RoleListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<RoleListBean>>(this) {
                    @Override
                    public void onNext(OaBasicItemBean<RoleListBean> bean) {
                        LoadingView.dismissProgress();
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(RoleListActivity.this));
                        mRecyclerView.addItemDecoration(new DividerDecoration(RoleListActivity.this));
                        adapter = new RoleListAdapter(RoleListActivity.this, bean.getItems());
                        mRecyclerView.setAdapter(adapter);
                    }
                });
              add(subscription);
        toolbarSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i("确定了");
                //将选择角色传回给前一个页面
                RxBus.getDefault().post(new RoleSelectEvent(adapter.getSelectedRoles()));
                finish();
            }
        });
    }
}
