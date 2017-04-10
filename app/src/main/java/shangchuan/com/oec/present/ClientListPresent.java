package shangchuan.com.oec.present;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.CustomerListBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.ClientListContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/10.
 */

public class ClientListPresent extends RxPresent<ClientListContract.View> implements ClientListContract.Present {
   private RetrofitHelper mHelper;
    private int currentPage;
   private ArrayList<CustomerListBean> mTotalList=new ArrayList<>();
    @Inject
    public ClientListPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }
    @Override
    public void getData(String type) {
        currentPage=1;
        Subscription subscription = mHelper.getApiSevice().getClientLst(type, SaveToken.mToken,currentPage++)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<CustomerListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<CustomerListBean>>handleResult())
                .subscribe(new CommonSubscriber<OaBasicItemBean<CustomerListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<CustomerListBean> bean) {
                        mTotalList=bean.getItems();
                        mView.showContent(mTotalList);
                    }
                });
        add(subscription);
    }

    @Override
    public void getMoreData(String type) {
        Subscription subscription = mHelper.getApiSevice().getClientLst(type, SaveToken.mToken,currentPage++)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<CustomerListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<CustomerListBean>>handleResult())
                .subscribe(new CommonSubscriber<OaBasicItemBean<CustomerListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<CustomerListBean> bean) {
                        mTotalList.addAll(bean.getItems());
                        mView.showMoreContent(mTotalList,mTotalList.size(),mTotalList.size()+bean.getSize());
                    }
                });
        add(subscription);

    }
}
