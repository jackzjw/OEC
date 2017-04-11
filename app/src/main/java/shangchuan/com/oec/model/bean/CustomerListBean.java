package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/10.
 */

public class CustomerListBean {
    private int Id;
    private int TenantId;
    private String CustomerType;
    private String CustomerName;
    private String CustomerShortName;
    private String CustomerCode;
    private String CustomerTel;
    private String CustomerCity;
    private String CustomerAddress;
    private String CustomerPostcode;
    private String GroupId;
    private String UserId;
    private String Remark;
    private String CreateUserId;
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

    public String getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(String customerType) {
        CustomerType = customerType;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerShortName() {
        return CustomerShortName;
    }

    public void setCustomerShortName(String customerShortName) {
        CustomerShortName = customerShortName;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }

    public String getCustomerTel() {
        return CustomerTel;
    }

    public void setCustomerTel(String customerTel) {
        CustomerTel = customerTel;
    }

    public String getCustomerCity() {
        return CustomerCity;
    }

    public void setCustomerCity(String customerCity) {
        CustomerCity = customerCity;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerPostcode() {
        return CustomerPostcode;
    }

    public void setCustomerPostcode(String customerPostcode) {
        CustomerPostcode = customerPostcode;
    }

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String groupId) {
        GroupId = groupId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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
    public String toString() {
        return "CustomerListBean{" +
                "Id=" + Id +
                ", TenantId=" + TenantId +
                ", CustomerType='" + CustomerType + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerShortName='" + CustomerShortName + '\'' +
                ", CustomerCode='" + CustomerCode + '\'' +
                ", CustomerTel='" + CustomerTel + '\'' +
                ", CustomerCity='" + CustomerCity + '\'' +
                ", CustomerAddress='" + CustomerAddress + '\'' +
                ", CustomerPostcode='" + CustomerPostcode + '\'' +
                ", GroupId='" + GroupId + '\'' +
                ", UserId='" + UserId + '\'' +
                ", Remark='" + Remark + '\'' +
                ", CreateUserId='" + CreateUserId + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }
}