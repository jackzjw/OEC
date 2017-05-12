package shangchuan.com.oec.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import shangchuan.com.oec.app.App;
import shangchuan.com.oec.di.component.ActivityComponent;
import shangchuan.com.oec.di.component.DaggerActivityComponent;
import shangchuan.com.oec.di.module.ActivityModule;

/**
 * Created by sg280 on 2017/3/3.
 */

public abstract class BaseActivity<T extends RxPresent> extends AppCompatActivity implements BaseView{
    private static final String TAG = "BaseActivity";
    @Inject
    protected T mPresent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getResourcesLayout());
        ButterKnife.bind(this);
         initInject();
         if(mPresent!=null){
             mPresent.attachView(this);
         }
         initEventData();
    }
    public void initToolBar(Toolbar toolbar) {
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
    protected ActivityComponent  getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent()).activityModule(new ActivityModule(this)).build();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresent!=null) {
            mPresent.deatchView();
        }
    }

    protected abstract int getResourcesLayout();
    protected abstract void initEventData();
    protected abstract void initInject();

}
