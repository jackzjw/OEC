package shangchuan.com.oec.present.contact;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.WoSuccessBean;

/**
 * Created by sg280 on 2017/4/11.
 */

public interface AddClientContract  {
    interface View extends BaseView{
         void saveSuccess(WoSuccessBean result);

    }
    interface  Present extends BasePresent<View>{
        void submitClientData(String CustomerType,String CustomerName,String CustomerShortName,String CustomerCode,
                              String CustomerTel,String CustomerCity,String CustomerAddress,String CustomerPostcode,
                              String Remark,int UserId,int GroupId);
    }
}
