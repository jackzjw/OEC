package shangchuan.com.oec.present.contact;

import java.util.HashMap;
import java.util.HashSet;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.GroupListBean;
import shangchuan.com.oec.model.bean.RoleListBean;

/**
 * Created by sg280 on 2017/4/27.
 */

public interface AddUserContract {
    interface View extends BaseView{
        void addSuccess();
        void showRoleList(HashSet<RoleListBean> bean);
        void showDeptList(HashSet<GroupListBean> bean);
    }
    interface Present extends BasePresent<View>{
        void addUser(HashMap<String,Object> hashMap);
    }


}
