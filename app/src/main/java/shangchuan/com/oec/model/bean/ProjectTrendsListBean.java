package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/5/19.
 */

public class ProjectTrendsListBean {

    private  String  Id ;
    private  String  UserId ;
    private  String  UserTrueName ;
    private  String   ObjectType;
    private  String  ObjectId ;
    private  String   IsComment;
    private  String  PId ;
    private  String  ProcessInfo ;
    private  String  Remark ;
    private  String  ObjectName ;
    private  String  ProjectID ;
    private  String   ProjectName;
    private  String  PreUrl ;
    private  String  CreateTime ;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserTrueName() {
        return UserTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        UserTrueName = userTrueName;
    }

    public String getObjectType() {
        return ObjectType;
    }

    public void setObjectType(String objectType) {
        ObjectType = objectType;
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

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public String getProcessInfo() {
        return ProcessInfo;
    }

    public void setProcessInfo(String processInfo) {
        ProcessInfo = processInfo;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getObjectName() {
        return ObjectName;
    }

    public void setObjectName(String objectName) {
        ObjectName = objectName;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getPreUrl() {
        return PreUrl;
    }

    public void setPreUrl(String preUrl) {
        PreUrl = preUrl;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
}
