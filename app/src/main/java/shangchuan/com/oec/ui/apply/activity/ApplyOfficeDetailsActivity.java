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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.AttchmentBean;
import shangchuan.com.oec.model.bean.OaDetailsBean;
import shangchuan.com.oec.model.bean.ProcessListBean;
import shangchuan.com.oec.model.bean.SelectOwnerBean;
import shangchuan.com.oec.present.OaDetailsPresent;
import shangchuan.com.oec.present.contact.OaDetailsContract;
import shangchuan.com.oec.ui.apply.adapter.DocumentAdapter;
import shangchuan.com.oec.ui.apply.adapter.ScanImgAdapter;
import shangchuan.com.oec.ui.apply.adapter.WrRemarkAdapter;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.Glides;
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
    @BindView(R.id.approve_to_other)
    TextView mTvPass;
    @BindView(R.id.approve_turn_down)
    TextView mTvReject;
    @BindView(R.id.approve_to_other)
    TextView mToOther;
    @BindView(R.id.rel_deal_result)
    RelativeLayout mRelDealResult;
    @BindView(R.id.et_msg)
    EditText mInputMsg;
    private int mId;

    //index用来确认是从审核列表跳过来的还是从我的申请列表跳过来的；index=1表示从申请列表跳过来，2：审核列表
public static Intent newIntent(Context context,int id,int index){
    Intent intent =new Intent(context,ApplyOfficeDetailsActivity.class);
    intent.putExtra("id",id);
    intent.putExtra("index",index);
    return  intent;
}


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_apply_office_details;
    }

    @Override
    protected void initEventData() {
        if(getIntent().getIntExtra("index",0)==1){
            mRelDealResult.setVisibility(View.GONE);
        }
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbartRight.setText("撤回");
        initToolBar(mToolbar);
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
        LoadingView.dismissProgress();
        mToolbarTitle.setText(bean.getOrderType()+"详情");
        Glides.getInstance().load(this, SharePreferenceUtil.getUserAvater(),mUserAvater);
        mOrderTitle.setText(bean.getOrderTitle());
        mSubmitTime.setText(bean.getOrderTime());
        mStratTime.setText(bean.getStartTime());
        mEndTime.setText(bean.getEndTime());
        mDuration.setText(bean.getOrderPeriod());
        mStatus.setText(CommonUtil.orderStatus(bean.getOrderStatus()));
        //2：已通过 3：已驳回
        if(bean.getOrderStatus()==2){
             imgStatus.setImageResource(R.drawable.application_img_pass);
        }else if(bean.getOrderStatus()==3){
            imgStatus.setImageResource(R.drawable.application_img_reject);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new WrRemarkAdapter(this,bean));

    }

    @Override
    public void dealSuccess() {
           LoadingView.dismissProgress();
        ToastUtil.shortShow("处理成功");
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
            mPresent.dealOaCheck(mId,3,msg(),ownerList.get(0).getId());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.approve_pass:
                 LoadingView.showProgress(this);
                mPresent.dealOaCheck(mId,1,msg(),0);
                break;
            case R.id.approve_turn_down:
                if(TextUtils.isEmpty(msg())){
                    ToastUtil.shortShow("请填写审批意见");
                    return;
                }
                 LoadingView.showProgress(this);
                mPresent.dealOaCheck(mId,2,msg(),0);
                break;
            case R.id.approve_to_other:
                if(TextUtils.isEmpty(msg())){
                    ToastUtil.shortShow("请填写审批意见");
                    return;
                }
                  startActivityForResult(new Intent(this,ApproverActivity.class),Constants.REQUEST_CODE);
                break;


        }

    }
}
