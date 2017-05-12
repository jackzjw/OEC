package shangchuan.com.oec.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import shangchuan.com.oec.app.App;
import shangchuan.com.oec.di.component.ActivityComponent;
import shangchuan.com.oec.di.component.DaggerActivityComponent;
import shangchuan.com.oec.di.module.ActivityModule;

/**
 * Created by codeest on 16/8/11.
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends AppCompatActivity implements BaseView {

    protected Activity mContext;
    protected CompositeSubscription mSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayout());
        ButterKnife.bind(this);
        mContext = this;
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void initToolBar(Toolbar toolbar) {
        if(toolbar==null) return;
        setSupportActionBar(toolbar);
        //设置左上角返回图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置返回图标可点击
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
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
    protected void onPause() {
        super.onPause();
    }
    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent()).activityModule(new ActivityModule(this)).build();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        unSubscribe();
    }

    protected abstract int getLayout();
    protected abstract void initEventAndData();
}
