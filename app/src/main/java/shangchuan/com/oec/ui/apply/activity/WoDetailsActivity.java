package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.widget.CircleImageView;

public class WoDetailsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar_right_title)
    TextView mToolbartRight;
    @BindView(R.id.user_avater)
    CircleImageView mUserAvater;
    @BindView(R.id.user_order_type)
    TextView mUserTitle;
    @BindView(R.id.order_num)
    TextView mOrderNum;
    @BindView(R.id.submit_time)
    TextView mSubmitDate;
    @BindView(R.id.order_type)
    TextView mSubmitType;
    @BindView(R.id.order_status)
    TextView mOrderStatus;
    @BindView(R.id.wo_flag)
    ImageView mFlag;
    @BindView(R.id.wo_title)
    TextView mWoTitle;
    @BindView(R.id.apply_explain)
    TextView mContent;
    @BindView(R.id.pic_recycleview)
    RecyclerView mPicRec;
    @BindView(R.id.doc_recycleview)
    RecyclerView mDocRec;
    @BindView(R.id.remark_recycleview)
    RecyclerView mRemarkRec;

    public static Intent getInstance(Context context,int id){
        Intent intent=new Intent(context,WoDetailsActivity.class);
        intent.putExtra("id",id);
        return intent;
    }


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_wo_details;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("工单详情");
        mToolbartRight.setText("撤回");
        initToolBar(mToolbar);
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }
}
