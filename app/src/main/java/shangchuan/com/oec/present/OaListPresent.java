package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.OaItemBean;
import shangchuan.com.oec.model.bean.OaTypeBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.OaListContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/3/30.
 */

public class OaListPresent extends RxPresent<OaListContract.View> implements OaListContract.Present {
   private RetrofitHelper mHelper;
    private int currentPage=1;
    private int classId;
    private List<OaItemBean> totalList=new ArrayList<>();
    @Inject
    public OaListPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }
    @Override
    public void getApplyType(final String type) {
        Subscription subscriptions = mHelper.getApiSevice().getApplyTypes(SaveToken.mToken)
                .flatMap(new Func1<HttpDataResult<List<OaTypeBean>>, Observable<HttpDataResult<OaBasicItemBean<OaItemBean>>>>() {
                    @Override
                    public Observable<HttpDataResult<OaBasicItemBean<OaItemBean>>> call(HttpDataResult<List<OaTypeBean>> result) {
                         if(result.getStatus()==200) {
                             for (OaTypeBean bean : result.getData()) {
                              //   LogUtil.i(result.getData().toString());
                                 if (type.equals(bean.getClassName())) {
                                     classId = bean.getId();
                                     return mHelper.getApiSevice().getOaResult(bean.getId(), SaveToken.mToken, currentPage++);
                                 }
                             }
                         }else {
                         }
                        return null;
                    }
                }).compose(RxUtil.<HttpDataResult<OaBasicItemBean<OaItemBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<OaItemBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean bean) {
                           totalList.addAll(bean.getItems());
                             mView.showContent(totalList);
                    }
                });
        add(subscriptions);
    }

    @Override
    public void getMoreContent() {
        Subscription subsrciption = mHelper.getApiSevice().getOaResult(classId, SaveToken.mToken, currentPage)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<OaItemBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<OaItemBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean bean) {
                          totalList.addAll(bean.getItems());
                        mView.showMoreContent(totalList,totalList.size(),totalList.size()+bean.getSize());
                    }
                });
            add(subsrciption);
    }
}
