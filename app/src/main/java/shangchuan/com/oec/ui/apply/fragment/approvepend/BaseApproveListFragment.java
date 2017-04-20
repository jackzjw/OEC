package shangchuan.com.oec.ui.apply.fragment.approvepend;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.model.bean.ApproveListBean;
import shangchuan.com.oec.present.ApproveListPresent;
import shangchuan.com.oec.present.contact.ApproveListContract;
import shangchuan.com.oec.ui.apply.adapter.ApproveListAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

/**
 * Created by sg280 on 2017/3/23.
 */

public abstract class BaseApproveListFragment extends BaseFragment<ApproveListPresent> implements ApproveListContract.View {

    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    private ApproveListAdapter adapter;
    protected String mType="加班";
    private boolean isMore;
    @Override
    public void loadData() {
        if(!isPrepared||!isVisible){
            return;
        }
        LoadingView.showProgress(mActivity);
        mPresent.getApproveList(mType);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerDecoration(mActivity));
        adapter=new ApproveListAdapter(mActivity,new ArrayList<ApproveListBean>());
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
                        LoadingView.showProgress(mActivity);
                        mPresent.getMoreContent(mType);
                    }
                }
            }
        });

    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_approvepend_overtime;
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
    public void showContent(List<ApproveListBean> bean) {

        LoadingView.dismissProgress();
        adapter.updateData(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<ApproveListBean> bean, int start, int end) {
        isMore=false;
        LoadingView.dismissProgress();
        adapter.updateData(bean);
        adapter.notifyItemRangeInserted(start,end);
    }
}
