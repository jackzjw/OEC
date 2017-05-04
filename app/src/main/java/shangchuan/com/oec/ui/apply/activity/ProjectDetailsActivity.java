package shangchuan.com.oec.ui.apply.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.SimpleActivity;
import shangchuan.com.oec.ui.apply.fragment.project.DocumentFragment;
import shangchuan.com.oec.ui.apply.fragment.project.ProjectTrendFragment;
import shangchuan.com.oec.ui.apply.fragment.project.TaskFragment;

public class ProjectDetailsActivity extends SimpleActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar_right_title)
    TextView mToolbarRight;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private List<Fragment> mFragmentList;
    @Override
    public void showError(String msg) {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_project_details2;
    }

    @Override
    protected void initEventAndData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("项目");
        mToolbarRight.setText("成员列表");
        initToolBar(mToolbar);
        mFragmentList=new ArrayList<>();
        mFragmentList.add(new TaskFragment());
        mFragmentList.add(new DocumentFragment());
        mFragmentList.add(new ProjectTrendFragment());
        mViewPager.setAdapter(new ListViewpagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }
    class ListViewpagerAdapter extends FragmentPagerAdapter {

        public ListViewpagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "任务";
                case 1:
                    return "文档";
                case 2:
                    return "动态";

            }
            return super.getPageTitle(position);
        }
    }
}
