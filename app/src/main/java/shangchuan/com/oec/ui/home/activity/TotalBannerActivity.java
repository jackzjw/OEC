package shangchuan.com.oec.ui.home.activity;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.ui.home.fragment.AlreadyReadBannerFragment;
import shangchuan.com.oec.ui.home.fragment.TotalBannerFragment;
import shangchuan.com.oec.ui.home.fragment.UnreadBannerFragment;

public class TotalBannerActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "TotalBannerActivity";
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_img)
    ImageView toolbarSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rb_banner_total)
    RadioButton mBannerTotal;
    @BindView(R.id.rb_banner_already_read)
    RadioButton mAlreadyBanner;
    @BindView(R.id.rb_banner_unread)
    RadioButton mUnreadBanner;
    private TotalBannerFragment mBannersBaseFragment;
    private AlreadyReadBannerFragment mAlreadyReadBannerFragment;
    private UnreadBannerFragment mUnreadBannerFragment;
    private Fragment temp;
    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_total_banner;
    }

    @Override
    protected void initEventData() {
        toolbarTitle.setText("全部公告");
        toolbarSearch.setImageResource(R.drawable.home_icon_news_search);
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        mUnreadBanner.setOnClickListener(this);
        mBannerTotal.setOnClickListener(this);
        mAlreadyBanner.setOnClickListener(this);
        mBannersBaseFragment =(TotalBannerFragment) getSupportFragmentManager().findFragmentByTag("total");
        if(mBannersBaseFragment ==null){
            mBannersBaseFragment =new TotalBannerFragment();
        }
         getSupportFragmentManager().beginTransaction().
                 add(R.id.total_banners_fragment_container, mBannersBaseFragment,"total").commit();
         temp= mBannersBaseFragment;
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb_banner_total:
                //全部
               switchFragment(mBannersBaseFragment,"total");
                break;
            case R.id.rb_banner_already_read:
                //已读
          mAlreadyReadBannerFragment=(AlreadyReadBannerFragment) getSupportFragmentManager().findFragmentByTag("already");
            if(mAlreadyReadBannerFragment==null){
                mAlreadyReadBannerFragment=new AlreadyReadBannerFragment();
            }
                switchFragment(mAlreadyReadBannerFragment,"already");
                break;
            case R.id.rb_banner_unread:
                //未读
          mUnreadBannerFragment=(UnreadBannerFragment)getSupportFragmentManager().findFragmentByTag("unread");
                if(mUnreadBannerFragment==null){
                    mUnreadBannerFragment=new UnreadBannerFragment();
                }
                switchFragment(mUnreadBannerFragment,"unread");
                break;
        }

    }
    private void switchFragment(Fragment f, String tagname){
        Log.e(TAG,"switchFragment");
        if(temp==null) return;
        if(temp!=f){
            if(f.isAdded()){
                getSupportFragmentManager().beginTransaction().hide(temp).show(f).commit();
            }else {
                getSupportFragmentManager().beginTransaction().hide(temp).add(R.id.total_banners_fragment_container,f,tagname).commit();
            }
            temp=f;
        }
    }

    @Override
    public void showError(String msg) {

    }
}
