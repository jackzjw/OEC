package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/4/13.
 */

public class OrgListBean {

    private int Id;
    private String OrganizationName;
    private ArrayList<UserListBean> user_list;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public ArrayList<UserListBean> getUser_list() {
        return user_list;
    }

    public void setUser_list(ArrayList<UserListBean> user_list) {
        this.user_list = user_list;
    }
}
