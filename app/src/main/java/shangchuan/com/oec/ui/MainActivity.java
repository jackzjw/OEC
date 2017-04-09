package shangchuan.com.oec.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.ui.apply.fragment.ApplyFragment;
import shangchuan.com.oec.ui.home.fragment.HomeFragment;
import shangchuan.com.oec.ui.team.fragment.TeamFragment;
import shangchuan.com.oec.ui.user.fragment.UserFragment;
import shangchuan.com.oec.util.ToastUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    protected static final String TAG = "MainActivity";
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_classify)
    RadioButton rbClassify;
    @BindView(R.id.rb_team)
    RadioButton rbTeam;
    @BindView(R.id.rb_user)
    RadioButton rbUser;
    private HomeFragment mHomeFragment;
    private ApplyFragment mApplyFragment;
    private TeamFragment mTeamFragment;
    private UserFragment mUserFragment;
    private Fragment temp;


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventData() {
        rbClassify.setOnClickListener(this);
        rbHome.setOnClickListener(this);
        rbTeam.setOnClickListener(this);
        rbUser.setOnClickListener(this);
        mHomeFragment=(HomeFragment) getSupportFragmentManager().findFragmentByTag("first");
        if(mHomeFragment==null){
            mHomeFragment=new HomeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container,mHomeFragment,"first");
            transaction.commit();
            Log.e(TAG,"application recreate");
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void initInject() {

    }
    private void switchFragment(Fragment f,String tagname){
        Log.e(TAG,"tagFragment="+temp);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        if(f.isAdded()){
            transaction.show(f).commit();
        }else {
            transaction.add(R.id.fragment_container,f,tagname).commit();
        }


    }
 private void hideFragment(FragmentTransaction transaction){
     if(mHomeFragment!=null&&mHomeFragment.isAdded()){
         transaction.hide(mHomeFragment);
     }
     if(mApplyFragment!=null&&mApplyFragment.isAdded()){
         Log.e(TAG,"applyFragment hide");
         transaction.hide(mApplyFragment);
     }
     if(mTeamFragment!=null&&mTeamFragment.isAdded()){
         transaction.hide(mTeamFragment);
     }
     if(mUserFragment!=null&&mUserFragment.isAdded()){
         transaction.hide(mUserFragment);
     }
 }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb_home:
                 switchFragment(mHomeFragment,"first");
                break;
            case R.id.rb_classify:
                mApplyFragment =(ApplyFragment)getSupportFragmentManager().findFragmentByTag("second");
                if(mApplyFragment ==null) {
                mApplyFragment =new ApplyFragment();
                }
                switchFragment(mApplyFragment,"second");
                break;
            case R.id.rb_team:
               mTeamFragment=(TeamFragment)getSupportFragmentManager().findFragmentByTag("third");
                if(mTeamFragment==null) {
                    mTeamFragment = new TeamFragment();
                }
                switchFragment(mTeamFragment,"third");
                break;
            case R.id.rb_user:
                mUserFragment=(UserFragment)getSupportFragmentManager().findFragmentByTag("four");
                if(mUserFragment==null) {
                    mUserFragment = new UserFragment();
                }
                switchFragment(mUserFragment,"four");
                break;


        }

    }

    @Override
    public void showError(String msg) {
        ToastUtil.show(msg);

    }


}
