package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/27.
 */

public class RoleListBean {

    private int Id;
    private String RoleName;
    private String Remark;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
