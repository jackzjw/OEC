package shangchuan.com.oec.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import shangchuan.com.oec.app.App;
import shangchuan.com.oec.di.component.DaggerActivityComponent;
import shangchuan.com.oec.di.component.FragmentComponent;
import shangchuan.com.oec.di.module.ActivityModule;
import shangchuan.com.oec.util.LogUtil;

/**
 * Created by sg280 on 2017/3/3.
 */

public abstract class BaseFragment<T extends RxPresent> extends Fragment implements BaseView {
    protected boolean isVisible;
    protected boolean isPrepared;
    protected View mView;
    @Inject
    protected T mPresent;
    protected AppCompatActivity mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
           this.mActivity=(AppCompatActivity)context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(getResourcesLayout(),null);
        initInject();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,mView);
        if(mPresent!=null){
            mPresent.attachView(this);
        }
        isPrepared=true;
        loadData();


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //因为这个方法是先于onCreateView方法的，在viewpage滑动切换页面时，如果之前有isPrepared=true
        //就会加载数据，所以会重复加载，因此要先置于false状态;
        if(isVisibleToUser){
            isVisible=true;
            loadData();
        }else {
            isVisible=false;
            isPrepared=false;
        }
    }
protected FragmentComponent getFragmentComponent(){
    return DaggerActivityComponent.builder().appComponent(App.getAppComponent())
            .activityModule(new ActivityModule(mActivity)).build().fragmentComponent();
}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresent!=null){
            mPresent.deatchView();
        }
    }


    public abstract void loadData();
    public abstract int getResourcesLayout();
    protected abstract void initInject();

}
