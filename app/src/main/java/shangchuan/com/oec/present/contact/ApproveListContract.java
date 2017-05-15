package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.OaItemBean;

/**
 * Created by sg280 on 2017/4/20.
 */

public interface ApproveListContract {
    interface View extends BaseView{
        void showContent(List<OaItemBean> bean);
        void showMoreContent(List<OaItemBean> bean, int start, int end);
        void refreshStatus(int pos,int size);
    }
    interface Present extends BasePresent<View>{
        void getApproveList(String type,int isAudit);
        void getMoreContent(String type,int isAudit);
    }
}
