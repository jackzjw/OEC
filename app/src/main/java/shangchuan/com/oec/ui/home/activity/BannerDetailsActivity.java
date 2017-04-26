package shangchuan.com.oec.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.component.RxBus;
import shangchuan.com.oec.model.bean.NewsDetailsBean;
import shangchuan.com.oec.present.NewsDetailPresent;
import shangchuan.com.oec.present.contact.NewsDetailsContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.LoadingView;

public class BannerDetailsActivity extends BaseActivity<NewsDetailPresent> implements NewsDetailsContract.View {
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.news_title)
    TextView mTitle;
    @BindView(R.id.create_time)
    TextView mDate;
    @BindView(R.id.content)
    TextView mContent;

   public static Intent getInstance(Context context,int id,int position){
       Intent intent=new Intent(context,BannerDetailsActivity.class);
       intent.putExtra("id",id);
       intent.putExtra("position",position);
       return intent;
   }
    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_banner_details;
    }

    @Override
    protected void initEventData() {
        mToolbarTitle.setText("更新日志");
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        //刷新adapter,未读标识换成已读标识
        LogUtil.i("post");
        RxBus.getDefault().post(getIntent().getIntExtra("position",-1));
        LoadingView.showProgress(this);
        mPresent.getNewsDetails(getIntent().getIntExtra("id",-1));
    }

    @Override
    protected void initInject() {
          getActivityComponent().inject(this);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public void showError(String msg) {
          LoadingView.dismissProgress();
          ToastUtil.shortShow(msg);
    }

    @Override
    public void showContent(NewsDetailsBean bean) {
        LoadingView.dismissProgress();
        mTitle.setText(bean.getNewsTitle());
        mDate.setText(bean.getCreateTime());
        mContent.setText(bean.getNewsContent());
    }
}
