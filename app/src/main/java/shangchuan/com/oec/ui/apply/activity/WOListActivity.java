package shangchuan.com.oec.ui.apply.activity;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;

public class WOListActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_img)
    ImageView toolbarSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_wolist;
    }

    @Override
    protected void initEventData() {
        toolbarTitle.setText("工单列表");
        toolbarSearch.setImageResource(R.drawable.home_icon_news_search);
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
