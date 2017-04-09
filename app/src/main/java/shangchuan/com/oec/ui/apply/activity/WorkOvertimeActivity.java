package shangchuan.com.oec.ui.apply.activity;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;

public class WorkOvertimeActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView mToolBarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_work_overtime;
    }

    @Override
    protected void initEventData() {
        mToolBarTitle.setText("加班");
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }
}
