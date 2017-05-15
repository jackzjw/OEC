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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.AttchmentBean;
import shangchuan.com.oec.model.bean.MySelfInfo;
import shangchuan.com.oec.model.bean.ProcessListBean;
import shangchuan.com.oec.model.bean.SelectOwnerBean;
import shangchuan.com.oec.model.bean.WorkReportDetailsBean;
import shangchuan.com.oec.present.WorkReportDetailPresent;
import shangchuan.com.oec.present.contact.WorkReportDeatailsContract;
import shangchuan.com.oec.ui.apply.adapter.DocumentAdapter;
import shangchuan.com.oec.ui.apply.adapter.ScanImgAdapter;
import shangchuan.com.oec.ui.apply.adapter.WrRemarkAdapter;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CircleImageView;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

public class WorkReportDetailActivity extends BaseActivity<WorkReportDetailPresent> implements WorkReportDeatailsContract.View,View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar_right_title)
    TextView mToolbartRight;
    @BindView(R.id.user_avatar)
    CircleImageView mUserAvater;
    @BindView(R.id.user_report_type)
    TextView mTitle;
    @BindView(R.id.submit_time)
    TextView mDate;
    @BindView(R.id.submit_start_time)
    TextView mStartTime;
    @BindView(R.id.submit_end_time)
    TextView mEndTime;
    @BindView(R.id.current_content)
    TextView mContent;
    @BindView(R.id.tv_next_plan)
    TextView mNextPlan;
    @BindView(R.id.img_recycleview)
    RecyclerView ImgRec;
    @BindView(R.id.doc_recycleview)
    RecyclerView DocRec;
    @BindView(R.id.tv_question_recommend)
    TextView mRecomend;
    @BindView(R.id.process_recycleview)
    RecyclerView ProcessRec;
     @BindView(R.id.rel_first)
    RelativeLayout mRelSendMsg;
    @BindView(R.id.et_msg)
    EditText Msg;
    @BindView(R.id.approve_pass)
    TextView mSendMsg;
    @BindView(R.id.approve_to_other)
    TextView mToOther;
    @BindView(R.id.rel_third)
    RelativeLayout mRelToOther;
    private int mType;
    private int mId;
    private WrRemarkAdapter remarkAdapter;
    private List<ProcessListBean> remarkList=new ArrayList<>();
    private int dealType;
    private ArrayList<SelectOwnerBean> ownerList;
    private WorkReportDetailsBean mData;

    public static Intent getInstance(Context context,int id,int type){
        Intent intent=new Intent(context,WorkReportDetailActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("type",type);
        return intent;
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_work_report_detail;
    }

    @Override
    protected void initEventData() {
       mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("工作报告详情");

        initToolBar(mToolbar);
        Glides.getInstance().loadCircle(this, MySelfInfo.getInstance().getAvatar(),mUserAvater);
        mId=getIntent().getIntExtra("id",-1);
        mType=getIntent().getIntExtra("type",0);
        //type=1表示我创建的工作报告详情，type=2表示审阅详情
        if(mType==1) {
            mToolbartRight.setText("撤回");
        }else {
            mRelToOther.setVisibility(View.VISIBLE);
        }
        mRelSendMsg.setVisibility(View.VISIBLE);
        mSendMsg.setText("发消息");
        mSendMsg.setOnClickListener(this);
        mToOther.setOnClickListener(this);
        mToolbartRight.setOnClickListener(this);
        LoadingView.showProgress(this);
        mPresent.getWrDetails(mId,mType);
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
    public void showContent(WorkReportDetailsBean bean) {
        mData=bean;
        LoadingView.dismissProgress();
        mTitle.setText(bean.getReportTitle());
        mDate.setText(bean.getCreateTime());
        mStartTime.setText(bean.getStartDate());
        mEndTime.setText(bean.getEndDate());
        mContent.setText(bean.getReportContent());
        mNextPlan.setText(bean.getReportPlan());
        mRecomend.setText(bean.getReportQuestion());
        mEndTime.setText("-");
        this.remarkList=bean.getProcessList();
         remarkAdapter = new WrRemarkAdapter(this,remarkList);
        ProcessRec.setLayoutManager(new LinearLayoutManager(this));
        ProcessRec.setAdapter(remarkAdapter);


    }

    @Override
    public void showImgUrls(final List<AttchmentBean> urls) {
        LogUtil.i(urls.toString());
      ImgRec.setLayoutManager(new GridLayoutManager(this,4));
        ScanImgAdapter adapter = new ScanImgAdapter(this, urls);
        ImgRec.setAdapter(adapter);
        adapter.setItemClickListener(new ScanImgAdapter.OnItemClickListener() {
            @Override
            public void imgClick(int position, int mediaType) {
                if(mediaType== Constants.VEDIO_TYPE){
                    LoadingView.showProgress(WorkReportDetailActivity.this);
                    mPresent.downloadFile(urls.get(position).getUrl());
                }else if(mediaType==Constants.IMAGE_TYPE){
                     startActivity(ViewPagerActivity.getInstance(WorkReportDetailActivity.this,(ArrayList<AttchmentBean>)urls,position));
                }
            }
        });
    }

    @Override
    public void showFileResourse(final List<AttchmentBean> beanList) {
        LoadingView.dismissProgress();
        DocumentAdapter documentAdapter=new DocumentAdapter(this,beanList);
        DocRec.setLayoutManager(new LinearLayoutManager(this));
        DocRec.addItemDecoration(new DividerDecoration(this));
        DocRec.setAdapter(documentAdapter);
        documentAdapter.setOnItemClickListener(new DocumentAdapter.OnItemClickListener() {
            @Override
            public void ItemClick(int position) {
                LoadingView.showProgress(WorkReportDetailActivity.this);
                mPresent.downloadFile(beanList.get(position).getUrl());
            }
        });
    }

    @Override
    public void openFile(String filePath) {
        LoadingView.dismissProgress();
        startActivity(CommonUtil.openFile(filePath));
    }

    @Override
    public void dealSuccess() {
            LoadingView.dismissProgress();
            ProcessListBean item = new ProcessListBean();
            item.setRemark(msg());
            item.setUserName(MySelfInfo.getInstance().getNickName());
           SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd HH:mm:ss");
           String time=sdf.format(System.currentTimeMillis());
            item.setCreateTime(time);
            item.setProcessResult(dealType);
            if(dealType==3) item.setToUserName(ownerList.get(0).getOwnerName());
            remarkList.add(item);
            remarkAdapter.notifyItemInserted(remarkList.size() - 1);
            Msg.setText("");
    }

    @Override
    public void deleteSucc() {
        //撤回成功,跳转到修改界面
        LoadingView.dismissProgress();
        startActivity(ModifyWRDetailActivity.getInstance(this,mData));
    }

    private String msg(){
        return Msg.getText().toString().trim();
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
            LoadingView.showProgress(this);
            mPresent.oaDealResult(mId,3,msg(),ownerList.get(0).getId()+"");
            dealType=3;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.approve_pass:
                if(TextUtils.isEmpty(msg())){
                    ToastUtil.shortShow("请输入消息");
                    return;
                }
                LoadingView.showProgress(this);
                mPresent.oaDealResult(mId,2,msg(),"0");
                dealType=2;
                break;
            case R.id.approve_to_other:
                if(TextUtils.isEmpty(msg())) {
                    ToastUtil.shortShow("请输入消息");
                    return;
                }
                startActivityForResult(new Intent(this,ApproverActivity.class),Constants.REQUEST_CODE);

                break;
            case R.id.toolbar_right_title:
                //撤回
                LoadingView.showProgress(this);
                mPresent.delWR(mId);

                break;
        }

    }
}
