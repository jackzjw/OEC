package shangchuan.com.oec.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/3/31.
 */

public class OaDetailsBean implements Parcelable {

    private boolean Success ;
    private int Id  ;
    private int TenantId  ;
    private int UserId  ;
    private int ClassId  ;
    private String  OrderType;
    private String StartTime ;
    private String  EndTime;
    private String  OrderPeriod;
    private String  OrderTitle;
    private String  OrderContent;
    private int OrderStatus  ;
    private String  Destination;
    private String  Companion;
    private String  CustomerId;
    private String  CustomerName;
    private String  ProjectId;
    private String  ProjectName;
    private String  OrderTime;
    private String OrderAmount;
    private String CreateTime;
    private String CreateUserName;
    private ArrayList<AttchmentBean> AttachmentList;
    private ArrayList<ProcessListBean> ProcessList;

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

    public ArrayList<AttchmentBean> getAttachmentList() {
        return AttachmentList;
    }

    public void setAttachmentList(ArrayList<AttchmentBean> attachmentList) {
        AttachmentList = attachmentList;
    }

    public ArrayList<ProcessListBean> getProcessList() {
        return ProcessList;
    }

    public void setProcessList(ArrayList<ProcessListBean> processList) {
        ProcessList = processList;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
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

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int classId) {
        ClassId = classId;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getOrderPeriod() {
        return OrderPeriod;
    }

    public void setOrderPeriod(String orderPeriod) {
        OrderPeriod = orderPeriod;
    }

    public String getOrderTitle() {
        return OrderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        OrderTitle = orderTitle;
    }

    public String getOrderContent() {
        return OrderContent;
    }

    public void setOrderContent(String orderContent) {
        OrderContent = orderContent;
    }

    public int getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getCompanion() {
        return Companion;
    }

    public void setCompanion(String companion) {
        Companion = companion;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }

    public String getOrderAmount() {
        return OrderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        OrderAmount = orderAmount;
    }

    @Override
    public String toString() {
        return "OaDetailsBean{" +
                "Success=" + Success +
                ", Id=" + Id +
                ", TenantId=" + TenantId +
                ", UserId=" + UserId +
                ", ClassId=" + ClassId +
                ", OrderType='" + OrderType + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", OrderPeriod='" + OrderPeriod + '\'' +
                ", OrderTitle='" + OrderTitle + '\'' +
                ", OrderContent='" + OrderContent + '\'' +
                ", OrderStatus=" + OrderStatus +
                ", Destination='" + Destination + '\'' +
                ", Companion='" + Companion + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", ProjectId='" + ProjectId + '\'' +
                ", ProjectName='" + ProjectName + '\'' +
                ", OrderTime='" + OrderTime + '\'' +
                ", OrderAmount='" + OrderAmount + '\'' +
                ", AttachmentList=" + AttachmentList +
                ", ProcessList=" + ProcessList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.Success ? (byte) 1 : (byte) 0);
        dest.writeInt(this.Id);
        dest.writeInt(this.TenantId);
        dest.writeInt(this.UserId);
        dest.writeInt(this.ClassId);
        dest.writeString(this.OrderType);
        dest.writeString(this.StartTime);
        dest.writeString(this.EndTime);
        dest.writeString(this.OrderPeriod);
        dest.writeString(this.OrderTitle);
        dest.writeString(this.OrderContent);
        dest.writeInt(this.OrderStatus);
        dest.writeString(this.Destination);
        dest.writeString(this.Companion);
        dest.writeString(this.CustomerId);
        dest.writeString(this.CustomerName);
        dest.writeString(this.ProjectId);
        dest.writeString(this.ProjectName);
        dest.writeString(this.OrderTime);
        dest.writeString(this.OrderAmount);
        dest.writeString(this.CreateTime);
        dest.writeString(this.CreateUserName);
        dest.writeTypedList(this.AttachmentList);
        dest.writeTypedList(this.ProcessList);
    }

    public OaDetailsBean() {
    }

    protected OaDetailsBean(Parcel in) {
        this.Success = in.readByte() != 0;
        this.Id = in.readInt();
        this.TenantId = in.readInt();
        this.UserId = in.readInt();
        this.ClassId = in.readInt();
        this.OrderType = in.readString();
        this.StartTime = in.readString();
        this.EndTime = in.readString();
        this.OrderPeriod = in.readString();
        this.OrderTitle = in.readString();
        this.OrderContent = in.readString();
        this.OrderStatus = in.readInt();
        this.Destination = in.readString();
        this.Companion = in.readString();
        this.CustomerId = in.readString();
        this.CustomerName = in.readString();
        this.ProjectId = in.readString();
        this.ProjectName = in.readString();
        this.OrderTime = in.readString();
        this.OrderAmount = in.readString();
        this.CreateTime = in.readString();
        this.CreateUserName = in.readString();
        this.AttachmentList = in.createTypedArrayList(AttchmentBean.CREATOR);
        this.ProcessList = in.createTypedArrayList(ProcessListBean.CREATOR);
    }

    public static final Parcelable.Creator<OaDetailsBean> CREATOR = new Parcelable.Creator<OaDetailsBean>() {
        @Override
        public OaDetailsBean createFromParcel(Parcel source) {
            return new OaDetailsBean(source);
        }

        @Override
        public OaDetailsBean[] newArray(int size) {
            return new OaDetailsBean[size];
        }
    };
}
