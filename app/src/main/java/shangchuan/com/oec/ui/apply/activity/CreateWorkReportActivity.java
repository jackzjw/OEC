package shangchuan.com.oec.ui.apply.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;

public class CreateWorkReportActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_img)
    ImageView mReportList;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_create_work_report;
    }

    @Override
    protected void initEventData() {
       mToolbarTitle.setText("创建工作报告");
        mReportList.setImageResource(R.drawable.application_icon_record);
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        mReportList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateWorkReportActivity.this,WorkReportDetailActivity.class));
            }
        });

    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }
}
