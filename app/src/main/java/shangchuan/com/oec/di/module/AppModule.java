package shangchuan.com.oec.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import shangchuan.com.oec.app.App;
import shangchuan.com.oec.di.ContextLife;
import shangchuan.com.oec.model.http.RetrofitHelper;

/**
 * Created by sg280 on 2017/3/6.
 */
@Module
public class AppModule {
   private App mApplication;
    public AppModule(App application){
        this.mApplication=application;
    }
    @Provides
    @ContextLife("Application")
    App privodeContext(){
        return mApplication;
    }
    @Provides
    @Singleton
    RetrofitHelper privodeHttpHelper(){
        return new RetrofitHelper();
    }


}
