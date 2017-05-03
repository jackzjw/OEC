package shangchuan.com.oec.present;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Subscription;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.MyInfoBean;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.ModifyUserInfoContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/28.
 */

public class ModifyUserInfoPresent extends RxPresent<ModifyUserInfoContract.View> implements ModifyUserInfoContract.Present {
  private RetrofitHelper mHelper;
    @Inject
    public ModifyUserInfoPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }




    @Override
    public void getUserInfoDetail(int id) {
        Subscription subscription = mHelper.getApiSevice().userInfoDetail(id, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<MyInfoBean>>scheduleRxHelper())
                .compose(RxUtil.<MyInfoBean>handleResult()).subscribe(new CommonSubscriber<MyInfoBean>(mView) {
                    @Override
                    public void onNext(MyInfoBean bean) {
                     mView.showUserInfoDetail(bean);
                    }
                });
        add(subscription);
    }

    @Override
    public void modifyUserInfo(HashMap<String, Object> hashMap) {
        Subscription subscription=mHelper.getApiSevice().modifyUserInfo(hashMap,SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<WoSuccessBean>>scheduleRxHelper())
                .compose(RxUtil.<WoSuccessBean>handleResult()).subscribe(new CommonSubscriber<WoSuccessBean>(mView) {
                    @Override
                    public void onNext(WoSuccessBean bean) {
                        mView.modifySuccess();
                    }
                });
        add(subscription);
    }

    @Override
    public void upLoadImg(String imgPath) {
        String fileName=imgPath.substring(imgPath.lastIndexOf("/")+1,imgPath.length());
             mHelper.doFile(Constants.USER_AVATER_DIR_PATH, imgPath, fileName, new Callback() {
                 @Override
                 public void onFailure(Call call, IOException e) {
                     e.printStackTrace();
                    mView.showError("头像上传失败");
                 }

                 @Override
                 public void onResponse(Call call, Response response) throws IOException {
                       mView.upLoadSuccess(response.body().string());
                 }
             });
    }

}
