package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/21.
 */

public class TrendsListBean {

    private int   Id;
    private String   JobType;
    private String   JobTitle;
    private String   JobContent;
    private int   OrderId ;
    private String  CreateUserId ;
    private String  CreateTime ;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getJobType() {
        return JobType;
    }

    public void setJobType(String jobType) {
        JobType = jobType;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getJobContent() {
        return JobContent;
    }

    public void setJobContent(String jobContent) {
        JobContent = jobContent;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public String getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(String createUserId) {
        CreateUserId = createUserId;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
}
