package shangchuan.com.oec.present.contact;

import java.util.HashMap;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.MyInfoBean;

/**
 * Created by sg280 on 2017/4/28.
 */

public interface ModifyUserInfoContract {
    interface View extends BaseView{
        void showUserInfoDetail(MyInfoBean bean);
        void modifySuccess();
        void upLoadSuccess(String fileName);
    }
    interface Present extends BasePresent<View>{
        void getUserInfoDetail(int id);
        void modifyUserInfo(HashMap<String,Object> hashMap);
        void upLoadImg(String imgPath);
    }
}
