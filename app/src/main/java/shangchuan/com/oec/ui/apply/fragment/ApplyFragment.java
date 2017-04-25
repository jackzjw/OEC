package shangchuan.com.oec.ui.apply.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.ui.apply.activity.ApplyOfficeActivity;
import shangchuan.com.oec.ui.apply.activity.ApprovePendActivity;
import shangchuan.com.oec.ui.apply.activity.AttendanceActivity;
import shangchuan.com.oec.ui.apply.activity.ClientListActivity;
import shangchuan.com.oec.ui.apply.activity.CreateWorkOrderActivity;
import shangchuan.com.oec.ui.apply.activity.CreateWorkReportActivity;
import shangchuan.com.oec.ui.apply.activity.ProjectDetailsActivity;
import shangchuan.com.oec.ui.apply.activity.WorkOfficeListActivity;

/**
 * Created by sg280 on 2017/3/7.
 */

public class ApplyFragment<T extends RxPresent> extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.rb_apply_add_wo)
    RadioButton mCreateWO;
    @BindView(R.id.rb_apply_wo_list)
    RadioButton mWOlist;
    @BindView(R.id.rb_work_apply)
    RadioButton mWorkApply;
    @BindView(R.id.rb_apply_work_report)
    RadioButton mWorkReport;
    @BindView(R.id.rb_apply_approve_work)
    RadioButton mApproveWork;
    @BindView(R.id.rb_work_check_work)
    RadioButton mCheckWork;
    @BindView(R.id.rel_apply_client)
    RelativeLayout mClient;
    @BindView(R.id.rel_apply_project)
    RelativeLayout mProject;



    @Override
    public void loadData() {
        mToolbarTitle.setText("应用");
        mCreateWO.setOnClickListener(this);
        mWOlist.setOnClickListener(this);
        mWorkApply.setOnClickListener(this);
        mWorkReport.setOnClickListener(this);
        mCheckWork.setOnClickListener(this);
        mApproveWork.setOnClickListener(this);
        mClient.setOnClickListener(this);
        mProject.setOnClickListener(this);
    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_apply;
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb_apply_add_wo:
                //创建工单
                mActivity.startActivity(new Intent(mActivity, CreateWorkOrderActivity.class));
                break;
            case R.id.rb_work_apply:
                //办公申请
                mActivity.startActivity(new Intent(mActivity, ApplyOfficeActivity.class));
                break;
            case R.id.rb_apply_work_report:
                //工作报告
                mActivity.startActivity(new Intent(mActivity, CreateWorkReportActivity.class));
                break;
            case R.id.rel_apply_client:
                //客户
                mActivity.startActivity(new Intent(mActivity, ClientListActivity.class));
                break;
            case R.id.rel_apply_project:
                //项目
                mActivity.startActivity(new Intent(mActivity, ProjectDetailsActivity.class));
                break;
            case R.id.rb_apply_approve_work:
                //办公审批
                startActivity(new Intent(mActivity, ApprovePendActivity.class));
                break;
            case R.id.rb_apply_wo_list:
                //工单列表
                startActivity(new Intent(mActivity, WorkOfficeListActivity.class));
                break;
            case R.id.rb_work_check_work:
                //考勤
                startActivity(new Intent(mActivity, AttendanceActivity.class));
                break;

        }
    }

    @Override
    public void showError(String msg) {

    }
}
