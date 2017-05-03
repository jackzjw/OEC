package shangchuan.com.oec.ui.team.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.model.bean.UserInfoBean;
import shangchuan.com.oec.present.UserListPresent;
import shangchuan.com.oec.present.contact.UserListContract;
import shangchuan.com.oec.ui.team.adapter.SortAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.SideBar;
import shangchuan.com.oec.widget.UserInfoPopupWindow;

/**
 * Created by sg280 on 2017/3/8.
 */

public class TeamUserFragment extends BaseFragment<UserListPresent> implements UserListContract.View {

    @BindView(R.id.sideBar)
    SideBar mSideBar;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    private SortAdapter adapter;
    private boolean isMore;
    private List<UserInfoBean> mInfoBeanList=new ArrayList<UserInfoBean>();
    @Override
    public void loadData() {
        if(!isVisible||!isPrepared){
            return;
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter=new SortAdapter(mActivity,mInfoBeanList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerDecoration(mActivity));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                 int lastIndex=((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalCount=recyclerView.getLayoutManager().getItemCount();
                if(lastIndex>totalCount-2&&dy>0){
                    if(!isMore){
                        isMore=true;
                        LoadingView.showProgress(mActivity);
                        mPresent.getMoreUser();
                    }
                }
            }
        });
      mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
          @Override
          public void onTouchingLetterChanged(String s) {
              int pos=adapter.getPositionForSection(s.charAt(0));
              if(pos!=-1){
                  mRecyclerView.smoothScrollToPosition(pos);
              }
          }
      });
        adapter.setOnItemClickListener(new SortAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                UserInfoPopupWindow popupWindow=new UserInfoPopupWindow(mActivity);
                popupWindow.setData(mInfoBeanList.get(position));
                popupWindow.showAtLocation(mRecyclerView, Gravity.CENTER,0,0);
            }
        });
        LoadingView.showProgress(mActivity);
             mPresent.getUserList();
    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_team_user;
    }

    @Override
    protected void initInject() {
          getFragmentComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        isMore=false;
        ToastUtil.show(msg);
    }

    @Override
    public void showContent(List<UserInfoBean> bean) {
        mInfoBeanList=bean;
        LoadingView.dismissProgress();
        adapter.updateData(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<UserInfoBean> bean, int start, int end) {
        mInfoBeanList=bean;
       LoadingView.dismissProgress();
        isMore=false;
        adapter.updateData(bean);
        adapter.notifyItemRangeInserted(start,end);
    }
}
