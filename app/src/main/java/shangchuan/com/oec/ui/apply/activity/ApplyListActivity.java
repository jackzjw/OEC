package shangchuan.com.oec.ui.apply.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.ui.apply.fragment.ApplyLeaveListFragment;
import shangchuan.com.oec.ui.apply.fragment.CommonListFragment;
import shangchuan.com.oec.ui.apply.fragment.OaOverTimeListFragment;
import shangchuan.com.oec.ui.apply.fragment.OutsideListFragment;
import shangchuan.com.oec.ui.apply.fragment.ReimburseListFragment;
import shangchuan.com.oec.util.ToastUtil;

public class ApplyListActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
     private  List<Fragment> mFragmentList;

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_apply_list;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("我的申请");
        initToolBar(mToolbar);
        mFragmentList=new ArrayList<>();
        mFragmentList.add(OaOverTimeListFragment.getInstance());
        mFragmentList.add(ApplyLeaveListFragment.getInstance());
        mFragmentList.add(OutsideListFragment.getInstance());
        mFragmentList.add(ReimburseListFragment.getInstance());
        mFragmentList.add(CommonListFragment.getInstance());
        mViewPager.setAdapter(new ListViewpagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {
        ToastUtil.show(msg);

    }



    class ListViewpagerAdapter extends FragmentStatePagerAdapter{

        public ListViewpagerAdapter(FragmentManager fm){
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
                    return getResources().getString(R.string.apply_work_overtime);
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
