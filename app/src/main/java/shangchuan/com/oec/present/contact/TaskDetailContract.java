package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.CommentResultBean;
import shangchuan.com.oec.model.bean.TaskCommentBean;
import shangchuan.com.oec.model.bean.TaskDetailsBean;

/**
 * Created by sg280 on 2017/5/15.
 */

public interface TaskDetailContract {
    interface View extends BaseView{
        void showContent(TaskDetailsBean bean);
        void showRemark(List<TaskCommentBean> commentBeanList);
        void remarkSucc(CommentResultBean result);
        void finishTaskSucc();
    }
    interface Present extends BasePresent<View>{
        void getTaskDetails(String id);
        void sendRemark(String taskId,String Pid,String toUserId,String remark);
        void finishTask(String taskId);
    }

}
