package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/18.
 */

public class WorkReportListBean {
    private int Id;
    private int TenantId;
    private int UserID;
    private String  ReportType;
    private String  StartDate;
    private String  EndDate;
    private String  ReportTitle;
    private int ReportStatus  ;
    private String  ReportContent;
    private String ReportQuestion;
    private int IsHasAttachment;
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

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getReportType() {
        return ReportType;
    }

    public void setReportType(String reportType) {
        ReportType = reportType;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getReportTitle() {
        return ReportTitle;
    }

    public void setReportTitle(String reportTitle) {
        ReportTitle = reportTitle;
    }

    public int getReportStatus() {
        return ReportStatus;
    }

    public void setReportStatus(int reportStatus) {
        ReportStatus = reportStatus;
    }

    public String getReportContent() {
        return ReportContent;
    }

    public void setReportContent(String reportContent) {
        ReportContent = reportContent;
    }

    public String getReportQuestion() {
        return ReportQuestion;
    }

    public void setReportQuestion(String reportQuestion) {
        ReportQuestion = reportQuestion;
    }

    public int getIsHasAttachment() {
        return IsHasAttachment;
    }

    public void setIsHasAttachment(int isHasAttachment) {
        IsHasAttachment = isHasAttachment;
    }
}
