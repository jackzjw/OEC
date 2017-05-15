package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/5/15.
 */

public class TaskCommentBean {

    private String Id ;
    private String  PId;
    private String UserID ;
    private String UserName ;
    private String Remark ;
    private String  ObjectId;
    private String  IsComment;
    private String  ProcessInfo;
    private String  ObjectName;
    private String  CreateUserId;
    private String CreateTime ;
    private String CreateUserName ;
    private String  UpdateTime;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
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

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getObjectId() {
        return ObjectId;
    }

    public void setObjectId(String objectId) {
        ObjectId = objectId;
    }

    public String getIsComment() {
        return IsComment;
    }

    public void setIsComment(String isComment) {
        IsComment = isComment;
    }

    public String getProcessInfo() {
        return ProcessInfo;
    }

    public void setProcessInfo(String processInfo) {
        ProcessInfo = processInfo;
    }

    public String getObjectName() {
        return ObjectName;
    }

    public void setObjectName(String objectName) {
        ObjectName = objectName;
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

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }
}
