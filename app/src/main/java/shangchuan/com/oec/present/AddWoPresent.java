package shangchuan.com.oec.present;

import com.yalantis.ucrop.entity.LocalMedia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
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
    private String[] fileNames;
    private String fileName;
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
                            if(item.getPid()==0){
                                parentsClass.add(item.getClassName());
                            }
                            if(item.getPid()==1){
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
    private int pid;
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
    public int getChildId(String childTitles) {
        //上传时根据子类型名字取出id
        for (WoClassBean item:mTotalList
                ) {
        if(item.getClassName()==childTitles){
            return item.getId();
        }
        }
        return 0;
    }

    @Override
    public void submitWo(int bid, int flag, String orderTitle, String orderContent, int[] handlers, String[] filesName) {
        Subscription subscription = mHelper.getApiSevice().submitWorkOrder(bid, flag, orderTitle, orderContent, handlers, filesName, SaveToken.mToken)
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
    public void upLoadFile(List<LocalMedia> selectMedia) {
        if(selectMedia.isEmpty()) return;
        fileNames=new String[selectMedia.size()];
        for(int i=0;i<selectMedia.size();i++){
            String path;
            String filename;
            if(selectMedia.get(i).isCompressed()){
                path=selectMedia.get(i).getCompressPath();
                filename=i+".jpg";
            }else {
                path=selectMedia.get(i).getPath();
                filename=i+".mp4";
            }
            mHelper.doFile("Attachment/WO/", path,filename, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //    LoadingView.Dismiss();
                    e.printStackTrace();
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if(response.isSuccessful()){
                        //   LoadingView.Dismiss();
                        fileName=response.body().string();
                        LogUtil.i("返回值="+response.body().string());
                    }else{
                        LogUtil.i(response.message());
                    }
                }
            });
           fileNames[i]=fileName;
        }
         mView.upLoadSuccess(fileNames);
    }
}
