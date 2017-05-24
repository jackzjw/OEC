package shangchuan.com.oec.ui.apply.fragment.project;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import rx.Subscription;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.SimpleFragment;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.ProjectProcessBlockList;
import shangchuan.com.oec.model.bean.ResultBean;
import shangchuan.com.oec.ui.apply.adapter.ProjectTrendAdapter;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/5/4.
 */

public class ProjectTrendFragment extends SimpleFragment {

    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    public static ProjectTrendFragment getInstance(String id){
        ProjectTrendFragment fragment=new ProjectTrendFragment();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.shortShow(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_trend;
    }

    @Override
    protected void initEventAndData() {
    LoadingView.showProgress(mActivity);
        String id=getArguments().getString("id");
       Subscription subscription= getActivityComponent().getHttpHelper().getApiSevice().getProjectTrends("5",id, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ResultBean<ProjectProcessBlockList>>>scheduleRxHelper())
                .compose(RxUtil.<ResultBean<ProjectProcessBlockList>>handleResult()).subscribe(new CommonSubscriber<ResultBean<ProjectProcessBlockList>>(this) {
            @Override
            public void onNext(ResultBean<ProjectProcessBlockList> bean) {
                LoadingView.dismissProgress();
                mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                mRecyclerView.setAdapter(new ProjectTrendAdapter(mActivity,bean.getResult().getProjectProcessBlockList()));

            }
        });
        add(subscription);

    }
}
