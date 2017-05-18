package shangchuan.com.oec.present;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.CommentResultBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.ResultBean;
import shangchuan.com.oec.model.bean.TaskDetailsBean;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.TaskDetailContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/5/15.
 */

public class TaskDetailPresent extends RxPresent<TaskDetailContract.View> implements TaskDetailContract.Present {
   private RetrofitHelper mHelper;
    @Inject
    public TaskDetailPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }
    @Override
    public void getTaskDetails(String id) {
        Subscription subscription=mHelper.getApiSevice().getTaskDetails(id, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ResultBean<TaskDetailsBean>>>scheduleRxHelper())
                .compose(RxUtil.<ResultBean<TaskDetailsBean>>handleResult()).subscribe(new CommonSubscriber<ResultBean<TaskDetailsBean>>(mView) {
                    @Override
                    public void onNext(ResultBean<TaskDetailsBean> bean) {
                        LogUtil.i(bean.getResult().toString());
                        mView.showContent(bean.getResult());
                        mView.showRemark(bean.getResult().getComment_list());
                    }
                });
        add(subscription);
    }

    @Override
    public void sendRemark(String taskId, String Pid, String toUserId, String remark) {
            Subscription subscription=mHelper.getApiSevice().submitRemark(taskId,Pid,toUserId,remark,SaveToken.mToken)
                    .compose(RxUtil.<HttpDataResult<ResultBean<CommentResultBean>>>scheduleRxHelper())
                    .compose(RxUtil.<ResultBean<CommentResultBean>>handleResult()).subscribe(new CommonSubscriber<ResultBean<CommentResultBean>>(mView) {
                        @Override
                        public void onNext(ResultBean<CommentResultBean> bean) {
                             mView.remarkSucc(bean.getResult());
                        }
                    });
        add(subscription);
    }

    @Override
    public void finishTask(String taskId) {
        Subscription subscription=mHelper.getApiSevice().finishTask(taskId,SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ResultBean<WoSuccessBean>>>scheduleRxHelper())
                .compose(RxUtil.<ResultBean<WoSuccessBean>>handleResult()).subscribe(new CommonSubscriber<ResultBean<WoSuccessBean>>(mView) {
                    @Override
                    public void onNext(ResultBean<WoSuccessBean> bean) {
                        mView.finishTaskSucc();
                    }
                });
        add(subscription);
    }


}
