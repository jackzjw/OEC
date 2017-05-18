package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.ClassListBean;
import shangchuan.com.oec.model.bean.TaskListBean;

/**
 * Created by sg280 on 2017/5/17.
 */

public interface ProjectTaskContract {
    interface View extends BaseView{

        void showTaskList(List<ClassListBean> list );
        void showFinishTaskList(List<TaskListBean> bean);

    }
    interface  Present extends BasePresent<View>{
        void getTaskList(String id);
    }
}
