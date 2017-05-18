package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/5/17.
 */

public class ClassListBean {

    private String Id ;
    private String ProjectId ;
    private String ClassName ;
    private String ClassDesc ;
    private String  OrderBy;
    private ArrayList<TaskListBean> task_list;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getClassDesc() {
        return ClassDesc;
    }

    public void setClassDesc(String classDesc) {
        ClassDesc = classDesc;
    }

    public String getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(String orderBy) {
        OrderBy = orderBy;
    }

    public ArrayList<TaskListBean> getTask_list() {
        return task_list;
    }

    public void setTask_list(ArrayList<TaskListBean> task_list) {
        this.task_list = task_list;
    }

    @Override
    public String toString() {
        return "ClassListBean{" +
                "Id='" + Id + '\'' +
                ", ProjectId='" + ProjectId + '\'' +
                ", ClassName='" + ClassName + '\'' +
                ", ClassDesc='" + ClassDesc + '\'' +
                ", OrderBy='" + OrderBy + '\'' +
                ", task_list=" + task_list +
                '}';
    }
}
