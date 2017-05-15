package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/5/5.
 */

public class TaskListBean {
    private int   Id ;
    private int ProjectId    ;
    private String  TaskTitle  ;
    private int TaskStatus    ;
    private int UserID    ;
    private String  UserName  ;
    private String   Deadline ;
    private String  ProjectName  ;
    private int TaskClassId    ;
    private String  ClassName  ;
    private String  CreateUserID    ;
    private String CreateTime;
    private String TodayOrFuture;

    public String getTodayOrFuture() {
        return TodayOrFuture;
    }

    public void setTodayOrFuture(String todayOrFuture) {
        TodayOrFuture = todayOrFuture;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public String getTaskTitle() {
        return TaskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        TaskTitle = taskTitle;
    }

    public int getTaskStatus() {
        return TaskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        TaskStatus = taskStatus;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public int getTaskClassId() {
        return TaskClassId;
    }

    public void setTaskClassId(int taskClassId) {
        TaskClassId = taskClassId;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getCreateUserID() {
        return CreateUserID;
    }

    public void setCreateUserID(String createUserID) {
        CreateUserID = createUserID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
}
