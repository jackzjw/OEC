package shangchuan.com.oec.ui.home.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.ui.apply.activity.CreateWorkReportActivity;
import shangchuan.com.oec.ui.home.activity.SearchActivity;
import shangchuan.com.oec.ui.home.activity.TotalBannerActivity;
import shangchuan.com.oec.widget.BasePopupWindow;

/**
 * Created by sg280 on 2017/3/6.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_img)
    ImageView toolbarAdd;
    private BasePopupWindow mpopupWindow;
    private TextView mCreateWorkReport;
    @BindView(R.id.rel_search)
    RelativeLayout mSearch;
    @Override
    public void loadData() {
       toolbarAdd.setImageDrawable(getResources().getDrawable(R.drawable.home_icon_new));
       //toolbarAdd.setImageResource(R.drawable.home_icon_new);
         toolbarTitle.setText("商传");
        mSearch.setOnClickListener(this);

    }
    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInject() {
   //   getFragmentComponent().inject(this);
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

    }
}
