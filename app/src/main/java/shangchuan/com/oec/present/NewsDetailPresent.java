package shangchuan.com.oec.present;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.NewsDetailsBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.NewsDetailsContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/26.
 */

public class NewsDetailPresent extends RxPresent<NewsDetailsContract.View> implements NewsDetailsContract.Present {
   private RetrofitHelper mHelper;
    @Inject
    public NewsDetailPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }


    @Override
    public void getNewsDetails(int id) {
        Subscription subscription = mHelper.getApiSevice().getNewsDetails(id, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<NewsDetailsBean>>scheduleRxHelper())
                .compose(RxUtil.<NewsDetailsBean>handleResult()).subscribe(new CommonSubscriber<NewsDetailsBean>(mView) {
                    @Override
                    public void onNext(NewsDetailsBean bean) {
                        mView.showContent(bean);
                    }
                });
        add(subscription);
    }
}
