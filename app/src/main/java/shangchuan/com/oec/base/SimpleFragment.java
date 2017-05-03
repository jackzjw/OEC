package shangchuan.com.oec.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import shangchuan.com.oec.app.App;
import shangchuan.com.oec.di.component.ActivityComponent;
import shangchuan.com.oec.di.component.DaggerActivityComponent;
import shangchuan.com.oec.di.module.ActivityModule;

/**
 * Created by sg280 on 2017/5/3.
 */

public abstract class SimpleFragment extends Fragment implements BaseView{
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;
    private boolean isInited = false;
    private CompositeSubscription mSubscription;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        if (savedInstanceState == null) {
            if (!isHidden()) {
                isInited = true;
                initEventAndData();
            }
        } else {
            if (!isHidden()) {
                isInited = true;
                initEventAndData();
            }
        }
    }
    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent()).activityModule(new ActivityModule(mActivity)).build();

    }
    protected void add(Subscription subscription){
        if(mSubscription==null){
            mSubscription=new CompositeSubscription();
        }
        mSubscription.add(subscription);
    }
    protected void unSubscribe(){
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isInited && !hidden) {
            isInited = true;
            initEventAndData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
        unSubscribe();
    }

    protected abstract int getLayoutId();
    protected abstract void initEventAndData();


}
