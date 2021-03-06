package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.TrendsListBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.TrendsListContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/26.
 */

public class TrendsListPresent extends RxPresent<TrendsListContract.View> implements TrendsListContract.Present {

    private RetrofitHelper mHelper;
    private int currentPage;
    private List<TrendsListBean> trendsList=new ArrayList<>();
    @Inject
    public TrendsListPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }
    @Override
    public void getTrendList(String jobType,String keyword) {
        currentPage=1;
        Subscription subscription = mHelper.getApiSevice().getTrendsList(jobType,keyword, currentPage++,"10",SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<TrendsListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<TrendsListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<TrendsListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<TrendsListBean> bean) {
                            trendsList = bean.getItems();
                            mView.showContent(bean.getItems());
                    }
                });
       add(subscription);

    }

    @Override
    public void getMoreTrend(String jobType,String keyword) {

        Subscription subscription = mHelper.getApiSevice().getTrendsList(jobType,keyword,currentPage++,"10", SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<TrendsListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<TrendsListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<TrendsListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<TrendsListBean> bean) {
                            trendsList.addAll(bean.getItems());
                            mView.showMoreContent(trendsList,trendsList.size(),trendsList.size()+bean.getSize());

                    }
                });
        add(subscription);
    }
}
