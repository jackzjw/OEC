package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.ApproveListBean;

/**
 * Created by sg280 on 2017/4/20.
 */

public interface ApproveListContract {
    interface View extends BaseView{
        void showContent(List<ApproveListBean> bean);
        void showMoreContent(List<ApproveListBean> bean,int start,int end);
    }
    interface Present extends BasePresent<View>{
        void getApproveList(String type);
        void getMoreContent(String type);
    }
}
