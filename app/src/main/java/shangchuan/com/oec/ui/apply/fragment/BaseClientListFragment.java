package shangchuan.com.oec.ui.apply.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.model.bean.CustomerListBean;
import shangchuan.com.oec.present.ClientListPresent;
import shangchuan.com.oec.present.contact.ClientListContract;
import shangchuan.com.oec.ui.apply.adapter.ClientListAdapter;
import shangchuan.com.oec.util.DensityUtil;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

/**
 * Created by sg280 on 2017/4/10.
 */

public  class BaseClientListFragment extends BaseFragment<ClientListPresent> implements ClientListContract.View  {


  protected  String type;
  @BindView(R.id.recycleview)
  RecyclerView mRecyclerView;
  private ClientListAdapter adapter;
  private boolean isLoadingMore=false;
  @Override
    public void loadData() {
    //懒加载
    if(!isVisible||!isPrepared){
      return;
    }
      mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
      adapter=new ClientListAdapter(mActivity,new ArrayList<CustomerListBean>());
      mRecyclerView.setAdapter(adapter);
    mRecyclerView.addItemDecoration(new DividerDecoration(ContextCompat.getColor(mActivity,R.color.theme_divide_color), DensityUtil.dp2px(mActivity,1f)));
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
            mPresent.getMoreData(type);
          }
        }

      }
    });
    LogUtil.i("present="+mPresent);
    mPresent.getData(type);

  }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_company_client_list;
    }

    @Override
    protected void initInject() {
              getFragmentComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
      ToastUtil.show(msg);
    }

    @Override
    public void showContent(List<CustomerListBean> bean) {
    //   LogUtil.i("数据="+bean.toString());
         adapter.updateData(bean);
      adapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<CustomerListBean> bean, int start, int end) {
      LoadingView.dismissProgress();
      isLoadingMore=false;
      adapter.updateData(bean);
      adapter.notifyItemRangeInserted(start,end);
    }
}
