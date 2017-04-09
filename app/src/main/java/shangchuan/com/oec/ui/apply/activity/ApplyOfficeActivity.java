package shangchuan.com.oec.ui.apply.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;

public class ApplyOfficeActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.toolbar_title)
    TextView mToolBarTitle;
    @BindView(R.id.toolbar_img)
    ImageView mToolbarRecord;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rel_work_overtime)
    RelativeLayout relOverTime;
    @BindView(R.id.rel_work_leave)
    RelativeLayout mApplyLeave;
    @BindView(R.id.rel_work_outside)
    RelativeLayout mWorkOutside;
    @BindView(R.id.rel_work_reimburse)
    RelativeLayout relReimburse;
    @BindView(R.id.rel_apply_common)
    RelativeLayout relCommon;


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_apply_office;
    }

    @Override
    protected void initEventData() {
        mToolBarTitle.setText("办公申请");
        mToolbarRecord.setImageResource(R.drawable.application_icon_record);
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        mToolbarRecord.setOnClickListener(this);
        relCommon.setOnClickListener(this);
        relOverTime.setOnClickListener(this);
        relReimburse.setOnClickListener(this);
        mWorkOutside.setOnClickListener(this);
        mApplyLeave.setOnClickListener(this);
        mToolbarRecord.setOnClickListener(this);
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rel_work_overtime:
                startActivity(new Intent(this,WorkOvertimeActivity.class));
                break;
            case R.id.rel_work_leave:
                startActivity(new Intent(this,ApplyLeaveActivity.class));
                break;
            case R.id.rel_work_outside:
                startActivity(new Intent(this,WorkOutsideActivity.class));
                break;
            case R.id.rel_work_reimburse:
                startActivity(new Intent(this,ReimburseActivity.class));
                break;
            case R.id.rel_apply_common:
                startActivity(new Intent(this,ApplyCommonActivity.class));
                break;
            case R.id.toolbar_img:
                startActivity(new Intent(this,ApplyListActivity.class));
                break;
        }

    }

    @Override
    public void showError(String msg) {

    }
}
