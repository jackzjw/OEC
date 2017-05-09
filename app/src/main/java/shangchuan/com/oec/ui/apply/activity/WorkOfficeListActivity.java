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
import shangchuan.com.oec.ui.apply.fragment.workofficelist.CreatedFragment;
import shangchuan.com.oec.ui.apply.fragment.workofficelist.PendingFragment;
import shangchuan.com.oec.ui.apply.fragment.workofficelist.TotalFragment;

public class WorkOfficeListActivity extends BaseActivity{

    @BindView(R.id.toolbar_img)
    ImageView toolbarImg;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private ArrayList<Fragment> mFragmentList;


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_work_office_list;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        toolbarImg.setImageResource(R.drawable.home_icon_news_search);
        mToolbarTitle.setText("工单列表");
        initToolBar(mToolbar);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new PendingFragment());
        mFragmentList.add(new CreatedFragment());
        mFragmentList.add(new TotalFragment());
        mViewPager.setAdapter(new WoListViewPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }
    @Override
    protected void initInject() {

    }


    @Override
    public void showError(String msg) {

    }
    class WoListViewPagerAdapter extends FragmentPagerAdapter {
        public WoListViewPagerAdapter(FragmentManager fm){
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
                    return "待处理";
                case 1:
                    return "已创建";
                case 2:
                    return "全部";
            }
            return super.getPageTitle(0);
        }
    }
}
