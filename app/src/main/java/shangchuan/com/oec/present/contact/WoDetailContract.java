package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.AttchmentBean;
import shangchuan.com.oec.model.bean.ProcessListBean;
import shangchuan.com.oec.model.bean.WoDetailBean;
import shangchuan.com.oec.model.bean.WoSuccessBean;

/**
 * Created by sg280 on 2017/4/24.
 */

public interface WoDetailContract {
    interface View extends BaseView{
        void showContent(WoDetailBean bean);
        void showMedias(List<AttchmentBean> medias);
        void showTexts(List<AttchmentBean> texts);
        void openFile(String filePath);
        void showRemark(List<ProcessListBean> remarks);
        void dealSuccess(WoSuccessBean bean);
        void delSuccess();

    }
    interface Present extends BasePresent<View>{
        void getWoDetail(int id);
        void downLoadFile(String url);
        void dealWoResult(int result,int orderId,String remark,String otherId);
        void delWorkOrder(int id);
    }
}
