package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.AttendanceListBean;

/**
 * Created by sg280 on 2017/4/25.
 */

public interface AttendanceContract {
    interface View extends BaseView{
        void showContent(List<AttendanceListBean> bean);
    }
    interface Present extends BasePresent<View>{
        void getAttendanceList(String date);
    }
}
