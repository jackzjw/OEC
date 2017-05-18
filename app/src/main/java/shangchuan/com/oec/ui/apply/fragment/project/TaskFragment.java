package shangchuan.com.oec.ui.apply.fragment.project;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.model.bean.ClassListBean;
import shangchuan.com.oec.model.bean.TaskListBean;
import shangchuan.com.oec.present.ProjectTaskPresent;
import shangchuan.com.oec.present.contact.ProjectTaskContract;
import shangchuan.com.oec.ui.apply.activity.AddTaskClassifyActivity;
import shangchuan.com.oec.ui.apply.adapter.ProjectTaskAdapter;
import shangchuan.com.oec.ui.apply.adapter.TaskListAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

/**
 * Created by sg280 on 2017/5/4.
 */

public class TaskFragment extends BaseFragment<ProjectTaskPresent> implements ProjectTaskContract.View,View.OnClickListener {
    @BindView(R.id.add_task_class)
    TextView mAddTaskClass;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.find_finished_task)
    TextView mFinishText;
     @BindView(R.id.img_arrow)
    ImageView mArrow;
    @BindView(R.id.finish_recycleview)
    RecyclerView mFinishRec;
    @BindView(R.id.rel_chakan)
    LinearLayout mLinearChaKan;
    private String projectId;
    private boolean isShow;
    public static TaskFragment getInstance(String id){
        TaskFragment fragment=new TaskFragment();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void loadData() {
        if(!isPrepared||!isVisible){
            return;
        }
        mLinearChaKan.setOnClickListener(this);
        mAddTaskClass.setOnClickListener(this);
         projectId=getArguments().getString("id");


    }

    @Override
    public void onResume() {
        super.onResume();
        LoadingView.showProgress(mActivity);
        mPresent.getTaskList(projectId);
    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_task;
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
    public void showTaskList(List<ClassListBean> bean) {
        LoadingView.dismissProgress();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerDecoration(mActivity));
        ProjectTaskAdapter adapter=new ProjectTaskAdapter(mActivity,bean);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void showFinishTaskList(List<TaskListBean> bean) {
        TaskListAdapter taskListAdapter=new TaskListAdapter(mActivity,bean,2);
        mFinishRec.setLayoutManager(new LinearLayoutManager(mActivity));
        mFinishRec.addItemDecoration(new DividerDecoration(mActivity));
        mFinishRec.setAdapter(taskListAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rel_chakan:
                isShow=!isShow;
                mFinishRec.setVisibility(isShow?View.VISIBLE:View.GONE);
                mFinishText.setText(isShow?"隐藏已完成任务":"查看已完成任务");
                mArrow.setImageResource(isShow?R.drawable.project_icon_below_arrow:R.drawable.home_news_arrow);
                break;
            case R.id.add_task_class:
                startActivity(AddTaskClassifyActivity.getInstance(mActivity,projectId));
                break;
        }
    }
}
