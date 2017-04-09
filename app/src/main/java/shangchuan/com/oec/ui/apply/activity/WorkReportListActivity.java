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
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.ui.apply.fragment.MyApproveListFragment;
import shangchuan.com.oec.ui.apply.fragment.MyCreateReportListFragment;

public class WorkReportListActivity extends BaseActivity {
   @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private List<Fragment> mFragmentList;


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_work_report_list;
    }

    @Override
    protected void initEventData() {
        mToolbarTitle.setText("工作报告列表");
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        mFragmentList=new ArrayList<>();
        mFragmentList.add(new MyCreateReportListFragment());
        mFragmentList.add(new MyApproveListFragment());
         mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
         mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }

    class MyViewPagerAdapter extends FragmentPagerAdapter{
        public MyViewPagerAdapter(FragmentManager fm){
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
                    return getString(R.string.apply_my_create);
                case 1:
                    return getString(R.string.apply_my_approve);
            }
            return super.getPageTitle(position);
        }
    }
}
