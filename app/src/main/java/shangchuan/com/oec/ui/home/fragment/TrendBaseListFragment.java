package shangchuan.com.oec.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.model.bean.TrendsListBean;
import shangchuan.com.oec.present.TrendsListPresent;
import shangchuan.com.oec.present.contact.TrendsListContract;
import shangchuan.com.oec.ui.home.adapter.TrendsListAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

/**
 * Created by sg280 on 2017/4/26.
 */

public abstract class TrendBaseListFragment extends BaseFragment<TrendsListPresent> implements TrendsListContract.View {
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    protected  String jobType="";
    private TrendsListAdapter adapter;
    private boolean isLoadingMore;

    @Override
    public void loadData() {
        if(!isVisible||!isPrepared){
            return;
        }
        LoadingView.showProgress(mActivity);
        mPresent.getTrendList(jobType,"");
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
                        mPresent.getMoreTrend(jobType,"");
                    }
                }

            }
        });

    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_dayoff_notify;
    }

    @Override
    protected void initInject() {
          getFragmentComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
           isLoadingMore=false;
           LoadingView.dismissProgress();
        ToastUtil.shortShow(msg);
    }

    @Override
    public void showContent(List<TrendsListBean> bean) {
        LoadingView.dismissProgress();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerDecoration(mActivity));
        adapter=new TrendsListAdapter(mActivity,bean);
         mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showMoreContent(List<TrendsListBean> bean, int start, int end) {
        isLoadingMore=false;
        LoadingView.dismissProgress();
        adapter.updateData(bean);
        adapter.notifyItemRangeInserted(start,end);


    }
}
