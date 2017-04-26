package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.component.RxBus;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.NewsListBean;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.TrendsListBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.HomeListContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/21.
 */

public class HomeListPresent extends RxPresent<HomeListContract.View> implements HomeListContract.Present {
  private RetrofitHelper mHelper;
    private List<NewsListBean> mNewsList=new ArrayList<>();
    @Inject
    public HomeListPresent(RetrofitHelper helper){
        this.mHelper=helper;
        registerEvent();
    }
    private void registerEvent() {
        Subscription subscription = RxBus.getDefault().toDefaultObservable(Integer.class, new Action1<Integer>() {
            @Override
            public void call(Integer pos) {
                mNewsList.get(pos).setReadStatus(1);
                mView.updateReadStatus(pos);
            }
        });
        add(subscription);
    }
    @Override
    public void getNewsList() {
        Subscription subscription = mHelper.getApiSevice().getNewsList(1, 3, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<NewsListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<NewsListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<NewsListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<NewsListBean> bean) {
                        mNewsList = bean.getItems();
                        mView.showNewsList(mNewsList);
                    }
                });
     add(subscription);


    }

    @Override
    public void getTrendsList() {
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("Page",1);
        hashMap.put("Size",5);
        Subscription subscription = mHelper.getApiSevice().getTrendsList(hashMap, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<TrendsListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<TrendsListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<TrendsListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<TrendsListBean> bean) {
                             mView.showTrendsList(bean.getItems());
                    }
                });
        add(subscription);
    }
}
