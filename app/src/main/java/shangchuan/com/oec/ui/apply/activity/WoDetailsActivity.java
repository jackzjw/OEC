package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
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
import shangchuan.com.oec.model.bean.ProcessListBean;
import shangchuan.com.oec.model.bean.SelectOwnerBean;
import shangchuan.com.oec.model.bean.WoDetailBean;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.model.event.DealEvent;
import shangchuan.com.oec.present.WoDetailPresent;
import shangchuan.com.oec.present.contact.WoDetailContract;
import shangchuan.com.oec.ui.apply.adapter.DocumentAdapter;
import shangchuan.com.oec.ui.apply.adapter.RemarkAdapter;
import shangchuan.com.oec.ui.apply.adapter.ScanImgAdapter;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CircleImageView;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

public class WoDetailsActivity extends BaseActivity<WoDetailPresent> implements WoDetailContract.View,View.OnClickListener {
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
    @BindView(R.id.rel_first)
    RelativeLayout mRelSend;
    @BindView(R.id.approve_pass)
    TextView mTvSend;
    @BindView(R.id.et_msg)
    EditText mInputMsg;
    @BindView(R.id.rel_second)
    RelativeLayout mRelFinish;
    @BindView(R.id.approve_turn_down)
    TextView mTvFinish;
    @BindView(R.id.rel_third)
    RelativeLayout mRelToOther;
    @BindView(R.id.approve_to_other)
    TextView mTvToOther;
    @BindView(R.id.rel_deal_result)
    LinearLayout mDealResult;
    private int dealType;
    private WoDetailBean mData;
    private int mId;
    private RemarkAdapter remarkAdapter;
    private List<ProcessListBean> remarkList=new ArrayList<>();
    private int mPos;

    public static Intent getInstance(Context context,int id,int position){
        Intent intent=new Intent(context,WoDetailsActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("position",position);
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

        initToolBar(mToolbar);
        mTvSend.setOnClickListener(this);
        mTvFinish.setOnClickListener(this);
        mTvToOther.setOnClickListener(this);
        mToolbartRight.setOnClickListener(this);
        mId=getIntent().getIntExtra("id",-1);
        mPos=getIntent().getIntExtra("position",-1);
        LoadingView.showProgress(this);
        mPresent.getWoDetail(mId);
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
    public void showContent(WoDetailBean bean) {
        this.mData=bean;
        mTvSend.setText("发消息");
        mTvFinish.setText("完成");
        //dealStatus=0可以转他人和待处理和发消息,1只能发消息
        if(bean.getReStatus()==1) {
            mToolbartRight.setText("撤回");
            mRelSend.setVisibility(View.VISIBLE);
        }else if(bean.getDealStatus()==1){
            mRelSend.setVisibility(View.VISIBLE);
        }
        else if(bean.getReStatus()==2) {
            mToolbartRight.setText("重新提交");
            mDealResult.setVisibility(View.GONE);
        }else {
            mRelFinish.setVisibility(View.VISIBLE);
            mRelToOther.setVisibility(View.VISIBLE);
            mRelSend.setVisibility(View.VISIBLE);
        }

        Glides.getInstance().loadCircle(this,bean.getCreateAvatar(),mUserAvater);
        mUserTitle.setText(bean.getCreateUserName()+"创建的工单");
        mSubmitDate.setText(bean.getCreateTime());
        mOrderNum.setText("工单号："+bean.getOrderNo());
        mSubmitType.setText(bean.getClassNameA()+"/"+bean.getClassNameB());
        mFlag.setImageResource(CommonUtil.orderFlag(bean.getOrderFlag()));
        mOrderStatus.setText(CommonUtil.woStatus(bean.getOrderStatus()));
        mWoTitle.setText(bean.getOrderTitle());
        mContent.setText(bean.getOrderContent());
        if(bean.getOrderStatus()==0||bean.getOrderStatus()==3){//已撤销
            mOrderStatus.setTextColor(ContextCompat.getColor(this,R.color.wo_status_cancle_color));
        }else if(bean.getOrderStatus()==1){//待处理
            mOrderStatus.setTextColor(ContextCompat.getColor(this,R.color.wo_status_wait_deal_color));

        }else{//处理中
            mOrderStatus.setTextColor(ContextCompat.getColor(this,R.color.wo_status_dealing_color));
        }

    }

    @Override
    public void showMedias(final List<AttchmentBean> medias) {
        mPicRec.setLayoutManager(new GridLayoutManager(this,4));
        ScanImgAdapter adapter = new ScanImgAdapter(this, medias);
        mPicRec.setAdapter(adapter);
        adapter.setItemClickListener(new ScanImgAdapter.OnItemClickListener() {
            @Override
            public void imgClick(int position, int mediaType) {
                if(mediaType== Constants.IMAGE_TYPE){
                    startActivity(ViewPagerActivity.getInstance(WoDetailsActivity.this,(ArrayList<AttchmentBean>) medias,position));
                }else if(mediaType==Constants.VEDIO_TYPE){
                    mPresent.downLoadFile(medias.get(position).getUrl());
                }
            }
        });

    }

    @Override
    public void showTexts(final List<AttchmentBean> texts) {
        DocumentAdapter documentAdapter=new DocumentAdapter(this,texts);
        mDocRec.setLayoutManager(new LinearLayoutManager(this));
        mDocRec.addItemDecoration(new DividerDecoration(this));
        mDocRec.setAdapter(documentAdapter);
        documentAdapter.setOnItemClickListener(new DocumentAdapter.OnItemClickListener() {
            @Override
            public void ItemClick(int position) {
                LoadingView.showProgress(WoDetailsActivity.this);
                mPresent.downLoadFile(texts.get(position).getUrl());
            }
        });
    }

    @Override
    public void openFile(String filePath) {
        LoadingView.dismissProgress();
        startActivity(CommonUtil.openFile(filePath));
    }

    @Override
    public void showRemark(List<ProcessListBean> remarks) {
        remarkList=remarks;
        LoadingView.dismissProgress();
        mRemarkRec.setLayoutManager(new LinearLayoutManager(this));
        remarkAdapter=new RemarkAdapter(this,remarks);
        mRemarkRec.setAdapter(remarkAdapter);
    }

    @Override
    public void dealSuccess(WoSuccessBean bean) {
         LoadingView.dismissProgress();
          if(dealType==1){
              //发消息成功
              ProcessListBean item=new ProcessListBean();
              item.setRemark(msg());
              item.setProcessResult(1);
              remarkList.add(item);
              remarkAdapter.notifyItemInserted(remarkList.size()-1);
              mTvSend.setText("");
          }else if(dealType==2){//完成
              ToastUtil.shortShow("操作成功");
              RxBus.getDefault().post(new DealEvent(2,mPos));
              finish();
          }else {
              ToastUtil.shortShow("操作成功");
              finish();
          }


    }

    @Override
    public void delSuccess() {
        LoadingView.dismissProgress();
        ToastUtil.shortShow("撤销成功");
        RxBus.getDefault().post(new DealEvent(0,mPos));
        finish();
    }

    private String msg(){
        return mInputMsg.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //发消息:1
            case R.id.approve_pass:
             if(TextUtils.isEmpty(msg())) {
                 ToastUtil.shortShow("请输入消息");
                 return;
             }
                LoadingView.showProgress(this);
                mPresent.dealWoResult(1,mId,msg(),"");
                dealType=1;
                break;
            //完成:2
            case R.id.approve_turn_down:
                LoadingView.showProgress(this);
                mPresent.dealWoResult(2,mId,"","");
                dealType=2;
                break;
            //转他人处理:3
            case R.id.approve_to_other:
                if(TextUtils.isEmpty(msg())) {
                    ToastUtil.shortShow("请输入消息");
                    return;
                }

                  startActivityForResult(new Intent(this,ApproverActivity.class),Constants.REQUEST_CODE);
                break;
            case R.id.toolbar_right_title:
                if(mData==null) break;
                if(mData.getReStatus()==1){
                    LoadingView.showProgress(this);
                    mPresent.delWorkOrder(mId);
                }else if(mData.getReStatus()==2){
                    //重新提交工单
                     startActivity(ModifyWoDetailActivity.getInstance(this,mData));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constants.REQUEST_CODE&&resultCode==RESULT_OK){
            ArrayList<SelectOwnerBean> ownerList = (ArrayList<SelectOwnerBean>) data.getSerializableExtra("ownerlist");
              if(ownerList.size()>1){
                  ToastUtil.shortShow("只能转一人处理,请重新选择");
                  return;
              }
            if(ownerList.size()==0){
                ToastUtil.shortShow("请选择处理人");
                return;
            }
            LoadingView.showProgress(this);
            dealType=3;
            mPresent.dealWoResult(3,mId,msg(),ownerList.get(0).getId()+"");
        }

    }
}
