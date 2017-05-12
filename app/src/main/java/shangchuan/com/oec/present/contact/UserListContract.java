package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.UserInfoBean;

/**
 * Created by sg280 on 2017/4/12.
 */

public interface UserListContract  {
    interface View extends BaseView{
        void showContent(List<UserInfoBean> bean);
        void showMoreContent(List<UserInfoBean> bean,int start,int end);
    }
    interface Present extends BasePresent<View>{
        void getUserList(String keyword);
        void getMoreUser();
    }
}
