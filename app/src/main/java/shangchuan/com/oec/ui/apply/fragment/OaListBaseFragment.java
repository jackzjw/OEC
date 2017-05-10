package shangchuan.com.oec.ui.apply.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.model.bean.OaItemBean;
import shangchuan.com.oec.present.OaListPresent;
import shangchuan.com.oec.present.contact.OaListContract;
import shangchuan.com.oec.ui.apply.adapter.OaListAdapter;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.TouchSwipeRefreshLayout;

/**
 * Created by sg280 on 2017/3/31.
 */

public class OaListBaseFragment extends BaseFragment<OaListPresent> implements OaListContract.View {
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    TouchSwipeRefreshLayout mRefreshLayout;
    private OaListAdapter adapter;
    private boolean isLoadingMore=false;
    protected String mType="";

    @Override
    public void loadData() {
        //懒加载
        if(!isVisible||!isPrepared){
            return;
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter=new OaListAdapter(mActivity,new ArrayList<OaItemBean>());
        mRecyclerView.setAdapter(adapter);
        LoadingView.showProgress(mActivity);
        mPresent.getApplyType(mType);
       mRecyclerView.addItemDecoration(new DividerDecoration(mActivity));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisiblePosition=((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount=recyclerView.getLayoutManager().getItemCount();
                if(lastVisiblePosition>totalItemCount-2&&dy>0){
                    if(!isLoadingMore){
                        isLoadingMore=true;
                        LoadingView.showProgress(mActivity);
                        mPresent.getMoreContent();
                    }
                }

            }
        });
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresent.getApplyType(mType);
            }
        });
        mPresent.registerEvent();
    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_overtime_list;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.show(msg);
        if(mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);
        }
        isLoadingMore=false;

    }

    @Override
    public void showContent(List<OaItemBean> bean) {
       LoadingView.dismissProgress();
        if(mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);
        }
        adapter.updateData(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<OaItemBean> bean,int start,int end) {
        LoadingView.dismissProgress();
        isLoadingMore=false;
        adapter.updateData(bean);
        adapter.notifyItemRangeInserted(start,end);
    }

    @Override
    public void refreshStatus(int position) {
        LogUtil.i("refresh="+position);
          adapter.notifyItemChanged(position);
    }


}
