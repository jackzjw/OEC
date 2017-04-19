package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.AttchmentBean;
import shangchuan.com.oec.model.bean.MySelfInfo;
import shangchuan.com.oec.model.bean.WorkReportDetailsBean;
import shangchuan.com.oec.present.WorkReportDetailPresent;
import shangchuan.com.oec.present.contact.WorkReportDeatailsContract;
import shangchuan.com.oec.ui.apply.adapter.RemarkAdapter;
import shangchuan.com.oec.ui.apply.adapter.ScanImgAdapter;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CircleImageView;
import shangchuan.com.oec.widget.LoadingView;

public class WorkReportDetailActivity extends BaseActivity<WorkReportDetailPresent> implements WorkReportDeatailsContract.View {
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
    private int mType;

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
        mToolbartRight.setText("撤回");
        initToolBar(mToolbar);
        Glides.getInstance().loadCircle(this, MySelfInfo.getInstance().getAvatar(),mUserAvater);
        int id=getIntent().getIntExtra("id",-1);
        mType=getIntent().getIntExtra("type",0);
        LoadingView.showProgress(this);
        mPresent.getWrDetails(id);
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
        mTitle.setText(bean.getReportTitle());
        mDate.setText(bean.getCreateTime());
        mStartTime.setText(bean.getStartDate());
        mEndTime.setText(bean.getEndDate());
        mContent.setText(bean.getReportContent());
        mNextPlan.setText(bean.getReportPlan());
        mRecomend.setText(bean.getReportQuestion());
        RemarkAdapter remarkAdapter = new RemarkAdapter(this, bean.getProcessList());
        ProcessRec.setLayoutManager(new LinearLayoutManager(this));
        ProcessRec.setAdapter(remarkAdapter);


    }

    @Override
    public void showImgUrls(final List<String> urls) {
      ImgRec.setLayoutManager(new GridLayoutManager(this,4));
        ScanImgAdapter adapter = new ScanImgAdapter(this, urls);
        ImgRec.setAdapter(adapter);
        adapter.setItemClickListener(new ScanImgAdapter.OnItemClickListener() {
            @Override
            public void imgClick(int position, int mediaType) {
                if(mediaType== Constants.VEDIO_TYPE){
                    Intent openVideo = new Intent(Intent.ACTION_VIEW);
                    openVideo.setDataAndType(Uri.parse(urls.get(position)), "video/*");
                    startActivity(openVideo);
                }else if(mediaType==Constants.IMAGE_TYPE){
                     startActivity(ViewPagerActivity.getInstance(WorkReportDetailActivity.this,(ArrayList<String>)urls,position));
                }
            }
        });
    }

    @Override
    public void showFileResourse(List<AttchmentBean> beanList) {

    }
}
