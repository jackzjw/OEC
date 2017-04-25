package shangchuan.com.oec.present;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.AttendanceListBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.AttendanceContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/25.
 */

public class AttendancePresent extends RxPresent<AttendanceContract.View> implements AttendanceContract.Present {
   private RetrofitHelper mHelper;
    @Inject
    public AttendancePresent(RetrofitHelper helper){
        this.mHelper=helper;
    }


    @Override
    public void getAttendanceList(String date) {
        Subscription subscription = mHelper.getApiSevice().getAttendanceList(date, 30, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<AttendanceListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<AttendanceListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<AttendanceListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<AttendanceListBean> bean) {
                      mView.showContent(bean.getItems());
                    }
                });
        add(subscription);
    }
}
