package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/4/1.
 */

public class WoClassBasicBean {
    private ArrayList<WoClassBean> ClassInfo;
    private String  CreateTime;
    private String  CreateUserName;
    private String  UpdateTime;
    private String UpdateUserName ;

    public ArrayList<WoClassBean> getClassInfo() {
        return ClassInfo;
    }

    public void setClassInfo(ArrayList<WoClassBean> classInfo) {
        ClassInfo = classInfo;
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
