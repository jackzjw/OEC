package shangchuan.com.oec.ui.home.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.ui.home.fragment.TotalTrendsFragment;
import shangchuan.com.oec.ui.home.fragment.TrendOaFragment;
import shangchuan.com.oec.ui.home.fragment.TrendWoFragment;

public class TrendsActivity extends BaseActivity {

    @BindView(R.id.toolbar_img)
    ImageView mToolbarImg;
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
        return R.layout.activity_trends;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarImg.setImageResource(R.drawable.home_icon_news_search);
        mToolbarTitle.setText("全部动态");
        initToolBar(mToolbar);
          mToolbarImg.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  startActivity(new Intent(TrendsActivity.this,SearchTrendActivity.class));
              }
          });
        mFragmentList=new ArrayList<>();
        mFragmentList.add(new TotalTrendsFragment());
        mFragmentList.add(new TrendWoFragment());
        mFragmentList.add(new TrendOaFragment());
        mViewPager.setAdapter(new TrendViewPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }
    class TrendViewPagerAdapter extends FragmentPagerAdapter {
        public TrendViewPagerAdapter(FragmentManager fm){
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
                    return "全部";
                case 1:
                    return "工单";
                case 2:
                    return "办公";
            }
            return super.getPageTitle(0);
        }
    }
}
