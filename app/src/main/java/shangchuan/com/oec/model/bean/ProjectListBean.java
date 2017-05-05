package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/5/5.
 */

public class ProjectListBean {

      private int  Id ;
     private int  ProjectType ;
     private int  CustomerId ;
      private String CustomerName   ;
     private String ProjectName   ;
     private String ProjectCode   ;
     private int  ProjectStatus ;
     private String Remark   ;
     private int  IsTop ;
private int  QuantityTotal ;
private int  QuantityDone ;
private int  UnDoneCount ;
private String CreateUserId  ;
    private String CreateTime  ;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getProjectType() {
        return ProjectType;
    }

    public void setProjectType(int projectType) {
        ProjectType = projectType;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getProjectCode() {
        return ProjectCode;
    }

    public void setProjectCode(String projectCode) {
        ProjectCode = projectCode;
    }

    public int getProjectStatus() {
        return ProjectStatus;
    }

    public void setProjectStatus(int projectStatus) {
        ProjectStatus = projectStatus;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getIsTop() {
        return IsTop;
    }

    public void setIsTop(int isTop) {
        IsTop = isTop;
    }

    public int getQuantityTotal() {
        return QuantityTotal;
    }

    public void setQuantityTotal(int quantityTotal) {
        QuantityTotal = quantityTotal;
    }

    public int getQuantityDone() {
        return QuantityDone;
    }

    public void setQuantityDone(int quantityDone) {
        QuantityDone = quantityDone;
    }

    public int getUnDoneCount() {
        return UnDoneCount;
    }

    public void setUnDoneCount(int unDoneCount) {
        UnDoneCount = unDoneCount;
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
