package shangchuan.com.oec.present.contact;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.NewsDetailsBean;

/**
 * Created by sg280 on 2017/4/26.
 */

public interface NewsDetailsContract {
   interface  View extends BaseView{
       void showContent(NewsDetailsBean bean);
   }
    interface Present extends BasePresent<View>{
        void getNewsDetails(int id);
    }

}
