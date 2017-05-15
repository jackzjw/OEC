package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.component.RxBus;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.OaItemBean;
import shangchuan.com.oec.model.bean.OaTypeBean;
import shangchuan.com.oec.model.event.OaDealEvent;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.ApproveListContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/20.
 */

public class ApproveListPresent extends RxPresent<ApproveListContract.View> implements ApproveListContract.Present{

    private RetrofitHelper mHelper;
    private int classId;
    private int currentPage=1;
    private List<OaItemBean> mTotalList=new ArrayList<>();
    @Inject
    public ApproveListPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }
    @Override
    public void getApproveList(final String type, final int isAudit) {

        currentPage=1;
        Subscription subscription = mHelper.getApiSevice().getApplyTypes(SaveToken.mToken)
                .flatMap(new Func1<HttpDataResult<List<OaTypeBean>>, Observable<HttpDataResult<OaBasicItemBean<OaItemBean>>>>() {
                    @Override
                    public Observable<HttpDataResult<OaBasicItemBean<OaItemBean>>> call(HttpDataResult<List<OaTypeBean>> result) {
                        if (result.getStatus() == 200) {
                            for (OaTypeBean item : result.getData()) {
                                if (type.equals(item.getClassName())) {
                                    classId = item.getId();
                                    return mHelper.getApiSevice().getApproveList(classId,isAudit, currentPage++, SaveToken.mToken);
                                }
                            }
                        }
                        return null;
                    }
                }).compose(RxUtil.<HttpDataResult<OaBasicItemBean<OaItemBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<OaItemBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<OaItemBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<OaItemBean> bean) {
                        LogUtil.i(bean.getItems().toString());
                                mTotalList=bean.getItems();
                              mView.showContent(mTotalList);
                    }
                });
        add(subscription);
    }

    @Override
    public void getMoreContent(String type,int isAudit) {
        Subscription subscription = mHelper.getApiSevice().getApproveList(classId,isAudit, currentPage++, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<OaItemBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<OaItemBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<OaItemBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<OaItemBean> bean) {
                        mTotalList.addAll(bean.getItems());
                        mView.showMoreContent(mTotalList, mTotalList.size(), mTotalList.size() + bean.getSize());
                    }
                });
        add(subscription);
    }
    //申请状态ID（0-已撤销，1-待审核，2-已通过，3-已驳回）
    //待审核列表回退时删除该条目
    public void registerEvent() {
        Subscription subscription= RxBus.getDefault().toDefaultObservable(OaDealEvent.class, new Action1<OaDealEvent>() {
            @Override
            public void call(OaDealEvent event) {
                mTotalList.remove(event.getPosition());
            mView.refreshStatus(event.getPosition(),mTotalList.size());
            }
        });
        add(subscription);
    }
}
