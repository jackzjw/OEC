package shangchuan.com.oec.present;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaDetailsBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.OaDetailsContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/3/31.
 */

public class OaDetailsPresent extends RxPresent<OaDetailsContract.View> implements OaDetailsContract.Present {

    private RetrofitHelper mHelper;

    @Inject
    public OaDetailsPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }

    @Override
    public void getData(int id) {
        Subscription subscription = mHelper.getApiSevice().getOaDetailsResult(id, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaDetailsBean>>scheduleRxHelper())
                .compose(RxUtil.<OaDetailsBean>handleResult()).subscribe(new CommonSubscriber<OaDetailsBean>(mView) {
                    @Override
                    public void onNext(OaDetailsBean bean) {
                         mView.showContent(bean);
                    }
                });
        add(subscription);
    }
}
