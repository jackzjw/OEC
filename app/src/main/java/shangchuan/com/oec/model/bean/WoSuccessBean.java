package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/1.
 */

public class WoSuccessBean {

    private int Id;
    private String CreateTime;
    private String CreateUserName;
    private String UpdateTime;
    private String UpdateUserName;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
