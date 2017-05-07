package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.RectDeptBean;

/**
 * Created by sg280 on 2017/5/6.
 */

public interface GroupListContract {
    interface View extends BaseView{
        void showGroupList(List<RectDeptBean> bean);
    }
    interface Present extends BasePresent<View>{
        void getGroupList();
        void registerRefresh();
    }
}
