package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/5/15.
 */

public class TaskDetailsBean {

    private ArrayList<AttchmentBean> attachment_list;
    private ArrayList<TaskCommentBean> comment_list;
    private String   Id;
    private String  ProjectId ;
    private String  TaskClassId ;
    private String  TaskTitle ;
    private String  TaskContent ;
    private String  TaskStatus ;
    private String  UserID ;
    private String  UserName ;
    private String  Deadline ;
    private String   Role;
    private String  CreateUserId ;
    private String CreateTime;
    private String CreateUserName;

    public ArrayList<AttchmentBean> getAttachment_list() {
        return attachment_list;
    }

    public void setAttachment_list(ArrayList<AttchmentBean> attachment_list) {
        this.attachment_list = attachment_list;
    }

    public ArrayList<TaskCommentBean> getComment_list() {
        return comment_list;
    }

    public void setComment_list(ArrayList<TaskCommentBean> comment_list) {
        this.comment_list = comment_list;
    }

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

    public String getTaskClassId() {
        return TaskClassId;
    }

    public void setTaskClassId(String taskClassId) {
        TaskClassId = taskClassId;
    }

    public String getTaskTitle() {
        return TaskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        TaskTitle = taskTitle;
    }

    public String getTaskContent() {
        return TaskContent;
    }

    public void setTaskContent(String taskContent) {
        TaskContent = taskContent;
    }

    public String getTaskStatus() {
        return TaskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        TaskStatus = taskStatus;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
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

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(String createUserId) {
        CreateUserId = createUserId;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String createUserName) {
        CreateUserName = createUserName;
    }
}
