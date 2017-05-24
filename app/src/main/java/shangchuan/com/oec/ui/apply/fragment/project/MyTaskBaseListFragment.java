package shangchuan.com.oec.ui.apply.fragment.project;

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
import shangchuan.com.oec.model.bean.TaskListBean;
import shangchuan.com.oec.present.TaskListPresent;
import shangchuan.com.oec.present.contact.TaskListContract;
import shangchuan.com.oec.ui.apply.adapter.TaskListAdapter;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

/**
 * Created by sg280 on 2017/5/3.
 */

public class MyTaskBaseListFragment extends BaseFragment<TaskListPresent> implements TaskListContract.View,View.OnClickListener {

    @BindView(R.id.today_recycleview)
    RecyclerView mTodayRec;
    @BindView(R.id.future_recycleview)
    RecyclerView mFutureRec;
    @BindView(R.id.rel_chakan)
    RelativeLayout mRelFinishedTask;
    @BindView(R.id.finish_recycleview)
    RecyclerView mFinishRec;
    @BindView(R.id.today_empty)
    TextView mTodayEmpty;
    @BindView(R.id.future_empty)
    TextView mFutureEmpty;
    @BindView(R.id.img_arrow)
    ImageView mArrow;
    @BindView(R.id.finish_empty)
    TextView mFinishEmpty;
    @BindView(R.id.text_show_hide)
    TextView mShowText;
    @BindView(R.id.rel_call)
    RelativeLayout relCall;
    protected int mStatus=0;
    protected String userId="0";
    protected int finishStatus=1;
    protected String tel;
    private boolean isShow;
    @Override
    public void loadData() {
        if(!isPrepared||!isVisible){
            return;
        }
       mRelFinishedTask.setOnClickListener(this);
        LogUtil.i(tel);
        if(tel!=null&&mStatus==0){
            relCall.setVisibility(View.VISIBLE);
        }
        LoadingView.showProgress(mActivity);
        mPresent.getTaskList(mStatus,userId);

    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_my_response;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
             LoadingView.dismissProgress();
             ToastUtil.shortShow(msg);
    }


    @Override
    public void showFinishedTask(List<TaskListBean> bean) {
        LoadingView.dismissProgress();
        if(bean.isEmpty()){
            mFinishEmpty.setVisibility(View.VISIBLE);
            return;
        }
        mFinishRec.setLayoutManager(new LinearLayoutManager(mActivity));
        mFinishRec.addItemDecoration(new DividerDecoration(mActivity));
        TaskListAdapter finishedAdapter = new TaskListAdapter(mActivity, bean,2);
        mFinishRec.setAdapter(finishedAdapter);
    }

    @Override
    public void showTodayTask(List<TaskListBean> bean) {
        LoadingView.dismissProgress();
        if(bean.isEmpty()){
            mTodayEmpty.setVisibility(View.VISIBLE);
            return;
        }
        mTodayRec.setLayoutManager(new LinearLayoutManager(mActivity));
        mTodayRec.addItemDecoration(new DividerDecoration(mActivity));
        TaskListAdapter todayAdapter = new TaskListAdapter(mActivity, bean,0);
        mTodayRec.setAdapter(todayAdapter);
    }

    @Override
    public void showFutureTask(List<TaskListBean> bean) {
        LoadingView.dismissProgress();
        if(bean.isEmpty()){
            mFutureEmpty.setVisibility(View.VISIBLE);
            return;
        }
        mFutureRec.setLayoutManager(new LinearLayoutManager(mActivity));
        mFutureRec.addItemDecoration(new DividerDecoration(mActivity));
        TaskListAdapter todayAdapter = new TaskListAdapter(mActivity, bean,1);
        mTodayRec.setAdapter(todayAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rel_chakan:
                isShow=!isShow;
                    LoadingView.showProgress(mActivity);
                    mPresent.getFinishedTask(finishStatus);
                mFinishRec.setVisibility(isShow?View.VISIBLE:View.GONE);
                mShowText.setText(isShow?"隐藏已完成任务":"查看已完成任务");
                mArrow.setImageResource(isShow?R.drawable.project_icon_below_arrow:R.drawable.home_news_arrow);

                break;
        }
    }
    @OnClick(R.id.call_phone)
    void call(){
        CommonUtil.callPhone(mActivity,tel);
    }

}
