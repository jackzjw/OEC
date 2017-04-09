package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/3/29.
 */

public class CharacterInfoBean {
    private int  LoginUserId ;
    private String  LoginUserTrueName ;
    private String   LoginUserNickName;
    private int  LoginTenantId ;
    private String  LoginTenantName ;
    private int CurrentLoginId   ;
    private int LoginIsAdmin   ;
    private String  LoginAvatar ;
    private String  lastTime ;
    private String  CreateTime;
    private String  CreateUserName;
    private String UpdateTime;
    private String  UpdateUserName;
    private ArrayList<Integer> LoginRoleIdList;

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

    public String getLoginAvatar() {
        return LoginAvatar;
    }

    public void setLoginAvatar(String loginAvatar) {
        LoginAvatar = loginAvatar;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
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

    public ArrayList<Integer> getLoginRoleIdList() {
        return LoginRoleIdList;
    }

    public void setLoginRoleIdList(ArrayList<Integer> loginRoleIdList) {
        LoginRoleIdList = loginRoleIdList;


    }

    @Override
    public String toString() {
        return "CharacterInfoBean{" +
                "LoginUserId=" + LoginUserId +
                ", LoginUserTrueName='" + LoginUserTrueName + '\'' +
                ", LoginUserNickName='" + LoginUserNickName + '\'' +
                ", LoginTenantId=" + LoginTenantId +
                ", LoginTenantName='" + LoginTenantName + '\'' +
                ", CurrentLoginId=" + CurrentLoginId +
                ", LoginIsAdmin=" + LoginIsAdmin +
                ", LoginAvatar='" + LoginAvatar + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", CreateUserName='" + CreateUserName + '\'' +
                ", UpdateTime='" + UpdateTime + '\'' +
                ", UpdateUserName='" + UpdateUserName + '\'' +
                ", LoginRoleIdList=" + LoginRoleIdList +
                '}';
    }
}
