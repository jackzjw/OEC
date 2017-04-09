package shangchuan.com.oec.ui.apply.activity;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;

public class AddContactsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_add_contacts;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("新增联系人");
        initToolBar(mToolbar);
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }
}
