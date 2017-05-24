package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
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
import shangchuan.com.oec.ui.apply.fragment.project.HeLaunchFragment;
import shangchuan.com.oec.ui.apply.fragment.project.HeResponseFragment;
import shangchuan.com.oec.util.LogUtil;

public class HeTaskActivity extends SimpleActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private List<Fragment> mFragmentList;
  public static Intent getInstance(Context context,String userid,String nickName,String tel){
      Intent intent=new Intent(context,HeTaskActivity.class);
      intent.putExtra("userid",userid);
      intent.putExtra("nickname",nickName);
      intent.putExtra("tel",tel);
      return intent;
  }
    @Override
    protected int getLayout() {
        return R.layout.activity_my_task;
    }

    @Override
    protected void initEventAndData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        String nickName=getIntent().getStringExtra("nickname");
        mToolbarTitle.setText(nickName+"的任务");
        String userId=getIntent().getStringExtra("userid");
        String tel=getIntent().getStringExtra("tel");
        LogUtil.i("heTaskActivity"+tel);
        mFragmentList=new ArrayList<>();
        mFragmentList.add(HeResponseFragment.getInstance(userId,tel));
        mFragmentList.add(HeLaunchFragment.getInstance(userId));
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
                    return "他负责的";
                case 1:
                    return "他发起的";

            }
            return super.getPageTitle(position);
        }
    }
}
