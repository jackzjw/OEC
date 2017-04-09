package shangchuan.com.oec.present.contact;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.OaDetailsBean;

/**
 * Created by sg280 on 2017/3/31.
 */

public interface OaDetailsContract  {
    interface View extends BaseView{
        void showContent(OaDetailsBean bean);
    }
    interface Present extends BasePresent<View>{
        void getData(int id);
    }
}
