package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.WorkReportListBean;

/**
 * Created by sg280 on 2017/4/19.
 */

public interface WorkReportContract {
    interface View extends BaseView{
        void showContent(List<WorkReportListBean> bean);
        void showMoreContent(List<WorkReportListBean> bean, int start, int end);
    }
    interface Present extends BasePresent<View>{
        void getWrList(int type);
        void getMoreWrList(int type);
    }
}
