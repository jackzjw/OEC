package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/4/10.
 */

public class ClientDetailsBasicBean {
    private CustomerListBean cinfo;
    private ArrayList<ContactListBean> cclist;

    public CustomerListBean getCinfo() {
        return cinfo;
    }

    public void setCinfo(CustomerListBean cinfo) {
        this.cinfo = cinfo;
    }

    public ArrayList<ContactListBean> getCclist() {
        return cclist;
    }

    public void setCclist(ArrayList<ContactListBean> cclist) {
        this.cclist = cclist;
    }
}
