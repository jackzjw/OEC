package shangchuan.com.oec.model.http;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.model.bean.MySelfInfo;
import shangchuan.com.oec.util.LogUtil;

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
        LogUtil.i("mime-type="+contentTypeFor);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
    /**
     * 下载文件
     * @param url
     * @param fileDir
     * @param fileName
     */
    public static void downFile(String url, final String fileDir, final String fileName) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call =okhttpclient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                makeRootDirectory(fileDir);
                try {
                    is = response.body().byteStream();
                    File file = new File(fileDir, fileName);
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) is.close();
                    if (fos != null) fos.close();
                }
            }
        });
    }
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {

        }
    }
}
