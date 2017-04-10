package shangchuan.com.oec.di.component;

import android.app.Activity;

import dagger.Component;
import shangchuan.com.oec.di.ActivityScope;
import shangchuan.com.oec.di.module.ActivityModule;
import shangchuan.com.oec.ui.WelcomeActivity;
import shangchuan.com.oec.ui.apply.activity.ApplyOfficeDetailsActivity;
import shangchuan.com.oec.ui.apply.activity.ClientDetailsActivity;
import shangchuan.com.oec.ui.apply.activity.CreateWorkOrderActivity;
import shangchuan.com.oec.ui.user.activity.LoginActivity;
import shangchuan.com.oec.ui.user.activity.OrganizationListActivity;
import shangchuan.com.oec.ui.user.activity.SwitchOrganizationActivity;

/**
 * Created by sg280 on 2017/3/6.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
    FragmentComponent fragmentComponent();
    void inject(LoginActivity activity);
    void inject(OrganizationListActivity activity);
    void inject(SwitchOrganizationActivity activity);
    void inject(WelcomeActivity activity);
    void inject(ApplyOfficeDetailsActivity activity);
    void inject(CreateWorkOrderActivity activity);
    void inject(ClientDetailsActivity activity);
}
