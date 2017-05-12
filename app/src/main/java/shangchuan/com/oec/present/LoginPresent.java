package shangchuan.com.oec.present;

import android.app.Activity;
import android.content.Context;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.LoginInfoBean;
import shangchuan.com.oec.model.bean.MySelfInfo;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.LoginContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.MD5Util;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/3/27.
 */

public class LoginPresent extends RxPresent<LoginContract.View> implements LoginContract.Present {

    private RetrofitHelper mHelper;
    private Context mContext;
    @Inject
    public LoginPresent(RetrofitHelper mRetroHelper, Activity activity){
         this.mHelper=mRetroHelper;
         this.mContext=activity;
    }
    @Override
    public void login(final String tel, final String pwd){
        final String md5Pwd= MD5Util.getStringMD5(pwd+"OEC");
        Subscription subscription = mHelper.getApiSevice().login(tel, md5Pwd).
                compose(RxUtil.<HttpDataResult<LoginInfoBean>>scheduleRxHelper())
                .compose(RxUtil.<LoginInfoBean>handleResult()).subscribe(
                        new CommonSubscriber<LoginInfoBean>(mView) {
                            @Override
                            public void onNext(LoginInfoBean bean) {

                                LogUtil.i("token="+bean.toString());
                                SaveToken.mToken=bean.getToken();
                                MySelfInfo.getInstance().setUserId(bean.getUserinfo().getLoginUserId());
                                MySelfInfo.getInstance().setCurrentTenantId(bean.getUserinfo().getCurrentLoginId());

                                MySelfInfo.getInstance().setIslogin(true);
                                mView.loginSuccess();
                            }
                        }
                );

            add(subscription);
    }

}
