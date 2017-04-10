package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.OaDetailsBean;
import shangchuan.com.oec.present.OaDetailsPresent;
import shangchuan.com.oec.present.contact.OaDetailsContract;
import shangchuan.com.oec.ui.apply.adapter.DocumentAdapter;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.DensityUtil;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.util.SharePreferenceUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CircleImageView;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

public class ApplyOfficeDetailsActivity extends BaseActivity<OaDetailsPresent> implements OaDetailsContract.View {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar_right_title)
    TextView mToolbartRight;
    @BindView(R.id.user_avater)
    CircleImageView mUserAvater;
    @BindView(R.id.user_order_type)
    TextView mOrderTitle;
    @BindView(R.id.submit_time)
    TextView mSubmitTime;
    @BindView(R.id.submit_start_time)
    TextView mStratTime;
    @BindView(R.id.submit_end_time)
    TextView mEndTime;
    @BindView(R.id.apply_hours)
    TextView mDuration;
    @BindView(R.id.apply_status)
    TextView mStatus;
    @BindView(R.id.apply_explain)
     TextView mContent;
    @BindView(R.id.remark_recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.img_status)
    ImageView imgStatus;
    @BindView(R.id.doc_recycleview)
    RecyclerView docRecycleView;


public static Intent newIntent(Context context,int id){
    Intent intent =new Intent(context,ApplyOfficeDetailsActivity.class);
    intent.putExtra("id",id);
    return  intent;
}


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_apply_office_details;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("加班申请详情");
        mToolbartRight.setText("撤回");
        initToolBar(mToolbar);
        int id=getIntent().getIntExtra("id",0);
        LoadingView.showProgress(this);
        mPresent.getData(id);
    }

    @Override
    protected void initInject() {
         getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.show(msg);
    }

    @Override
    public void showContent(OaDetailsBean bean) {
        LoadingView.dismissProgress();
        Glides.getInstance().load(this, SharePreferenceUtil.getUserAvater(),mUserAvater);
        mOrderTitle.setText(bean.getOrderTitle());
        mSubmitTime.setText(bean.getOrderTime());
        mStratTime.setText(bean.getStartTime());
        mEndTime.setText(bean.getEndTime());
        mDuration.setText(bean.getOrderPeriod());
        mStatus.setText(CommonUtil.orderStatus(bean.getOrderStatus()));
        if(bean.getOrderStatus()==1){
            mStatus.setTextColor(ContextCompat.getColor(this,R.color.apply_wait_approve_text_color));
            imgStatus.setVisibility(View.INVISIBLE);
        }
        mContent.setText(bean.getOrderContent());
       imgStatus.setImageResource(bean.isSuccess()?R.drawable.application_img_pass:R.drawable.application_img_reject);
       if(!bean.getAttachmentList().isEmpty()){
           DocumentAdapter documentAdapter=new DocumentAdapter(this,bean.getAttachmentList());
           docRecycleView.setLayoutManager(new LinearLayoutManager(this));
           docRecycleView.addItemDecoration(new DividerDecoration(
                   ContextCompat.getColor(this,R.color.theme_divide_color), DensityUtil.dp2px(this,1f)));
           docRecycleView.setAdapter(documentAdapter);
           documentAdapter.setOnItemClickListener(new DocumentAdapter.OnItemClickListener() {
               @Override
               public void ItemClick(int position) {

               }
           });
       }




    }
}
