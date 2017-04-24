package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/18.
 */

public class ProcessListBean {

    private int Id;
    private int TenantId;
    private int ReportId;
    private int UserID;
    private String UserName;
    private int ToUserID;
    private String ToUserName;
    private int  ProcessResult;
    private String  ProcessResultName;
    private String  Remark;
    private String  CreateUserId;
    private String CreateTime;

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTenantId() {
        return TenantId;
    }

    public void setTenantId(int tenantId) {
        TenantId = tenantId;
    }

    public int getReportId() {
        return ReportId;
    }

    public void setReportId(int reportId) {
        ReportId = reportId;
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

    public int getToUserID() {
        return ToUserID;
    }

    public void setToUserID(int toUserID) {
        ToUserID = toUserID;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public int getProcessResult() {
        return ProcessResult;
    }

    public void setProcessResult(int processResult) {
        ProcessResult = processResult;
    }

    public String getProcessResultName() {
        return ProcessResultName;
    }

    public void setProcessResultName(String processResultName) {
        ProcessResultName = processResultName;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(String createUserId) {
        CreateUserId = createUserId;
    }
}
