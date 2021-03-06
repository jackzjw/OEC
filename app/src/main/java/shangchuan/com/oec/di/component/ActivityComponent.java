package shangchuan.com.oec.di.component;

import android.app.Activity;

import dagger.Component;
import shangchuan.com.oec.di.ActivityScope;
import shangchuan.com.oec.di.module.ActivityModule;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.ui.WelcomeActivity;
import shangchuan.com.oec.ui.apply.activity.AddClientActivity;
import shangchuan.com.oec.ui.apply.activity.ApplyCommonActivity;
import shangchuan.com.oec.ui.apply.activity.ApplyLeaveActivity;
import shangchuan.com.oec.ui.apply.activity.ApplyOfficeDetailsActivity;
import shangchuan.com.oec.ui.apply.activity.ApprovePendListActivity;
import shangchuan.com.oec.ui.apply.activity.ApproverActivity;
import shangchuan.com.oec.ui.apply.activity.AttendanceActivity;
import shangchuan.com.oec.ui.apply.activity.ClientDetailsActivity;
import shangchuan.com.oec.ui.apply.activity.CreateWorkOrderActivity;
import shangchuan.com.oec.ui.apply.activity.CreateWorkReportActivity;
import shangchuan.com.oec.ui.apply.activity.ModifyBaseActivity;
import shangchuan.com.oec.ui.apply.activity.ModifyClientDetailsActivity;
import shangchuan.com.oec.ui.apply.activity.ModifyWRDetailActivity;
import shangchuan.com.oec.ui.apply.activity.ModifyWoDetailActivity;
import shangchuan.com.oec.ui.apply.activity.ProjectListActivity;
import shangchuan.com.oec.ui.apply.activity.ReimburseActivity;
import shangchuan.com.oec.ui.apply.activity.TaskDetailsActivity;
import shangchuan.com.oec.ui.apply.activity.WoDetailsActivity;
import shangchuan.com.oec.ui.apply.activity.WorkOutsideActivity;
import shangchuan.com.oec.ui.apply.activity.WorkOvertimeActivity;
import shangchuan.com.oec.ui.apply.activity.WorkReportDetailActivity;
import shangchuan.com.oec.ui.home.activity.BannerDetailsActivity;
import shangchuan.com.oec.ui.home.activity.SearchBannerActivity;
import shangchuan.com.oec.ui.home.activity.SearchTrendActivity;
import shangchuan.com.oec.ui.team.activity.AddUserActivity;
import shangchuan.com.oec.ui.team.activity.RoleListActivity;
import shangchuan.com.oec.ui.user.activity.LoginActivity;
import shangchuan.com.oec.ui.user.activity.OrganizationListActivity;
import shangchuan.com.oec.ui.user.activity.SwitchOrganizationActivity;
import shangchuan.com.oec.ui.user.activity.UserInfoActivity;

/**
 * Created by sg280 on 2017/3/6.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    RetrofitHelper getHttpHelper();
    Activity getActivity();
    FragmentComponent fragmentComponent();
    void inject(LoginActivity activity);
    void inject(OrganizationListActivity activity);
    void inject(SwitchOrganizationActivity activity);
    void inject(WelcomeActivity activity);
    void inject(ApplyOfficeDetailsActivity activity);
    void inject(CreateWorkOrderActivity activity);
    void inject(ClientDetailsActivity activity);
    void inject(AddClientActivity activity);
    void inject(ModifyClientDetailsActivity activity);
    void inject(ApproverActivity activity);
    void inject (WorkOvertimeActivity activity);
    void inject(WorkOutsideActivity activity);
    void inject(ReimburseActivity activity);
    void inject(ApplyCommonActivity activity);
    void inject(ApplyLeaveActivity activity);
    void inject(CreateWorkReportActivity activity);
    void inject (WorkReportDetailActivity activity);
    void inject(WoDetailsActivity activity);
    void inject(AttendanceActivity activity);
    void inject(BannerDetailsActivity activity);
    void inject(AddUserActivity activity);
    void inject(RoleListActivity activity);
    void inject(ApprovePendListActivity activity);
    void inject(UserInfoActivity activity);
    void inject(ProjectListActivity activity);
    void inject(ModifyWoDetailActivity activity);
    void inject(ModifyWRDetailActivity activity);
    void inject(SearchTrendActivity activity);
    void inject(SearchBannerActivity activity);
    void inject(TaskDetailsActivity activity);
    void inject(ModifyBaseActivity activity);

}
