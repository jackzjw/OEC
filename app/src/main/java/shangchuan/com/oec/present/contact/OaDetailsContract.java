package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.AttchmentBean;
import shangchuan.com.oec.model.bean.OaDetailsBean;
import shangchuan.com.oec.model.bean.ProcessListBean;

/**
 * Created by sg280 on 2017/3/31.
 */

public interface OaDetailsContract  {
    interface View extends BaseView{
        void showContent(OaDetailsBean bean);
        void showMediaContent(List<AttchmentBean> medias);
        void showTextContent(List<AttchmentBean> texts);
        void openFile(String filePath);
        void showRemark(List<ProcessListBean> bean);
        void dealSuccess();
        void deleteSucc();
    }
    interface Present extends BasePresent<View>{
        void getData(int id);
        void downloadFile(String url);
        void dealOaCheck(int orderId,int result,String remark,String toUserId);
        void deleteOa(int id);
    }
}
