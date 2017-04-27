package shangchuan.com.oec.model.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sg280 on 2017/4/27.
 */

public class GroupListBean implements Serializable{


    private int Id;
    private int PId;
    private String GroupName;
    private String Remark;
    private ArrayList<GroupListBean> group_list;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPId() {
        return PId;
    }

    public void setPId(int PId) {
        this.PId = PId;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public ArrayList<GroupListBean> getGroup_list() {
        return group_list;
    }

    public void setGroup_list(ArrayList<GroupListBean> group_list) {
        this.group_list = group_list;
    }

    @Override
    public String toString() {
        return "GroupListBean{" +
                "Id=" + Id +
                ", PId=" + PId +
                ", GroupName='" + GroupName + '\'' +
                ", Remark='" + Remark + '\'' +
                ", group_list=" + group_list +
                '}';
    }
}
