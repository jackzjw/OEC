package shangchuan.com.oec.present.contact;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.ClientDetailsBasicBean;

/**
 * Created by sg280 on 2017/4/10.
 */

public interface ClientDetailContract {
    interface  View extends BaseView{
        void showContent(ClientDetailsBasicBean result);
    }
    interface Present extends BasePresent<View>{
        void getClientDetails(int id);
    }

}
