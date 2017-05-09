package shangchuan.com.oec.present;

import com.yalantis.ucrop.entity.LocalMedia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import shangchuan.com.oec.model.bean.WoClassBasicBean;
import shangchuan.com.oec.model.bean.WoClassBean;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.AddWoContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/6.
 */

public class AddWoPresent extends RxPresent<AddWoContract.View> implements AddWoContract.Present {
   private RetrofitHelper mHelper;
    private List<WoClassBean> mTotalList=new ArrayList<>();
     private List<String> childList=new ArrayList<>();


    @Inject
    public AddWoPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }

    @Override
    public void getWoClassType() {
        Subscription subscription = mHelper.getApiSevice().getWoType(SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<WoClassBasicBean>>scheduleRxHelper())
                .compose(RxUtil.<WoClassBasicBean>handleResult()).subscribe(new CommonSubscriber<WoClassBasicBean>(mView) {
                    @Override
                    public void onNext(WoClassBasicBean bean) {
                        mTotalList.addAll(bean.getClassInfo());
                        //取出父类型的名字
                        List<String> parentsClass=new ArrayList<String>();
                        for (WoClassBean item:bean.getClassInfo()
                             ) {
                            if(item.getPid().equals("0")){
                                parentsClass.add(item.getClassName());
                            }
                            if(item.getPid().equals("1")){
                                childList.add(item.getClassName());
                            }
                        }
                        int psize=parentsClass.size();
                        int csize=childList.size();
                        mView.showParentData((String[])parentsClass.toArray(new String[psize]));
                        mView.showChildData((String[])childList.toArray(new String[csize]));
                    }
                });
        add(subscription);

    }
    private String pid;
    @Override
    public void getChildData(String parentTitle) {
         //根据父类型id取出对应子类型的名字集合
        childList.clear();
        for(WoClassBean item:mTotalList
             ) {
            if(item.getClassName()==parentTitle){
                pid=item.getId();

            }
        }

        for (WoClassBean item:mTotalList
             ) {
            if(item.getPid()==pid){
                childList.add(item.getClassName());
            }
        }
        int size=childList.size();
        mView.showChildData((String[]) childList.toArray(new String[size]));
    }

    @Override
    public String getChildId(String childTitles) {
        //上传时根据子类型名字取出id
        for (WoClassBean item:mTotalList
                ) {
        if(item.getClassName().equals(childTitles)){
            return item.getId();
        }
        }
        return "";
    }

    @Override
    public void submitWo(HashMap<String,Object> hashMap) {

        Subscription subscription = mHelper.getApiSevice().submitWorkOrder(hashMap)
                .compose(RxUtil.<HttpDataResult<WoSuccessBean>>scheduleRxHelper())
                .compose(RxUtil.<WoSuccessBean>handleResult()).subscribe(new CommonSubscriber<WoSuccessBean>(mView) {
                    @Override
                    public void onNext(WoSuccessBean bean) {
                        mView.showSuccess();
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
            mHelper.doMultiFile("Attachment/WO/",builder, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //    LoadingView.Dismiss();
                    e.printStackTrace();
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if(response.isSuccessful()){
                        //   LoadingView.Dismiss();
                        mView.upLoadSuccess(response.body().string());
                    }else{
                        mView.showError(response.message());
                        LogUtil.i(response.message());
                    }
                }
            });

        }


}
