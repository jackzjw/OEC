package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.component.RxBus;
import shangchuan.com.oec.model.bean.AttchmentBean;
import shangchuan.com.oec.model.bean.OaDetailsBean;
import shangchuan.com.oec.model.bean.ProcessListBean;
import shangchuan.com.oec.model.bean.SelectOwnerBean;
import shangchuan.com.oec.model.event.OaDealEvent;
import shangchuan.com.oec.model.event.WithDrawEvent;
import shangchuan.com.oec.present.OaDetailsPresent;
import shangchuan.com.oec.present.contact.OaDetailsContract;
import shangchuan.com.oec.ui.apply.adapter.DocumentAdapter;
import shangchuan.com.oec.ui.apply.adapter.OaRemarkAdapter;
import shangchuan.com.oec.ui.apply.adapter.ScanImgAdapter;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.SharePreferenceUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CircleImageView;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

public class ApplyOfficeDetailsActivity extends BaseActivity<OaDetailsPresent> implements OaDetailsContract.View,View.OnClickListener {
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
    @BindView(R.id.img_recycleview)
    RecyclerView ImgRec;
    @BindView(R.id.approve_pass)
    TextView mTvPass;
    @BindView(R.id.approve_turn_down)
    TextView mTvReject;
    @BindView(R.id.approve_to_other)
    TextView mToOther;
    @BindView(R.id.rel_first)
    RelativeLayout mRelSend;
    @BindView(R.id.rel_second)
    RelativeLayout mRelTurnDown;
    @BindView(R.id.rel_third)
    RelativeLayout mRelToOther;
    @BindView(R.id.et_msg)
    EditText mInputMsg;
    @BindView(R.id.rel_deal_result)
    LinearLayout mDealResult;

    private int mId;
    private int mIndex;
    private List<ProcessListBean> remarkList=new ArrayList<>();
    private OaRemarkAdapter remarkAdapter;
    private int dealType;
    private ArrayList<SelectOwnerBean> ownerList;
    private int mPos;
    private OaDetailsBean mData;

    //index用来确认是从审核列表跳过来的还是从我的申请列表跳过来的；index=1表示从申请列表跳过来，2：待审核列表
    //3：表示从已审核的跳转过来
    //position用来刷新处理后的状态
public static Intent newIntent(Context context,int id,int index,int position){
    Intent intent =new Intent(context,ApplyOfficeDetailsActivity.class);
    intent.putExtra("id",id);
    intent.putExtra("index",index);
    intent.putExtra("position",position);
    return  intent;
}


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_apply_office_details;
    }

    @Override
    protected void initEventData() {
        mIndex=getIntent().getIntExtra("index",0);
        mPos=getIntent().getIntExtra("position",-1);
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        if(mIndex==2){
            //待审核的显示处理操作
            mRelSend.setVisibility(View.VISIBLE);
            mRelTurnDown.setVisibility(View.VISIBLE);
            mRelToOther.setVisibility(View.VISIBLE);
            mTvPass.setText("通过");
            mTvReject.setText("驳回");
        }else if(mIndex==3||mIndex==1){
            //从自己创建、已审核的跳转过来
            mDealResult.setVisibility(View.GONE);
        }
        mToolbartRight.setOnClickListener(this);
        mTvPass.setOnClickListener(this);
        mTvReject.setOnClickListener(this);
        mToOther.setOnClickListener(this);
        mId=getIntent().getIntExtra("id",0);
        LoadingView.showProgress(this);
        mPresent.getData(mId);
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
    private String msg(){
        return mInputMsg.getText().toString().trim();
    }

    @Override
    public void showContent(OaDetailsBean bean) {
        LogUtil.i(bean.toString());
        this.mData=bean;
        LoadingView.dismissProgress();
        if(mIndex==1&&bean.getOrderStatus()==0){
            mToolbartRight.setText("重新提交");
        }else if(mIndex==1){
            mToolbartRight.setText("撤回");
        }
        mToolbarTitle.setText(bean.getOrderType()+"详情");
        Glides.getInstance().load(this, SharePreferenceUtil.getUserAvater(),mUserAvater);
        mOrderTitle.setText(bean.getCreateUserName()+"的"+bean.getOrderType()+"申请");
        mSubmitTime.setText(bean.getCreateTime());
        mStratTime.setText(bean.getStartTime());
        mEndTime.setText(bean.getEndTime());
        mDuration.setText(bean.getOrderPeriod());
        mStatus.setText(CommonUtil.orderStatus(bean.getOrderStatus()));
        int status=bean.getOrderStatus();
        //2：已通过 3：已驳回 0:已撤销 1：待审核
        if(status==2){
             imgStatus.setImageResource(R.drawable.application_img_pass);
            mDealResult.setVisibility(View.GONE);
        }else if(status==3){
            imgStatus.setImageResource(R.drawable.application_img_reject);
            mDealResult.setVisibility(View.GONE);
        }else if(status==0){
            mDealResult.setVisibility(View.GONE);
        }
        mContent.setText(bean.getOrderContent());





    }

    @Override
    public void showMediaContent(final List<AttchmentBean> medias) {
        ImgRec.setLayoutManager(new GridLayoutManager(this,4));
        ScanImgAdapter adapter = new ScanImgAdapter(this, medias);
        ImgRec.setAdapter(adapter);
        adapter.setItemClickListener(new ScanImgAdapter.OnItemClickListener() {
            @Override
            public void imgClick(int position, int mediaType) {
                if(mediaType== Constants.IMAGE_TYPE){
                    startActivity(ViewPagerActivity.getInstance(ApplyOfficeDetailsActivity.this,(ArrayList<AttchmentBean>) medias,position));
                }else if(mediaType==Constants.VEDIO_TYPE){
                    LoadingView.showProgress(ApplyOfficeDetailsActivity.this);
                    mPresent.downloadFile(medias.get(position).getUrl());
                }
            }
        });

    }

    @Override
    public void showTextContent(final List<AttchmentBean> texts) {
        DocumentAdapter documentAdapter=new DocumentAdapter(this,texts);
        docRecycleView.setLayoutManager(new LinearLayoutManager(this));
        docRecycleView.addItemDecoration(new DividerDecoration(this));
        docRecycleView.setAdapter(documentAdapter);
        documentAdapter.setOnItemClickListener(new DocumentAdapter.OnItemClickListener() {
            @Override
            public void ItemClick(int position) {
                LoadingView.showProgress(ApplyOfficeDetailsActivity.this);
                mPresent.downloadFile(texts.get(position).getUrl());
            }
        });
    }

    @Override
    public void openFile(String filePath) {
        LoadingView.dismissProgress();
        startActivity(CommonUtil.openFile(filePath));

    }

    @Override
    public void showRemark(List<ProcessListBean> bean) {
         remarkList=bean;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        remarkAdapter=new OaRemarkAdapter(this,remarkList);
        mRecyclerView.setAdapter(remarkAdapter);

    }

    @Override
    public void dealSuccess() {
        //2:通过 3:转他人处理 4:驳回
           LoadingView.dismissProgress();
       RxBus.getDefault().post(new OaDealEvent(mPos));
        finish();

    }

    @Override
    public void deleteSucc() {
        //撤回成功
        LoadingView.dismissProgress();
        RxBus.getDefault().post(new WithDrawEvent(mPos));
         finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constants.REQUEST_CODE&&resultCode==RESULT_OK){
           ownerList = (ArrayList<SelectOwnerBean>) data.getSerializableExtra("ownerlist");
            if(ownerList.size()>1){
                ToastUtil.shortShow("只能转一人处理,请重新选择");
                return;
            }
            if(ownerList.size()==0){
                ToastUtil.shortShow("请选择处理人");
                return;
            }
            LoadingView.showProgress(this);//转他人=3
            mPresent.dealOaCheck(mId,3,msg(),ownerList.get(0).getId()+"");
            dealType=3;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.approve_pass://通过=2
                 LoadingView.showProgress(this);
                mPresent.dealOaCheck(mId,2,msg(),"0");
                dealType=2;
                break;
            case R.id.approve_turn_down://驳回=4
                if(TextUtils.isEmpty(msg())){
                    ToastUtil.shortShow("请填写审批意见");
                    return;
                }
                 LoadingView.showProgress(this);
                mPresent.dealOaCheck(mId,4,msg(),"0");
                dealType=4;
                break;
            case R.id.approve_to_other:
                if(TextUtils.isEmpty(msg())){
                    ToastUtil.shortShow("请填写审批意见");
                    return;
                }
                  startActivityForResult(new Intent(this,ApproverActivity.class),Constants.REQUEST_CODE);
                break;
            case R.id.toolbar_right_title:
                 //重新提交
                if(mData.getOrderStatus()==0){
                    String orderType=mData.getOrderType();
                   if(orderType.equals("加班")){
                       startActivity(ModifyOverTimeActivity.getInstance(this,mData));
                   }else if(orderType.equals("报销")) {
                       startActivity(ModifyReimburseActivity.getInstance(this, mData));
                   }else if(orderType.equals("外勤/出差")){
                       startActivity(ModifyReimburseActivity.getInstance(this, mData));
                   }else if(orderType.equals("请假")){
                       startActivity(ModifyLeaveActivity.getInstance(this,mData));
                   }else {
                       startActivity(ModifyCommonActivity.getInstance(this,mData));
                   }
                }else {
                    //撤回
                    LoadingView.showProgress(this);
                    mPresent.deleteOa(mId);
                }
                break;

        }

    }
}
