package shangchuan.com.oec.ui.apply.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.ui.apply.fragment.approvepend.CommonFragment;
import shangchuan.com.oec.ui.apply.fragment.approvepend.LeaveFragment;
import shangchuan.com.oec.ui.apply.fragment.approvepend.OutsideFragment;
import shangchuan.com.oec.ui.apply.fragment.approvepend.OverTimeFragment;
import shangchuan.com.oec.ui.apply.fragment.approvepend.ReimburseFragment;

public class ApprovePendActivity extends BaseActivity {
    @BindView(R.id.toolbar_img)
    ImageView toolbarImg;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    private ArrayList<Fragment> mFragmentList;


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_approve_pend;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        toolbarImg.setImageResource(R.drawable.application_icon_more);
        mToolbarTitle.setText("待审批");
        initToolBar(mToolbar);
        mFragmentList=new ArrayList<>();
        mFragmentList.add(new OverTimeFragment());
        mFragmentList.add(new LeaveFragment());
        mFragmentList.add(new OutsideFragment());
        mFragmentList.add(new ReimburseFragment());
        mFragmentList.add(new CommonFragment());
        mViewPager.setAdapter(new PendViewPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }

    class PendViewPagerAdapter extends FragmentPagerAdapter {
        public PendViewPagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return getString(R.string.apply_work_overtime);
                case 1:
                    return getString(R.string.apply_leave);
                case 2:
                    return getString(R.string.apply_work_outside);
                case 3:
                    return getString(R.string.apply_reimburse);
                case 4:
                    return getString(R.string.apply_common);
            }
            return super.getPageTitle(position);
        }
    }
}
