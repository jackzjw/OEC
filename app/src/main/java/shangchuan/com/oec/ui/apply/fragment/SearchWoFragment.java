package shangchuan.com.oec.ui.apply.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.component.RxBus;
import shangchuan.com.oec.model.bean.WoClassBean;
import shangchuan.com.oec.model.bean.WoListBean;
import shangchuan.com.oec.model.event.KeyWordEvent;
import shangchuan.com.oec.present.WoListPresent;
import shangchuan.com.oec.present.contact.WoListContract;
import shangchuan.com.oec.ui.apply.adapter.WoListAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

/**
 * Created by sg280 on 2017/5/12.
 */

public class SearchWoFragment extends BaseFragment<WoListPresent> implements WoListContract.View {
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.view_stub)
    ViewStub mViewStub;
    private WoListAdapter contentAdapter;
    private RelativeLayout mRelEmpty;
    private boolean isFirst;
    private View vs;
    private String keyword;
    private Subscription subscription;

    public static SearchWoFragment getInstance(String keyWord){
        SearchWoFragment fragment=new SearchWoFragment();
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
        keyword=getArguments().getString("keyword");
        LoadingView.showProgress(mActivity);
        mPresent.registerEvent();
        mPresent.searchWoList(keyword);
        registerEvent();

    }

    private void registerEvent() {
    subscription=RxBus.getDefault().toDefaultObservable(KeyWordEvent.class, new Action1<KeyWordEvent>() {
            @Override
            public void call(KeyWordEvent event) {
                keyword=event.getKeyword();
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
    public void showParentName(List<WoClassBean> bean) {

    }

    @Override
    public void showChildName(List<WoClassBean> Bean) {

    }

    @Override
    public void showContentList(List<WoListBean> bean) {

    }

    @Override
    public void showMoreContent(List<WoListBean> bean, int start, int end) {

    }


    @Override
    public void refreshStatus(int position) {
        contentAdapter.notifyItemChanged(position);
    }

    @Override
    public void searchResult(List<WoListBean> bean) {
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
        contentAdapter=new WoListAdapter(mActivity,bean);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(contentAdapter);
        mRecyclerView.addItemDecoration(new DividerDecoration(mActivity));
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
