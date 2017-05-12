package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.component.RxBus;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.NewsClassifyBean;
import shangchuan.com.oec.model.bean.NewsListBean;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.NewListContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/26.
 */

public class NewsListPresent extends RxPresent<NewListContract.View> implements NewListContract.Present {
    private RetrofitHelper mHelper;
    private int currentPage;
    private List<NewsListBean> totalNews=new ArrayList<>();
    @Inject
    public NewsListPresent(RetrofitHelper helper){
        this.mHelper=helper;
        registerEvent();
    }

    private void registerEvent() {
        Subscription subscription = RxBus.getDefault().toDefaultObservable(Integer.class, new Action1<Integer>() {
            @Override
            public void call(Integer pos) {
                LogUtil.i("调用了"+pos);
                totalNews.get(pos).setReadStatus(1);
                 mView.updateReadStatus(pos);
            }
        });
           add(subscription);
    }

    @Override
    public void getNewsClassify() {
        Subscription subscription = mHelper.getApiSevice().getNewsClassify(SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<List<NewsClassifyBean>>>scheduleRxHelper())
                .compose(RxUtil.<List<NewsClassifyBean>>handleResult()).subscribe(new CommonSubscriber<List<NewsClassifyBean>>(mView) {
                    @Override
                    public void onNext(List<NewsClassifyBean> been) {
                        mView.showNewsClassify(been.get(0));
                    }
                });
        add(subscription);
    }

    @Override
    public void getNewsList(String classId, int status,String keyword) {
         currentPage=1;
        Subscription subscription = mHelper.getApiSevice().getNewsLists(classId, status, currentPage++,keyword, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<NewsListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<NewsListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<NewsListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<NewsListBean> bean) {
                         totalNews=bean.getItems();
                        mView.showContent(totalNews);
                    }
                });
        add(subscription);

    }

    @Override
    public void getMoreNews(String classid, int status,String keyword) {
        Subscription subscription = mHelper.getApiSevice().getNewsLists(classid, status, currentPage++,keyword, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<NewsListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<NewsListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<NewsListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<NewsListBean> bean) {
                        totalNews.addAll(bean.getItems());
                        mView.showMoreContent(totalNews,totalNews.size(),totalNews.size()+bean.getSize());
                    }
                });
        add(subscription);
    }
}
