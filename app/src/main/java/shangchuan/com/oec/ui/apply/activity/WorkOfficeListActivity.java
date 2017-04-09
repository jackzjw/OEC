package shangchuan.com.oec.ui.apply.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

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
    private PendingFragment mWoListBaseFragment;
    private CreatedFragment mCreatedFragment;
    private TotalFragment mTotalFragment;

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

        mWoListBaseFragment =(PendingFragment) getSupportFragmentManager().findFragmentByTag("first");
        if(mWoListBaseFragment ==null){
            mWoListBaseFragment =new PendingFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, mWoListBaseFragment,"pending");
            transaction.commit();
        }
      mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
          @Override
          public void onTabSelected(TabLayout.Tab tab) {
              if(tab.getPosition()==0){
                  switchFragment(mWoListBaseFragment,"pending");

              }else if(tab.getPosition()==1){
                  mCreatedFragment=(CreatedFragment)getSupportFragmentManager().findFragmentByTag("created");
                  if(mCreatedFragment==null){
                      mCreatedFragment=new CreatedFragment();
                  }
                  switchFragment(mCreatedFragment,"created");
              }else {
                  mTotalFragment=(TotalFragment)getSupportFragmentManager().findFragmentByTag("total");
                  if(mTotalFragment==null){
                      mTotalFragment=new TotalFragment();
                  }
                  switchFragment(mTotalFragment,"total");
              }
          }

          @Override
          public void onTabUnselected(TabLayout.Tab tab) {

          }

          @Override
          public void onTabReselected(TabLayout.Tab tab) {

          }
      });
    }
    private void switchFragment(Fragment f, String tagname){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        if(f.isAdded()){
            transaction.show(f).commit();
        }else {
            transaction.add(R.id.fragment_container,f,tagname).commit();
        }


    }
    private void hideFragment(FragmentTransaction transaction){
        if(mWoListBaseFragment !=null&& mWoListBaseFragment.isAdded()){
            transaction.hide(mWoListBaseFragment);
        }
        if(mCreatedFragment!=null&&mCreatedFragment.isAdded()){
            transaction.hide(mCreatedFragment);
        }
        if(mTotalFragment!=null&&mTotalFragment.isAdded()){
            transaction.hide(mTotalFragment);
        }

    }
    @Override
    protected void initInject() {

    }


    @Override
    public void showError(String msg) {

    }
}
