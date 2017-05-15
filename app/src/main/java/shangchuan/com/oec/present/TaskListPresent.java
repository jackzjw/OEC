package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.ResultBean;
import shangchuan.com.oec.model.bean.TaskListBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.TaskListContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/5/5.
 */

public class TaskListPresent extends RxPresent<TaskListContract.View> implements TaskListContract.Present {
  private RetrofitHelper mHelper;
    @Inject
    public TaskListPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }
    @Override
    public void getTaskList(int status) {
        Subscription subscription=mHelper.getApiSevice().getTaskList(status,0,"0",99, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ResultBean<OaBasicItemBean<TaskListBean>>>>scheduleRxHelper())
                .compose(RxUtil.<ResultBean<OaBasicItemBean<TaskListBean>>>handleResult()).subscribe(new CommonSubscriber<ResultBean<OaBasicItemBean<TaskListBean>>>(mView) {
                    @Override
                    public void onNext(ResultBean<OaBasicItemBean<TaskListBean>> bean) {
                        List<TaskListBean> todayList=new ArrayList<TaskListBean>();
                        List<TaskListBean> futureList=new ArrayList<TaskListBean>();

                        for(TaskListBean item:bean.getResult().getItems()){
                            if(item.getTodayOrFuture().equals("0")){
                                //今天
                                todayList.add(item);
                            }else if(item.getTodayOrFuture().equals("1")){
                                //未来
                                futureList.add(item);
                            }
                        }
                        mView.showTodayTask(todayList);
                        mView.showFutureTask(futureList);
                    }
                });
        add(subscription);
    }

    @Override
    public void getFinishedTask(int status) {
        //已完成
        Subscription subscription=mHelper.getApiSevice().getTaskList(status,0,"0",99, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ResultBean<OaBasicItemBean<TaskListBean>>>>scheduleRxHelper())
                .compose(RxUtil.<ResultBean<OaBasicItemBean<TaskListBean>>>handleResult()).subscribe(new CommonSubscriber<ResultBean<OaBasicItemBean<TaskListBean>>>(mView) {
                    @Override
                    public void onNext(ResultBean<OaBasicItemBean<TaskListBean>> bean) {
                        mView.showFinishedTask(bean.getResult().getItems());
                    }
                });
                add(subscription);
    }
}
