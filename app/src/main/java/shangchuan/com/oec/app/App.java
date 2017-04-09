package shangchuan.com.oec.app;

import android.app.Activity;
import android.app.Application;

import java.util.HashSet;

import shangchuan.com.oec.di.component.AppComponent;
import shangchuan.com.oec.di.component.DaggerAppComponent;
import shangchuan.com.oec.di.module.AppModule;

/**
 * Created by sg280 on 2017/3/3.
 */

public class App extends Application {
    private static App instance;
    private HashSet<Activity> mActivities;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
    public static App getInstance(){
        return instance;
    }
    public void addActivity(Activity activity){
        if(mActivities==null){
            mActivities=new HashSet<>();
        }
        mActivities.add(activity);
    }

    public void removeActivity(Activity activity){
        mActivities.remove(activity);
    }
    public void exitApp(){
         if(mActivities!=null){
             for(Activity act:mActivities){
                 mActivities.remove(act);
             }
         }
       android.os.Process.killProcess(android.os.Process.myPid());
          System.exit(0);
    }
     public static AppComponent getAppComponent(){
         return DaggerAppComponent.builder().appModule(new AppModule(instance)).build();
     }
}

