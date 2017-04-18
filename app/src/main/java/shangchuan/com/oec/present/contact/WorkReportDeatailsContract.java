package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.AttchmentBean;
import shangchuan.com.oec.model.bean.WorkReportDetailsBean;

/**
 * Created by sg280 on 2017/4/18.
 */

public interface WorkReportDeatailsContract {
    interface View extends BaseView{
        void showContent(WorkReportDetailsBean bean);
        void showImgUrls(List<String> urls);
        void showFileResourse(List<AttchmentBean> beanList);
    }
    interface Present extends BasePresent<View>{
        void getWrDetails(int id);
    }
}
