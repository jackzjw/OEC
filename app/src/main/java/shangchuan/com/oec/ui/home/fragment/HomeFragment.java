package shangchuan.com.oec.ui.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.model.bean.NewsListBean;
import shangchuan.com.oec.model.bean.TrendsListBean;
import shangchuan.com.oec.present.HomeListPresent;
import shangchuan.com.oec.present.contact.HomeListContract;
import shangchuan.com.oec.ui.apply.activity.CreateWorkReportActivity;
import shangchuan.com.oec.ui.home.activity.SearchActivity;
import shangchuan.com.oec.ui.home.activity.TotalBannerActivity;
import shangchuan.com.oec.ui.home.adapter.NewsListAdapter;
import shangchuan.com.oec.ui.home.adapter.TrendsListAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.BasePopupWindow;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

/**
 * Created by sg280 on 2017/3/6.
 */

public class HomeFragment extends BaseFragment<HomeListPresent> implements View.OnClickListener,HomeListContract.View{
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_img)
    ImageView toolbarAdd;
    private BasePopupWindow mpopupWindow;
    private TextView mCreateWorkReport;
    @BindView(R.id.rel_search)
    RelativeLayout mSearch;
    @BindView(R.id.dynamic_recycleview)
    RecyclerView TrendsRec;
    @BindView(R.id.banner_recycleview)
    RecyclerView NewsRec;
    private NewsListAdapter newsAdapter;

    @Override
    public void loadData() {
       toolbarAdd.setImageDrawable(getResources().getDrawable(R.drawable.home_icon_new));
         toolbarTitle.setText("商传");
        mSearch.setOnClickListener(this);
        LoadingView.showProgress(mActivity);
        mPresent.getNewsList();
        mPresent.getTrendsList();

    }
    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInject() {
     getFragmentComponent().inject(this);
    }
    @OnClick(R.id.btn_total_banners)
    void toTotalActivity(){
        startActivity(new Intent(mActivity, TotalBannerActivity.class));


    }
    @OnClick(R.id.btn_total_dynamics)
    void toTotalDynamic(){

    }

    //点击加号
    @OnClick(R.id.toolbar_img)
    void add(){
       /*  mpopupWindow=new BasePopupWindow(mActivity);
        View contentView= LayoutInflater.from(mActivity).inflate(R.layout.popupwindow_home_add_work,null);
       mCreateWorkReport=(TextView)contentView.findViewById(R.id.create_work_report);
        mCreateWorkReport.setOnClickListener(this);
        mpopupWindow.setContentView(contentView);
        mpopupWindow.showAsDropDown(toolbarAdd,  DensityUtil.dp2px(mActivity,-140),DensityUtil.dp2px(mActivity,20),Gravity.BOTTOM|Gravity.RIGHT);*/

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_work_report:
                startActivity(new Intent(mActivity, CreateWorkReportActivity.class));
                mpopupWindow.dismiss();
                break;
            case R.id.rel_search:
                startActivity(new Intent(mActivity, SearchActivity.class));
                mActivity.overridePendingTransition(0,0);
                break;
        }

    }

    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.show(msg);
    }

    @Override
    public void showNewsList(List<NewsListBean> bean) {
           LoadingView.dismissProgress();
        NewsRec.setLayoutManager(new LinearLayoutManager(mActivity));
        NewsRec.addItemDecoration(new DividerDecoration(mActivity));
        newsAdapter=new NewsListAdapter(mActivity,bean);
         NewsRec.setAdapter(newsAdapter);
    }

    @Override
    public void showTrendsList(List<TrendsListBean> bean) {
         //  LoadingView.dismissProgress();
          TrendsRec.setLayoutManager(new LinearLayoutManager(mActivity));
          TrendsRec.addItemDecoration(new DividerDecoration(mActivity));
        TrendsListAdapter trendsAdapter = new TrendsListAdapter(mActivity, bean);
           TrendsRec.setAdapter(trendsAdapter);
    }
}
