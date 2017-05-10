package shangchuan.com.oec.present;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.app.App;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.CharactersTokenBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.MySelfInfo;
import shangchuan.com.oec.model.bean.OrganizeInfoBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.OrganizationListContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.SharePreferenceUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/3/29.
 */

public class OrganizationListPresent extends RxPresent<OrganizationListContract.View> implements OrganizationListContract.Present {
    private RetrofitHelper mHelper;

    @Inject
    public OrganizationListPresent(RetrofitHelper helper){
        this.mHelper=helper;

    }
    @Override
    public void getOzList() {
       int userId= SharePreferenceUtil.getUserId();
        Subscription subscription = mHelper.getApiSevice().getTenantList(userId, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<List<OrganizeInfoBean>>>scheduleRxHelper())
                .compose(RxUtil.<List<OrganizeInfoBean>>handleResult()).subscribe(new CommonSubscriber<List<OrganizeInfoBean>>(mView) {
                    @Override
                    public void onNext(List<OrganizeInfoBean> beanList) {
                        mView.showTenantsResult(beanList);
                    }
                });
        add(subscription);
    }

    @Override
    public void enterTenant(int userid, final int tenantId) {
        Subscription subscription=mHelper.getApiSevice().enterTenant(userid,tenantId,SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<CharactersTokenBean>>scheduleRxHelper())
                .compose(RxUtil.<CharactersTokenBean>handleResult()).subscribe(new CommonSubscriber<CharactersTokenBean>(mView) {
                    @Override
                    public void onNext(CharactersTokenBean bean) {
                        MySelfInfo.getInstance().setTenantId(tenantId);
                        MySelfInfo.getInstance().setNickName(bean.getUserinfo().getLoginUserNickName());
                        MySelfInfo.getInstance().setTrueName(bean.getUserinfo().getLoginUserTrueName());
                        MySelfInfo.getInstance().setAvatar(bean.getUserinfo().getLoginAvatar());
                        MySelfInfo.getInstance().setTenantName(bean.getUserinfo().getLoginTenantName());
                        MySelfInfo.getInstance().writeToCache(App.getInstance());
                        mView.showCharacterInfo(bean);
                    }
                });

             add(subscription);

    }
}
