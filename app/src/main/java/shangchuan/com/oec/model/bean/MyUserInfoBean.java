package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/3/27.
 */

public class MyUserInfoBean {
    private int  LoginUserId;
    private String LoginUserTrueName;
    private String LoginUserNickName;
    private ArrayList<Integer> LoginRoleIdList;
    private int    LoginTenantId;
    private String LoginTenantName;
    private int    CurrentLoginId;
    private int    LoginIsAdmin;
    private String LastTime;
    private String TenantInfos ;
    private String      CreateTime ;
    private String      CreateUserName ;
    private String      UpdateTime ;
    private String  UpdateUserName;
    public int getLoginUserId() {
        return LoginUserId;
    }

    public void setLoginUserId(int loginUserId) {
        LoginUserId = loginUserId;
    }

    public String getLoginUserTrueName() {
        return LoginUserTrueName;
    }

    public void setLoginUserTrueName(String loginUserTrueName) {
        LoginUserTrueName = loginUserTrueName;
    }

    public String getLoginUserNickName() {
        return LoginUserNickName;
    }

    public void setLoginUserNickName(String loginUserNickName) {
        LoginUserNickName = loginUserNickName;
    }

    public ArrayList<Integer> getLoginRoleIdList() {
        return LoginRoleIdList;
    }

    public void setLoginRoleIdList(ArrayList<Integer> loginRoleIdList) {
        LoginRoleIdList = loginRoleIdList;
    }

    public int getLoginTenantId() {
        return LoginTenantId;
    }

    public void setLoginTenantId(int loginTenantId) {
        LoginTenantId = loginTenantId;
    }

    public String getLoginTenantName() {
        return LoginTenantName;
    }

    public void setLoginTenantName(String loginTenantName) {
        LoginTenantName = loginTenantName;
    }

    public int getCurrentLoginId() {
        return CurrentLoginId;
    }

    public void setCurrentLoginId(int currentLoginId) {
        CurrentLoginId = currentLoginId;
    }

    public int getLoginIsAdmin() {
        return LoginIsAdmin;
    }

    public void setLoginIsAdmin(int loginIsAdmin) {
        LoginIsAdmin = loginIsAdmin;
    }

    public String getLastTime() {
        return LastTime;
    }

    public void setLastTime(String lastTime) {
        LastTime = lastTime;
    }

    public String getTenantInfos() {
        return TenantInfos;
    }

    public void setTenantInfos(String tenantInfos) {
        TenantInfos = tenantInfos;
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

    @Override
    public String toString() {
        return "MyUserInfoBean{" +
                "LoginUserId=" + LoginUserId +
                ", LoginUserTrueName='" + LoginUserTrueName + '\'' +
                ", LoginUserNickName='" + LoginUserNickName + '\'' +
                ", LoginRoleIdList='" + LoginRoleIdList + '\'' +
                ", LoginTenantId=" + LoginTenantId +
                ", LoginTenantName='" + LoginTenantName + '\'' +
                ", CurrentLoginId=" + CurrentLoginId +
                ", LoginIsAdmin=" + LoginIsAdmin +
                ", LastTime='" + LastTime + '\'' +
                ", TenantInfos='" + TenantInfos + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", CreateUserName='" + CreateUserName + '\'' +
                ", UpdateTime='" + UpdateTime + '\'' +
                ", UpdateUserName='" + UpdateUserName + '\'' +
                '}';
    }
}
