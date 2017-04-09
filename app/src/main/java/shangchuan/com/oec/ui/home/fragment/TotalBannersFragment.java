package shangchuan.com.oec.ui.home.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;

/**
 * Created by sg280 on 2017/3/10.
 */

public class TotalBannersFragment extends BaseFragment {
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    List<Fragment> mFragmentList;
    private TotalBannerPagerAdapter madapter;

    @Override
    public void loadData() {
   mFragmentList=new ArrayList<>();
        mFragmentList.add(new UpdateLogFragment());
        mFragmentList.add(new SystemBannerFragment());
        mFragmentList.add(new DayoffNotifyFragment());
        madapter=new TotalBannerPagerAdapter(mActivity.getSupportFragmentManager());
        mViewPager.setAdapter(madapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);

    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_total_banners;
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }

    class TotalBannerPagerAdapter extends FragmentPagerAdapter{
        public TotalBannerPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "更新日志";
                case 1:
                    return "系统公告";
                case 2:
                    return "放假通知";
            }
            return super.getPageTitle(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }


        @Override
        public int getCount() {
            return 3;
        }
    }

}
