package shangchuan.com.oec.ui.team.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.component.RxBus;
import shangchuan.com.oec.model.bean.UserInfoBean;
import shangchuan.com.oec.model.event.KeyWordEvent;
import shangchuan.com.oec.present.UserListPresent;
import shangchuan.com.oec.present.contact.UserListContract;
import shangchuan.com.oec.ui.team.adapter.SortAdapter;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.UserInfoPopupWindow;

/**
 * Created by sg280 on 2017/5/12.
 */

public class SearchUserFragment extends BaseFragment<UserListPresent> implements UserListContract.View {
  @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.view_stub)
    ViewStub mViewStub;
    private List<UserInfoBean> mInfoBeanList=new ArrayList<UserInfoBean>();
    private SortAdapter adapter;
    private RelativeLayout mRelEmpty;
    private boolean isFirst;
    private View vs;
    private String keyWord;
    private Subscription subscription;

    public static SearchUserFragment getInstance(String keyWord){
        SearchUserFragment fragment=new SearchUserFragment();
        Bundle bundle =new Bundle();
        bundle.putString("keyword",keyWord);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void loadData() {
        if(!isPrepared||!isVisible){
            return;
        }

        keyWord=getArguments().getString("keyword");
        LogUtil.i("关键字="+keyWord);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter=new SortAdapter(mActivity,mInfoBeanList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerDecoration(mActivity));
        adapter.setOnItemClickListener(new SortAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                UserInfoPopupWindow popupWindow=new UserInfoPopupWindow(mActivity);
                popupWindow.setData(mInfoBeanList.get(position));
                popupWindow.showAtLocation(mRecyclerView, Gravity.CENTER,0,0);
            }
        });
       LoadingView.showProgress(mActivity);
       mPresent.getUserList(keyWord);
       registerEvent();

    }
    private void registerEvent() {
        subscription= RxBus.getDefault().toDefaultObservable(KeyWordEvent.class, new Action1<KeyWordEvent>() {
            @Override
            public void call(KeyWordEvent event) {
                keyWord=event.getKeyword();
                LogUtil.i(event.getKeyword());
            }
        });

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
    public void showContent(List<UserInfoBean> bean) {
        mInfoBeanList=bean;
        LogUtil.i(bean.toString());
        LoadingView.dismissProgress();
        if(bean.isEmpty()) {
            try {
                vs = mViewStub.inflate();
            } catch (Exception e) {
                vs.setVisibility(View.VISIBLE);
            }
            return;
        }
        if(vs!=null){
            vs.setVisibility(View.GONE);
        }
        adapter.updateData(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<UserInfoBean> bean, int start, int end) {

    }

    @Override
    public void showError(String msg) {
       LoadingView.dismissProgress();
        ToastUtil.shortShow(msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
