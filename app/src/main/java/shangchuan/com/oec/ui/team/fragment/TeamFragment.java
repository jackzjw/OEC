package shangchuan.com.oec.ui.team.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.ui.team.activity.AddOrganizeStructureActivity;

/**
 * Created by sg280 on 2017/3/7.
 */

public class TeamFragment<T extends RxPresent> extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.rb_team_user)
    RadioButton rbUser;
    @BindView(R.id.rb_team_organize)
    RadioButton rbOrganize;
    @BindView(R.id.rb_team_character)
    RadioButton rbCharacter;
    @BindView(R.id.img_team_add_user)
    ImageView imgAddUser;
    @BindView(R.id.team_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.rg_team)
    RadioGroup mRadioGroup;
    private List<Fragment> mfragmentList;

    @Override
    public void loadData() {
        imgAddUser.setOnClickListener(this);
        mfragmentList=new ArrayList<>();
        mfragmentList.add(new TeamUserFragment());
        mfragmentList.add(new OrganizeFragment());
        mfragmentList.add(new CharacterFragment());
        TeamPagerAdapter viewPagerAdapter = new TeamPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setCurrentItem(0);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_team_user:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_team_organize:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_team_character:
                        mViewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                  switch (position){
                      case 0:  rbUser.setChecked(true);
                          break;
                      case 1:  rbOrganize.setChecked(true);
                          break;
                      case 2:  rbCharacter.setChecked(true);
                          break;
                  }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_team;
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_team_add_user:
                startActivity(new Intent(mActivity, AddOrganizeStructureActivity.class));
                break;
        }
    }

    @Override
    public void showError(String msg) {

    }

    class TeamPagerAdapter extends FragmentPagerAdapter{
     public TeamPagerAdapter(FragmentManager fm) {
         super(fm);
     }

        @Override
        public Fragment getItem(int position) {
            return mfragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
