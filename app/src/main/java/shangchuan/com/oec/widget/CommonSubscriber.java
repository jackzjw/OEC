package shangchuan.com.oec.widget;

import android.text.TextUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.component.ApiException;

/**
 * Created by codeest on 2017/2/23.
 */

public abstract class CommonSubscriber<T> extends Subscriber<T> {
    private BaseView mView;
    private String mErrorMsg;

    protected CommonSubscriber(BaseView view){
        this.mView = view;
    }

    protected CommonSubscriber(BaseView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null)
            return;
        e.printStackTrace();
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showError(mErrorMsg);
        } else if (e instanceof ApiException) {
            mView.showError(e.getMessage());
        } else if (e instanceof HttpException) {
            mView.showError("网络不可用ヽ(≧Д≦)ノ");
        } else if(e instanceof IOException){
            mView.showError("连接服务器失败ヽ(≧Д≦)ノ");
        }else if(e instanceof SocketTimeoutException) {
            mView.showError("网络连接超时");
        }else {
            mView.showError("未知错误ヽ(≧Д≦)ノ");
        }
    }
}
