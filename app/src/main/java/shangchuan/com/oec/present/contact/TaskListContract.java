package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.TaskListBean;

/**
 * Created by sg280 on 2017/5/5.
 */

public interface TaskListContract {
    interface View extends BaseView{
        void showUnfinishTask(List<TaskListBean> bean);
        void showFinishedTask(List<TaskListBean> bean);

    }
    interface Present extends BasePresent<View>{
        void getTaskList(int status);
    }
}
