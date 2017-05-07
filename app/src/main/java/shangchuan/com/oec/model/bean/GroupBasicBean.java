package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/4/27.
 */

public class GroupBasicBean {
    private String TenantName;
    private ArrayList<GroupListBean> group_list;

    public String getTenantName() {
        return TenantName;
    }

    public void setTenantName(String tenantName) {
        TenantName = tenantName;
    }

    public ArrayList<GroupListBean> getGroup_list() {
        return group_list;
    }

    public void setGroup_list(ArrayList<GroupListBean> group_list) {
        this.group_list = group_list;
    }

    @Override
    public String toString() {
        return "GroupBasicBean{" +
                "TenantName='" + TenantName + '\'' +
                ", group_list=" + group_list +
                '}';
    }
}
