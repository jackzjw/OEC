package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.ProjectListBean;
import shangchuan.com.oec.model.bean.ResultBean;
import shangchuan.com.oec.model.bean.ResultListBean;
import shangchuan.com.oec.model.bean.TaskListBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.ProjectListContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/5/5.
 */

public class ProjectListPresent extends RxPresent<ProjectListContract.View> implements ProjectListContract.Present {
    private RetrofitHelper mHelper;
    @Inject
    public ProjectListPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }



    @Override
    public void getProjectList(int status) {
        Subscription subscription=mHelper.getApiSevice().projectList(status, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ResultListBean<ProjectListBean>>>scheduleRxHelper())
                .compose(RxUtil.<ResultListBean<ProjectListBean>>handleResult()).subscribe(new CommonSubscriber<ResultListBean<ProjectListBean>>(mView) {
                    @Override
                    public void onNext(ResultListBean<ProjectListBean> bean) {
                        mView.showContent(bean.getResult());
                    }
                });
        add(subscription);
    }

    @Override
    public void getTaskList(int status, String userid) {
        Subscription subscription=mHelper.getApiSevice().getTaskList(status,0,userid,99, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ResultBean<OaBasicItemBean<TaskListBean>>>>scheduleRxHelper())
                .compose(RxUtil.<ResultBean<OaBasicItemBean<TaskListBean>>>handleResult()).subscribe(new CommonSubscriber<ResultBean<OaBasicItemBean<TaskListBean>>>(mView) {
                    @Override
                    public void onNext(ResultBean<OaBasicItemBean<TaskListBean>> bean) {
                        List<TaskListBean> todayList=new ArrayList<TaskListBean>();

                        for(TaskListBean item:bean.getResult().getItems()){
                            if(item.getTodayOrFuture().equals("0")){
                                //今天
                                todayList.add(item);
                            }
                        }

                        mView.showTodayTask(todayList);
                    }
                });
        add(subscription);
    }
}
