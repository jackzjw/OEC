package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.ApproveListBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.OaTypeBean;
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
    private List<ApproveListBean> mTotalList=new ArrayList<>();
    @Inject
    public ApproveListPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }
    @Override
    public void getApproveList(final String type) {

        currentPage=1;
        Subscription subscription = mHelper.getApiSevice().getApplyTypes(SaveToken.mToken)
                .flatMap(new Func1<HttpDataResult<List<OaTypeBean>>, Observable<HttpDataResult<OaBasicItemBean<ApproveListBean>>>>() {
                    @Override
                    public Observable<HttpDataResult<OaBasicItemBean<ApproveListBean>>> call(HttpDataResult<List<OaTypeBean>> result) {
                        if (result.getStatus() == 200) {
                            for (OaTypeBean item : result.getData()) {
                                if (type.equals(item.getClassName())) {
                                    classId = item.getId();
                                        LogUtil.i("id="+classId);
                                    return mHelper.getApiSevice().getApproveList(classId, currentPage++, SaveToken.mToken);
                                }
                            }
                        }
                        return null;
                    }
                }).compose(RxUtil.<HttpDataResult<OaBasicItemBean<ApproveListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<ApproveListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<ApproveListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<ApproveListBean> bean) {
                        LogUtil.i(bean.getItems().toString());
                                mTotalList=bean.getItems();
                              mView.showContent(mTotalList);
                    }
                });
        add(subscription);
    }

    @Override
    public void getMoreContent(String type) {
        Subscription subscription = mHelper.getApiSevice().getApproveList(classId, currentPage++, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<ApproveListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<ApproveListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<ApproveListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<ApproveListBean> bean) {
                        mTotalList.addAll(bean.getItems());
                        mView.showMoreContent(mTotalList, mTotalList.size(), mTotalList.size() + bean.getSize());
                    }
                });
        add(subscription);
    }
}
