package shangchuan.com.oec.model.http;


import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.model.bean.MySelfInfo;

/**
 * Created by sg280 on 2016/12/1.
 */

public class RetrofitHelper {
    private static OkHttpClient okhttpclient=null;
    private static HttpService apiService=null;


    public RetrofitHelper(){
        init();
    }

    private void init() {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        okhttpBuilder.connectTimeout(20, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(20, TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(20, TimeUnit.SECONDS);
        okhttpBuilder.retryOnConnectionFailure(true);
        okhttpclient=okhttpBuilder.build();
    }
    public static HttpService getApiSevice(){
        if(apiService==null) {
            apiService = new Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okhttpclient).
                    addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build().create(HttpService.class);
        }
        return apiService;
    }
    public static void doFile(String url, String path, String fileName, Callback callback){

        //判断文件类型
        MediaType MEDIA_TYPE=MediaType.parse(judgeType(path));
        //创建文件参数
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart(MEDIA_TYPE.type(),fileName, RequestBody.create(MEDIA_TYPE,new File(path)))
                .addFormDataPart("tenantid", MySelfInfo.getInstance().getTenantId()+"")
                .addFormDataPart("filepath",url);


        Request request=new Request.Builder().url(Constants.FILE_BASE_URL).post(builder.build()).build();
        Call call=okhttpclient.newCall(request);
        call.enqueue(callback);
    }
    //多文件上传
    public static void doMultiFile(String url,MultipartBody.Builder builder,Callback callback){
        builder.addFormDataPart("tenantid", MySelfInfo.getInstance().getTenantId()+"")
                .addFormDataPart("filepath",url);;
        Request request=new Request.Builder().url(Constants.FILE_BASE_URL).post(builder.build()).build();
        Call call=okhttpclient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 根据文件路径判断MediaType
     *
     * @param path
     * @return
     */
    public static String judgeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }


}
