package shangchuan.com.oec.present.contact;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;

/**
 * Created by sg280 on 2017/3/27.
 */

public interface LoginContract  {
    interface View extends BaseView{
       void  loginSuccess();
    }
    interface Present extends BasePresent<View>{
        void login(String tel,String pwd);
    }

}
