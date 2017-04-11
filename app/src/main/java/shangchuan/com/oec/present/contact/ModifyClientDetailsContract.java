package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.ClientDetailsBasicBean;
import shangchuan.com.oec.model.bean.ContactListBean;
import shangchuan.com.oec.model.bean.WoSuccessBean;

/**
 * Created by sg280 on 2017/4/11.
 */

public interface ModifyClientDetailsContract {
    interface View extends BaseView {
        void saveSuccess(WoSuccessBean result);
        void showContent(ClientDetailsBasicBean bean);
        void deleteSuccess();
        void showContactList(List<ContactListBean> bean);
    }
    interface  Present extends BasePresent<View> {
        void submitClientData(String CustomerType,String CustomerName,String CustomerShortName,String CustomerCode,
                              String CustomerTel,String CustomerCity,String CustomerAddress,String CustomerPostcode,
                              String Remark,int UserId,int GroupId);
        void getClientDetails(int id);
        void deleteContact(int id);
        void getContactList(int id);

    }
}
