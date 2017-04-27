package shangchuan.com.oec.model.event;

import java.util.HashSet;

import shangchuan.com.oec.model.bean.RoleListBean;

/**
 * Created by sg280 on 2017/4/27.
 */

public class RoleSelectEvent {
    private HashSet<RoleListBean> roleBeans;

    public RoleSelectEvent(HashSet<RoleListBean> roles){
        this.roleBeans=roles;
    }
    public HashSet<RoleListBean> getRoleBeans() {
        return roleBeans;
    }

    public void setRoleBeans(HashSet<RoleListBean> roleBeans) {
        this.roleBeans = roleBeans;
    }
}
