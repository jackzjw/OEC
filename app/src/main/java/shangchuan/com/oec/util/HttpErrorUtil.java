package shangchuan.com.oec.util;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import shangchuan.com.oec.component.ApiException;

/**
 * Created by sg280 on 2017/3/27.
 */

public class HttpErrorUtil
{
    public static void handCommonError(Throwable e){
        if(e instanceof IOException){
            ToastUtil.show("网络连接失败");
        }else if(e instanceof HttpException){
            ToastUtil.show("服务器连接失败");
        }else if(e instanceof ApiException){
            //APIException自行处理
        }else {
            ToastUtil.show("未知错误");
        }
    }
}
