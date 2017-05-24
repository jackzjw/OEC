package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/5/19.
 */

public class ProjectProcessBlockList {
    private ArrayList<ProjectTrendsBean> ProjectProcessBlockList;
    private String CreateUserId ;
    private String CreateTime ;
    private String CreateUserName ;

    public ArrayList<ProjectTrendsBean> getProjectProcessBlockList() {
        return ProjectProcessBlockList;
    }

    public void setProjectProcessBlockList(ArrayList<ProjectTrendsBean> projectProcessBlockList) {
        ProjectProcessBlockList = projectProcessBlockList;
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

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String createUserName) {
        CreateUserName = createUserName;
    }
}
