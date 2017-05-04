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
import shangchuan.com.oec.ui.apply.fragment.project.MyLaunchFragment;
import shangchuan.com.oec.ui.apply.fragment.project.MyResponseFragment;

public class MyTaskActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private List<Fragment> mFragmentList;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_task;
    }

    @Override
    protected void initEventAndData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("我的任务");
        initToolBar(mToolbar);
        mFragmentList=new ArrayList<>();
        mFragmentList.add(new MyResponseFragment());
        mFragmentList.add(new MyLaunchFragment());
        mViewPager.setAdapter(new ListViewpagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void showError(String msg) {

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
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "我负责的";
                case 1:
                    return "我发起的";

            }
            return super.getPageTitle(position);
        }
    }
}
