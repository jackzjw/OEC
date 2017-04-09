package shangchuan.com.oec.ui.apply.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.ui.apply.fragment.CompanyClientListFragment;
import shangchuan.com.oec.ui.apply.fragment.PersonalClientListFragmet;

public class ClientListActivity extends BaseActivity {
    @BindView(R.id.toolbar_right_title)
    TextView mToolbarRight;
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
        return R.layout.activity_client;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarRight.setText(R.string.apply_add_client);
        mToolbarTitle.setText("客户列表");
        initToolBar(mToolbar);
        mFragmentList=new ArrayList<>();
        mFragmentList.add(new CompanyClientListFragment());
        mFragmentList.add(new PersonalClientListFragmet());
        mViewPager.setAdapter(new ClientViewPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        mToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClientListActivity.this,AddContactsActivity.class));
            }
        });

    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }

    class ClientViewPagerAdapter extends FragmentPagerAdapter {
        public ClientViewPagerAdapter(FragmentManager fm){
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
                    return getString(R.string.apply_company);
                case 1:
                    return getString(R.string.apply_personal);
            }
            return super.getPageTitle(position);
        }
    }
}
