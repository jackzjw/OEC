package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.CustomerListBean;

/**
 * Created by sg280 on 2017/4/10.
 */

public interface ClientListContract  {
    interface View extends BaseView{
        void showContent(List<CustomerListBean> bean);
        void showMoreContent(List<CustomerListBean> bean,int start,int end);
    }

        interface Present extends BasePresent<View>{
    void getData(String type);
    void getMoreData(String type);
}
}