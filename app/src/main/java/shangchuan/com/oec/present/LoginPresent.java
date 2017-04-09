package shangchuan.com.oec.present;

import android.app.Activity;
import android.content.Context;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.LoginInfoBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.LoginContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.SharePreferenceUtil;
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
    public void login(String tel,String pwd){
           LogUtil.i(tel+pwd);
        Subscription subscription = mHelper.getApiSevice().login(tel, pwd).
                compose(RxUtil.<HttpDataResult<LoginInfoBean>>scheduleRxHelper())
                .compose(RxUtil.<LoginInfoBean>handleResult()).subscribe(
                        new CommonSubscriber<LoginInfoBean>(mView) {
                            @Override
                            public void onNext(LoginInfoBean bean) {
                                LogUtil.i("token="+bean.getToken());
                                SaveToken.mToken=bean.getToken();
                                SharePreferenceUtil.setUserId(bean.getUserinfo().getLoginUserId());
                                SharePreferenceUtil.setLogin(true);
                                mView.loginSuccess();
                            }
                        }
                );

            add(subscription);
    }

}
