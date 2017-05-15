package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/5/15.
 */

public class CommentResultBean {

    private String  Id;
    private String  PId;
    private String  Remark;
    private String UserId ;
    private String ToUserId ;
    private String  UserName;
    private String ToUserName ;
    private String  ProcessInfo;
    private String CreateUserName ;
    private String CreateTime ;

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

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getToUserId() {
        return ToUserId;
    }

    public void setToUserId(String toUserId) {
        ToUserId = toUserId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getProcessInfo() {
        return ProcessInfo;
    }

    public void setProcessInfo(String processInfo) {
        ProcessInfo = processInfo;
    }

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String createUserName) {
        CreateUserName = createUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
}
