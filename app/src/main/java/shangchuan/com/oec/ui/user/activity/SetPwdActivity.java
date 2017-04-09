package shangchuan.com.oec.ui.user.activity;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;

public class SetPwdActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_set_pwd;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("找回密码");
        initToolBar(mToolbar);
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }
}
