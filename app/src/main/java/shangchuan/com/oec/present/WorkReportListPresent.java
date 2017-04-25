package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.WorkReportListBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.WorkReportContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/18.
 */

public class WorkReportListPresent extends RxPresent<WorkReportContract.View> implements WorkReportContract.Present {

    private RetrofitHelper mHelper;
    private int currentPage=1;
    private List<WorkReportListBean> mTotalList=new ArrayList<>();
    @Inject
    public WorkReportListPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }


    @Override
    public void getWrList(int type) {
        currentPage=1;
        Subscription subscription = mHelper.getApiSevice().getWorkReportList(type, currentPage++, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<WorkReportListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<WorkReportListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<WorkReportListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<WorkReportListBean> bean) {
                        LogUtil.i(bean.getItems().toString());
                             mView.showContent(bean.getItems());
                             mTotalList=bean.getItems();
                    }
                });
         add(subscription);
    }

    @Override
    public void getMoreWrList(int type) {
        Subscription subscription = mHelper.getApiSevice().getWorkReportList(type, currentPage++, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<WorkReportListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<WorkReportListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<WorkReportListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<WorkReportListBean> bean) {
                        mTotalList.addAll(bean.getItems());
                        mView.showMoreContent(mTotalList,mTotalList.size(),mTotalList.size()+bean.getSize());
                    }
                });
        add(subscription);
    }
}
