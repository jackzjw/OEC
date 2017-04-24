package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/3/31.
 */

public class OaDetailsBean {

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
    private ArrayList<AttchmentBean> AttachmentList;
    private ArrayList<ProcessListBean> ProcessList;

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


}
