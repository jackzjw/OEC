package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/28.
 */

public class MyInfoBean {


      private int UserId  ;
      private int  UTId ;
      private String  UserMobile;
      private String UserTitile  ;
      private String UserNickName ;
      private String UserTrueName ;
      private int UserGender  ;
      private String UserAvatar;
      private String  UserEmail;
      private int[] RoleIds ;
      private int[] GroupIds;

    public String getUserAvatar() {
        return UserAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        UserAvatar = userAvatar;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getUTId() {
        return UTId;
    }

    public void setUTId(int UTId) {
        this.UTId = UTId;
    }

    public String getUserMobile() {
        return UserMobile;
    }

    public void setUserMobile(String userMobile) {
        UserMobile = userMobile;
    }

    public String getUserTitile() {
        return UserTitile;
    }

    public void setUserTitile(String userTitile) {
        UserTitile = userTitile;
    }

    public String getUserNickName() {
        return UserNickName;
    }

    public void setUserNickName(String userNickName) {
        UserNickName = userNickName;
    }

    public String getUserTrueName() {
        return UserTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        UserTrueName = userTrueName;
    }

    public int getUserGender() {
        return UserGender;
    }

    public void setUserGender(int userGender) {
        UserGender = userGender;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public int[] getRoleIds() {
        return RoleIds;
    }

    public void setRoleIds(int[] roleIds) {
        RoleIds = roleIds;
    }

    public int[] getGroupIds() {
        return GroupIds;
    }

    public void setGroupIds(int[] groupIds) {
        GroupIds = groupIds;
    }
}
