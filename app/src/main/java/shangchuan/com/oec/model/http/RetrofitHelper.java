package shangchuan.com.oec.model.http;



import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import shangchuan.com.oec.app.Constants;

/**
 * Created by sg280 on 2016/12/1.
 */

public class RetrofitHelper {
    private static OkHttpClient okhttpclient=null;
    private static HttpService apiService=null;
    private static FileHttpService fileHttpService=null;

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
    public static FileHttpService getFileApiService(){
        if(fileHttpService==null){
            fileHttpService=new Retrofit.Builder().baseUrl(Constants.FILE_BASE_URL).client(okhttpclient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create())
                    .build().create(FileHttpService.class);
        }
        return fileHttpService;
    }


}
