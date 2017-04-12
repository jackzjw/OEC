package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/12.
 */

public class UserInfoBean {

    private int Id ;
    private int UserId ;
    private int IsAdmin  ;
    private String UserMobile ;
    private String UserTitle ;
    private String UserNickName ;
    private String UserTrueName ;
    private int UserGender  ;
    private String UserEmail ;
    private String UserAvatar ;
    private String sortLetter;

    public String getSortLetter() {
        return sortLetter;
    }

    public void setSortLetter(String sortLetter) {
        this.sortLetter = sortLetter;
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

    public int getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        IsAdmin = isAdmin;
    }

    public String getUserMobile() {
        return UserMobile;
    }

    public void setUserMobile(String userMobile) {
        UserMobile = userMobile;
    }

    public String getUserTitle() {
        return UserTitle;
    }

    public void setUserTitle(String userTitle) {
        UserTitle = userTitle;
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

    public String getUserAvatar() {
        return UserAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        UserAvatar = userAvatar;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "sortLetter='" + sortLetter + '\'' +
                '}';
    }
}
