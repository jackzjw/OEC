package shangchuan.com.oec.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sg280 on 2017/4/18.
 */

public class ProcessListBean implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeInt(this.TenantId);
        dest.writeInt(this.ReportId);
        dest.writeInt(this.UserID);
        dest.writeString(this.UserName);
        dest.writeInt(this.ToUserID);
        dest.writeString(this.ToUserName);
        dest.writeInt(this.ProcessResult);
        dest.writeString(this.ProcessResultName);
        dest.writeString(this.Remark);
        dest.writeString(this.CreateUserId);
        dest.writeString(this.CreateTime);
    }

    public ProcessListBean() {
    }

    protected ProcessListBean(Parcel in) {
        this.Id = in.readInt();
        this.TenantId = in.readInt();
        this.ReportId = in.readInt();
        this.UserID = in.readInt();
        this.UserName = in.readString();
        this.ToUserID = in.readInt();
        this.ToUserName = in.readString();
        this.ProcessResult = in.readInt();
        this.ProcessResultName = in.readString();
        this.Remark = in.readString();
        this.CreateUserId = in.readString();
        this.CreateTime = in.readString();
    }

    public static final Parcelable.Creator<ProcessListBean> CREATOR = new Parcelable.Creator<ProcessListBean>() {
        @Override
        public ProcessListBean createFromParcel(Parcel source) {
            return new ProcessListBean(source);
        }

        @Override
        public ProcessListBean[] newArray(int size) {
            return new ProcessListBean[size];
        }
    };

    @Override
    public String toString() {
        return "ProcessListBean{" +
                "Id=" + Id +
                ", TenantId=" + TenantId +
                ", ReportId=" + ReportId +
                ", UserID=" + UserID +
                ", UserName='" + UserName + '\'' +
                ", ToUserID=" + ToUserID +
                ", ToUserName='" + ToUserName + '\'' +
                ", ProcessResult=" + ProcessResult +
                ", ProcessResultName='" + ProcessResultName + '\'' +
                ", Remark='" + Remark + '\'' +
                ", CreateUserId='" + CreateUserId + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }
}
