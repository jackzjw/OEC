package shangchuan.com.oec.present;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OrgBasicBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.ApproverContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/13.
 */

public class ApproverPresent extends RxPresent<ApproverContract.View> implements ApproverContract.Present {

    private RetrofitHelper mHelper;
    @Inject
    public ApproverPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }
    @Override
    public void getOrgList() {
        Subscription subscription = mHelper.getApiSevice().getGroupList(SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OrgBasicBean>>scheduleRxHelper())
                .compose(RxUtil.<OrgBasicBean>handleResult()).subscribe(new CommonSubscriber<OrgBasicBean>(mView) {
                    @Override
                    public void onNext(OrgBasicBean bean) {
                      mView.showContent(bean.getOrg_list());
                    }
                });
        add(subscription);
    }
}
