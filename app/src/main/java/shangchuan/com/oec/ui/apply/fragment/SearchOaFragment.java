package shangchuan.com.oec.ui.apply.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import shangchuan.com.oec.model.bean.OaItemBean;
import shangchuan.com.oec.model.event.KeyWordEvent;
import shangchuan.com.oec.present.OaListPresent;
import shangchuan.com.oec.present.contact.OaListContract;
import shangchuan.com.oec.ui.apply.adapter.OaListAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

/**
 * Created by sg280 on 2017/5/12.
 */

public class SearchOaFragment extends BaseFragment<OaListPresent> implements OaListContract.View{
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.view_stub)
    ViewStub mViewStub;
    private OaListAdapter adapter;
    private boolean isFirst;
    private RelativeLayout mRelEmpty;
    private View vs;
    private String keyword;
    private Subscription subscription;

    public static SearchOaFragment getInstance(String keyWord){
        SearchOaFragment fragment=new SearchOaFragment();
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerDecoration(mActivity));
        adapter=new OaListAdapter(mActivity,new ArrayList<OaItemBean>());
        mRecyclerView.setAdapter(adapter);
       LoadingView.showProgress(mActivity);
         keyword=getArguments().getString("keyword");
       mPresent.searchOaList(keyword);
        mPresent.registerEvent();
        //关键字传输
       registerEvent();
    }
    private void registerEvent() {
        subscription= RxBus.getDefault().toDefaultObservable(KeyWordEvent.class, new Action1<KeyWordEvent>() {
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
    public void showContent(List<OaItemBean> bean) {

    }

    @Override
    public void showMoreContent(List<OaItemBean> bean, int start, int end) {

    }

    @Override
    public void refreshStatus(int position) {
        adapter.notifyItemChanged(position);
    }

    @Override
    public void searchResult(List<OaItemBean> bean) {
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
    public void showError(String msg) {
       LoadingView.dismissProgress();
        ToastUtil.shortShow(msg);
    }
    public void search(String key){
        LoadingView.showProgress(mActivity);
        mPresent.searchOaList(key);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
