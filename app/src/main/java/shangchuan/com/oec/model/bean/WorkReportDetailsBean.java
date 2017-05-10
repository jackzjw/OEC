package shangchuan.com.oec.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/4/18.
 */

public class WorkReportDetailsBean implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeInt(this.TenantId);
        dest.writeInt(this.UserID);
        dest.writeString(this.ReportType);
        dest.writeString(this.StartDate);
        dest.writeString(this.EndDate);
        dest.writeString(this.ReportTitle);
        dest.writeString(this.ReportContent);
        dest.writeString(this.ReportPlan);
        dest.writeString(this.ReportQuestion);
        dest.writeString(this.Attachment);
        dest.writeString(this.ToUserID);
        dest.writeTypedList(this.ProcessList);
        dest.writeTypedList(this.AttachmentList_new);
        dest.writeString(this.CreateTime);
    }

    public WorkReportDetailsBean() {
    }

    protected WorkReportDetailsBean(Parcel in) {
        this.Id = in.readInt();
        this.TenantId = in.readInt();
        this.UserID = in.readInt();
        this.ReportType = in.readString();
        this.StartDate = in.readString();
        this.EndDate = in.readString();
        this.ReportTitle = in.readString();
        this.ReportContent = in.readString();
        this.ReportPlan = in.readString();
        this.ReportQuestion = in.readString();
        this.Attachment = in.readString();
        this.ToUserID = in.readString();
        this.ProcessList = in.createTypedArrayList(ProcessListBean.CREATOR);
        this.AttachmentList_new = in.createTypedArrayList(AttchmentBean.CREATOR);
        this.CreateTime = in.readString();
    }

    public static final Parcelable.Creator<WorkReportDetailsBean> CREATOR = new Parcelable.Creator<WorkReportDetailsBean>() {
        @Override
        public WorkReportDetailsBean createFromParcel(Parcel source) {
            return new WorkReportDetailsBean(source);
        }

        @Override
        public WorkReportDetailsBean[] newArray(int size) {
            return new WorkReportDetailsBean[size];
        }
    };
}
