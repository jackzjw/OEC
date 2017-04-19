package shangchuan.com.oec.ui.apply.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.model.bean.WorkReportListBean;
import shangchuan.com.oec.present.WorkReportListPresent;
import shangchuan.com.oec.present.contact.WorkReportContract;
import shangchuan.com.oec.ui.apply.adapter.WorkReporListAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

/**
 * Created by sg280 on 2017/3/14.
 */

public abstract class BaseMyReportListFragment extends BaseFragment<WorkReportListPresent> implements WorkReportContract.View {

  @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    private WorkReporListAdapter adapter;
     protected int mType=1;
    private boolean isMore;

    @Override
    public void loadData() {
        if(!isPrepared||!isVisible) return;
        LoadingView.showProgress(mActivity);
        //type=1,表示我创建的报告
        mPresent.getWrList(mType);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleIndex=((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalCount=recyclerView.getLayoutManager().getItemCount();
                if(dy>0&&lastVisibleIndex>totalCount-2){
                    if(!isMore){
                        isMore=true;
                        LoadingView.showProgress(mActivity);
                        mPresent.getMoreWrList(mType);
                    }
                }
            }
        });


    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_my_create_report_list;
    }

    @Override
    protected void initInject() {
             getFragmentComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
        isMore=false;
        LoadingView.dismissProgress();
        ToastUtil.show(msg);

    }


    @Override
    public void showContent(List<WorkReportListBean> bean) {
        LoadingView.dismissProgress();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter=new WorkReporListAdapter(mActivity,bean,mType);
        mRecyclerView.addItemDecoration(new DividerDecoration(mActivity));
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void showMoreContent(List<WorkReportListBean> bean, int start, int end) {
        isMore=false;
        LoadingView.dismissProgress();
        adapter.updateData(bean);
        adapter.notifyItemRangeInserted(start,end);
    }




}
