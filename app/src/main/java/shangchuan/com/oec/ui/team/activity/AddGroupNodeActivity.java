package shangchuan.com.oec.ui.team.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import rx.Subscription;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.base.SimpleActivity;
import shangchuan.com.oec.model.bean.GroupBasicBean;
import shangchuan.com.oec.model.bean.GroupListBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.ui.team.adapter.AddGroupNodeAdapter;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.SaveToken;

public class AddGroupNodeActivity extends SimpleActivity implements BaseView{

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_right_title)
    TextView toolbarSure;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
   private GroupListBean selectNode;
    @Override
    protected int getLayout() {
        return R.layout.activity_add_group_node;
    }

    @Override
    protected void initEventAndData() {
        toolbarTitle.setText("选择节点");
        toolbarSure.setText("确定");
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        LoadingView.showProgress(this);
        Subscription subscription = getActivityComponent().getHttpHelper().getApiSevice().getOrGroupList(SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<GroupBasicBean>>scheduleRxHelper())
                .compose(RxUtil.<GroupBasicBean>handleResult()).subscribe(new CommonSubscriber<GroupBasicBean>(this) {
                    @Override
                    public void onNext(GroupBasicBean bean) {
                         LoadingView.dismissProgress();
                        mRecyclerView.addItemDecoration(new DividerDecoration(AddGroupNodeActivity.this));
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(AddGroupNodeActivity.this));
                        AddGroupNodeAdapter adapter = new AddGroupNodeAdapter(AddGroupNodeActivity.this, bean.getGroup_list());
                        mRecyclerView.setAdapter(adapter);
                        if(!bean.getGroup_list().isEmpty()){
                            selectNode=bean.getGroup_list().get(0);
                        }
                        adapter.setOwnerListener(new AddGroupNodeAdapter.OnSelectOwnerListener() {
                            @Override
                            public void selectedOwnerClick(GroupListBean bean) {
                                 selectNode=bean;
                            }
                        });

                    }
                });
        add(subscription);
        toolbarSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent();
                intent.putExtra("selectNode",selectNode);
                setResult(RESULT_OK,intent);
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
