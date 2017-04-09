package shangchuan.com.oec.di.component;

import javax.inject.Singleton;

import dagger.Component;
import shangchuan.com.oec.app.App;
import shangchuan.com.oec.di.ContextLife;
import shangchuan.com.oec.di.module.AppModule;
import shangchuan.com.oec.model.http.RetrofitHelper;

/**
 * Created by sg280 on 2017/3/6.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
     @ContextLife("Application")
     App getApplication();
    RetrofitHelper getHttpHelper();



}

