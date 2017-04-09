package shangchuan.com.oec.ui.user.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;

public class OrganizationInfoActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar_img)
    ImageView mToolbartRight;

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_organization_info;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("机构信息");
        mToolbartRight.setImageResource(R.drawable.user_icon_revise);
        initToolBar(mToolbar);
        mToolbartRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrganizationInfoActivity.this,ModifyOrganizeInfoActivity.class));
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
