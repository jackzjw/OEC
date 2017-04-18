package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/4/18.
 */

public class WorkReportDetailsBean {

    private int Id;
    private int TenantId;
    private int UserID;
    private String  ReportType;
    private String  StartDate;
    private String  EndDate;
    private String  ReportTitle;
    private String  ReportContent;
    private String  ReportPlan;
    private String  ReportQuestion;
    private String  Attachment;
    private String  ToUserID;
    private ArrayList<ProcessListBean> ProcessList;
    private ArrayList<AttchmentBean> AttachmentList_new  ;
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

    public String getReportContent() {
        return ReportContent;
    }

    public void setReportContent(String reportContent) {
        ReportContent = reportContent;
    }

    public String getReportPlan() {
        return ReportPlan;
    }

    public void setReportPlan(String reportPlan) {
        ReportPlan = reportPlan;
    }

    public String getReportQuestion() {
        return ReportQuestion;
    }

    public void setReportQuestion(String reportQuestion) {
        ReportQuestion = reportQuestion;
    }

    public String getAttachment() {
        return Attachment;
    }

    public void setAttachment(String attachment) {
        Attachment = attachment;
    }

    public String getToUserID() {
        return ToUserID;
    }

    public void setToUserID(String toUserID) {
        ToUserID = toUserID;
    }

    public ArrayList<ProcessListBean> getProcessList() {
        return ProcessList;
    }

    public void setProcessList(ArrayList<ProcessListBean> processList) {
        ProcessList = processList;
    }

    public ArrayList<AttchmentBean> getAttachmentList_new() {
        return AttachmentList_new;
    }

    public void setAttachmentList_new(ArrayList<AttchmentBean> attachmentList_new) {
        AttachmentList_new = attachmentList_new;
    }
}
