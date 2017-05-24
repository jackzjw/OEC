package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.ProjectListBean;
import shangchuan.com.oec.model.bean.TaskListBean;

/**
 * Created by sg280 on 2017/5/5.
 */

public interface ProjectListContract {
    interface View extends BaseView{
        void showContent(List<ProjectListBean> bean);
        void showTodayTask(List<TaskListBean> bean);
    }
    interface Present extends BasePresent<View>{
        void getProjectList(int status);
        void getTaskList(int status,String userid);
    }
}
