package shangchuan.com.oec.present;

import com.yalantis.ucrop.entity.LocalMedia;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.AddApplyContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/17.
 */

public class AddWorkReportPresent extends RxPresent<AddApplyContract.View> implements AddApplyContract.Present {
    private RetrofitHelper mHelper;
    @Inject
    public AddWorkReportPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }


    @Override
    public void submitData(HashMap<String, Object> map) {
        Subscription subscription = mHelper.getApiSevice().addWorkReport(map, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<WoSuccessBean>>scheduleRxHelper())
                .compose(RxUtil.<WoSuccessBean>handleResult()).subscribe(new CommonSubscriber<WoSuccessBean>(mView) {
                    @Override
                    public void onNext(WoSuccessBean bean) {
                           mView.AddSuccess();
                    }
                });
               add(subscription);
    }

    @Override
    public void upLoadFile(List<LocalMedia> selectMedia,List<String> filePaths) {
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i < selectMedia.size(); i++) {
           String path;
            if (selectMedia.get(i).isCompressed()) {
                path = selectMedia.get(i).getCompressPath();

            } else {
                path = selectMedia.get(i).getPath();

            }
            String fileName=path.substring(path.lastIndexOf("/")+1,path.length());
            MediaType MEDIA_TYPE =MediaType.parse( mHelper.judgeType(path));
            builder.addFormDataPart(MEDIA_TYPE.type(),fileName, RequestBody.create(MEDIA_TYPE,new File(path)));
        }
        for(String path:filePaths){
            String fileName=path.substring(path.lastIndexOf("/")+1,path.length());
            MediaType MEDIA_TYPE =MediaType.parse( mHelper.judgeType(path));
            builder.addFormDataPart(MEDIA_TYPE.type(),fileName,RequestBody.create(MEDIA_TYPE,new File(path)));
        }
        mHelper.doMultiFile("Attachment/OAReport/", builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    mView.upLoadFileSuccess(response.body().string());
                }else {
                    mView.showError(response.message());
                    LogUtil.i(response.message());
                }
            }
        });
    }
}
