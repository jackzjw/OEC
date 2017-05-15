package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/3/30.
 */

public class OaItemBean {

    private int Id  ;
    private int UserId   ;
    private int  ClassId ;
    private String  OrderType ;
    private String StartTime  ;
    private String  EndTime ;
    private String   OrderPeriod;
    private String   OrderTitle;
    private String  OrderContent ;
    private int  OrderStatus ;
    private String  Destination ;
    private String  Companion ;
    private String CustomerId   ;
    private String CustomerName  ;
    private String ProjectId;
    private String  ProjectName;
    private String  OrderTime;
    private String   OrderAmount;
    private String  Handler;
    private String  Attachments;
    private String CreateTime;
    private String CreateUserName;

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

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getHandler() {
        return Handler;
    }

    public void setHandler(String handler) {
        Handler = handler;
    }

    public String getAttachments() {
        return Attachments;
    }

    public void setAttachments(String attachments) {
        Attachments = attachments;
    }

    @Override
    public String toString() {
        return "OaItemBean{" +
                "Id=" + Id +
                ", UserId=" + UserId +
                ", ClassId=" + ClassId +
                ", OrderType='" + OrderType + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", OrderPeriod=" + OrderPeriod +
                ", OrderTitle='" + OrderTitle + '\'' +
                ", OrderContent='" + OrderContent + '\'' +
                ", OrderStatus=" + OrderStatus +
                ", Destination='" + Destination + '\'' +
                ", Companion='" + Companion + '\'' +
                ", CustomerId=" + CustomerId +
                ", CustomerName='" + CustomerName + '\'' +
                ", ProjectId='" + ProjectId + '\'' +
                ", ProjectName='" + ProjectName + '\'' +
                ", OrderTime='" + OrderTime + '\'' +
                ", OrderAmount=" + OrderAmount +
                ", Handler='" + Handler + '\'' +
                ", Attachments='" + Attachments + '\'' +
                '}';


    }
}
