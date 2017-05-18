package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.ClassListBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.ResultListBean;
import shangchuan.com.oec.model.bean.TaskListBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.ProjectTaskContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/5/17.
 */

public class ProjectTaskPresent extends RxPresent<ProjectTaskContract.View> implements ProjectTaskContract.Present {
   private RetrofitHelper mHelper;
    private List<TaskListBean> mFinishedList=new ArrayList<>();
     @Inject
    public ProjectTaskPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }
    @Override
    public void getTaskList(String id) {
        Subscription subscription=mHelper.getApiSevice().getProjectTaskList(id, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ResultListBean<ClassListBean>>>scheduleRxHelper())
                .compose(RxUtil.<ResultListBean<ClassListBean>>handleResult()).subscribe(new CommonSubscriber<ResultListBean<ClassListBean>>(mView) {
                    @Override
                    public void onNext(ResultListBean<ClassListBean> bean) {
                        LogUtil.i(bean.getResult().toString());
                        for(ClassListBean item1:bean.getResult()){
                            Iterator<TaskListBean> it = item1.getTask_list().iterator();
                            while (it.hasNext()){
                                TaskListBean bean1=it.next();
                                if(bean1.getTaskStatus()==3){
                                    mFinishedList.add(bean1);
                                    it.remove();
                                }
                            }
                        }
                         mView.showTaskList(bean.getResult());
                         mView.showFinishTaskList(mFinishedList);
                    }
                });
        add(subscription);
    }
}
