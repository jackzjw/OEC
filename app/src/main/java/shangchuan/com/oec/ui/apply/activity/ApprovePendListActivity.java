package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.OaItemBean;
import shangchuan.com.oec.present.ApproveListPresent;
import shangchuan.com.oec.present.contact.ApproveListContract;
import shangchuan.com.oec.ui.apply.adapter.ApproveListAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

public class ApprovePendListActivity extends BaseActivity<ApproveListPresent> implements ApproveListContract.View {
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    private int isAudit=1;
     private String mType;
    private boolean isMore;
    private ApproveListAdapter adapter;

    public static Intent getInstance(Context context,String type){
        Intent intent=new Intent(context,ApprovePendListActivity.class);
        intent.putExtra("type",type);
        return intent;
    }


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_approve_pend_list;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        mType=getIntent().getStringExtra("type");
        mToolbarTitle.setText(mType+"已审批");
        LoadingView.showProgress(this);
        mPresent.getApproveList(mType,isAudit);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerDecoration(this));
        adapter=new ApproveListAdapter(this,new ArrayList<OaItemBean>());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleIndex=((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalCount=recyclerView.getLayoutManager().getItemCount();
                if(dy>0&&lastVisibleIndex>totalCount-2){
                    if(!isMore){
                        isMore=true;
                        LoadingView.showProgress(ApprovePendListActivity.this);
                        mPresent.getMoreContent(mType,isAudit);
                    }
                }
            }
        });
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
        isMore=false;
        LoadingView.dismissProgress();
        ToastUtil.show(msg);
    }

    @Override
    public void showContent(List<OaItemBean> bean) {
        LoadingView.dismissProgress();
        adapter.updateData(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<OaItemBean> bean, int start, int end) {
        isMore=false;
        LoadingView.dismissProgress();
        adapter.updateData(bean);
        adapter.notifyItemRangeInserted(start,end);
    }

    @Override
    public void refreshStatus(int pos, boolean isDel) {

    }
}
