package shangchuan.com.oec.ui.apply.fragment.project;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.model.bean.TaskListBean;
import shangchuan.com.oec.present.TaskListPresent;
import shangchuan.com.oec.present.contact.TaskListContract;
import shangchuan.com.oec.ui.apply.adapter.TaskListAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

/**
 * Created by sg280 on 2017/5/3.
 */

public class MyResponseFragment extends BaseFragment<TaskListPresent> implements TaskListContract.View,View.OnClickListener {

    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.rel_chakan)
    RelativeLayout mRelFinishedTask;
    @BindView(R.id.finish_recycleview)
    RecyclerView mFinishRec;
    protected int status=0;
    private boolean isShow;
    @Override
    public void loadData() {
       mRelFinishedTask.setOnClickListener(this);
        LoadingView.showProgress(mActivity);
        mPresent.getTaskList(status);

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
    public void showUnfinishTask(List<TaskListBean> bean) {
        if(bean.isEmpty()) return;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
           mRecyclerView.addItemDecoration(new DividerDecoration(mActivity));
         TaskListAdapter unFinishAdapter = new TaskListAdapter(mActivity, bean);
          mRecyclerView.setAdapter(unFinishAdapter);
    }

    @Override
    public void showFinishedTask(List<TaskListBean> bean) {
        if(bean.isEmpty()) return;
        mFinishRec.setLayoutManager(new LinearLayoutManager(mActivity));
        mFinishRec.addItemDecoration(new DividerDecoration(mActivity));
        TaskListAdapter finishedAdapter = new TaskListAdapter(mActivity, bean);
        mFinishRec.setAdapter(finishedAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rel_chakan:
                isShow=!isShow;
                mFinishRec.setVisibility(isShow?View.VISIBLE:View.GONE);


                break;
        }
    }
}
