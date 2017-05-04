package shangchuan.com.oec.ui.apply.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;

public class ProjectListActivity extends BaseActivity {

    @BindView(R.id.rel_mytask)
    RelativeLayout mRelMyTask;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_project_list;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("项目");
        initToolBar(mToolbar);
       mRelMyTask.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(ProjectListActivity.this,ProjectDetailsActivity.class));
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
