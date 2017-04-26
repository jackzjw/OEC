package shangchuan.com.oec.ui.home.fragment;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.model.bean.NewsClassifyBean;
import shangchuan.com.oec.model.bean.NewsListBean;
import shangchuan.com.oec.present.NewsListPresent;
import shangchuan.com.oec.present.contact.NewListContract;
import shangchuan.com.oec.ui.home.adapter.NewsListAdapter;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

/**
 * Created by sg280 on 2017/3/10.
 */

public abstract class BannersBaseFragment extends BaseFragment<NewsListPresent> implements NewListContract.View {
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    protected int readStatus=2;//全部
    private NewsListAdapter adapter;
    private boolean isLoadingMore;
    private int classId;
    @Override
    public void loadData() {


        LoadingView.showProgress(mActivity);
         mPresent.getNewsClassify();
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
                        mPresent.getMoreNews(classId,readStatus);
                    }
                }

            }
        });

    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_total_banners;
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
    public void showContent(List<NewsListBean> bean) {
           LoadingView.dismissProgress();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerDecoration(mActivity));
        adapter=new NewsListAdapter(mActivity,bean);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void showMoreContent(List<NewsListBean> bean, int start, int end) {
              LoadingView.dismissProgress();
              isLoadingMore=false;
              adapter.updateData(bean);
              adapter.notifyItemRangeInserted(start,end);
    }

    @Override
    public void updateReadStatus(int position) {
        adapter.notifyItemChanged(position);
    }

    @Override
    public void showNewsClassify(NewsClassifyBean bean) {
       if( bean.getList().isEmpty()) return;
              LoadingView.dismissProgress();
        for(NewsClassifyBean item:bean.getList()){
            TabLayout.Tab tab = mTabLayout.newTab().setText(item.getClassName());
            tab.setTag(item.getId());
            mTabLayout.addTab(tab);
        }
        mTabLayout.getTabAt(0).select();
         classId=bean.getList().get(0).getId();
        LoadingView.showProgress(mActivity);
        mPresent.getNewsList(classId,readStatus);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtil.i(""+classId);
                LoadingView.showProgress(mActivity);
                classId=(int)tab.getTag();
                mPresent.getNewsList(classId,readStatus);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
