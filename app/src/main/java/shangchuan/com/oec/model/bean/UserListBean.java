package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/13.
 */

public class UserListBean {

    private int Id;
    private int UserId;
    private String UserNickName;
    private String UserAvatar;
    private String UserTrueName;
    private String GroupName;

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

    public String getUserNickName() {
        return UserNickName;
    }

    public void setUserNickName(String userNickName) {
        UserNickName = userNickName;
    }

    public String getUserAvatar() {
        return UserAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        UserAvatar = userAvatar;
    }

    public String getUserTrueName() {
        return UserTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        UserTrueName = userTrueName;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }
}
