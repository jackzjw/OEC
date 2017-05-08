package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.WoClassBean;
import shangchuan.com.oec.model.bean.WoListBean;

/**
 * Created by sg280 on 2017/4/5.
 */

public interface WoListContract  {
    interface View extends BaseView{
        void showParentName(List<WoClassBean> bean);
        void showChildName(List<WoClassBean> Bean);
        void showContentList(List<WoListBean> bean);
        void showMoreContent(List<WoListBean> bean,int start,int end);
    }
    interface Present extends BasePresent<View>{
        void getClassName();
        void getWoList(String aid,String bid,String orderStatus,String status);
        void getMoreWoList(String aid,String bid,String orderStatus,String status);
        void parentToChild(String pid);
    }
}
