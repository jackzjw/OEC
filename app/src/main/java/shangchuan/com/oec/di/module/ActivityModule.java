package shangchuan.com.oec.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import shangchuan.com.oec.di.ActivityScope;

/**
 * Created by sg280 on 2017/3/6.
 */
@Module
public class ActivityModule {
    private Activity mActivity;
    public ActivityModule(Activity activity){
        this.mActivity=activity;
    }
    @ActivityScope
    @Provides
    Activity privodeActivity(){
     return mActivity;
 }
}
