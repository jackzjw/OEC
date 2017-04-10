package shangchuan.com.oec.present;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.ClientDetailsBasicBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.ClientDetailContract;
import shangchuan.com.oec.present.contact.ClientDetailContract.Present;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/10.
 */

public class ClientDetailsPresent extends RxPresent<ClientDetailContract.View> implements Present {
    private RetrofitHelper mHelper;
    @Inject
    public ClientDetailsPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }

    @Override
    public void getClientDetails(int id) {
        Subscription subscription = mHelper.getApiSevice().getClientDetails(id, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ClientDetailsBasicBean>>scheduleRxHelper())
                .compose(RxUtil.<ClientDetailsBasicBean>handleResult()).subscribe(new CommonSubscriber<ClientDetailsBasicBean>(mView) {
                    @Override
                    public void onNext(ClientDetailsBasicBean bean) {
                         mView.showContent(bean);
                    }
                });
        add(subscription);


    }
}
