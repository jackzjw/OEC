package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/3/28.
 */

public class OrganizeInfoBean {

    private int  Id          ;
    private String    TenantName      ;
    private String       TenantLogo   ;
    private String  CreateTime        ;
    private String   CreateUserName       ;
    private String     UpdateTime     ;
    private String     UpdateUserName     ;
    private String  TenantCode;
    private String  AdminMobile;

    public String getTenantCode() {
        return TenantCode;
    }

    public void setTenantCode(String tenantCode) {
        TenantCode = tenantCode;
    }

    public String getAdminMobile() {
        return AdminMobile;
    }

    public void setAdminMobile(String adminMobile) {
        AdminMobile = adminMobile;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenantName() {
        return TenantName;
    }

    public void setTenantName(String tenantName) {
        TenantName = tenantName;
    }

    public String getTenantLogo() {
        return TenantLogo;
    }

    public void setTenantLogo(String tenantLogo) {
        TenantLogo = tenantLogo;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String createUserName) {
        CreateUserName = createUserName;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public String getUpdateUserName() {
        return UpdateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        UpdateUserName = updateUserName;
    }
}
