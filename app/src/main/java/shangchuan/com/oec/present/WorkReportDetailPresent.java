package shangchuan.com.oec.present;

import android.Manifest;
import android.app.Activity;
import android.os.Environment;

import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.AttchmentBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.WorkReportDetailsBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.WorkReportDeatailsContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/18.
 */

public class WorkReportDetailPresent extends RxPresent<WorkReportDeatailsContract.View> implements WorkReportDeatailsContract.Present {
   private RetrofitHelper mHelper;
    private List<AttchmentBean> imgUrls=new ArrayList<>();
    private List<AttchmentBean> docList=new ArrayList<>();
    private Activity mActivity;
    @Inject
    public WorkReportDetailPresent(RetrofitHelper helper, Activity activity){
        this.mHelper=helper;
        this.mActivity=activity;
    }


    @Override
    public void getWrDetails(int id,int type) {
        Subscription subscription = mHelper.getApiSevice().getWorkReportDeatails(id, type,SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<WorkReportDetailsBean>>scheduleRxHelper())
                .compose(RxUtil.<WorkReportDetailsBean>handleResult()).subscribe(new CommonSubscriber<WorkReportDetailsBean>(mView) {
                    @Override
                    public void onNext(WorkReportDetailsBean bean) {
                             mView.showContent(bean);
                        LogUtil.i(bean.getAttachmentList_new().toString());
                        for(AttchmentBean item:bean.getAttachmentList_new()){
                            if(item.getAttType().equals("jpg")){
                                imgUrls.add(item);
                            }else if(item.getAttType().equals("mp4")){
                                String innerSdPath= Environment.getExternalStorageDirectory().getPath();
                                String path=innerSdPath+Constants.OEC_VEDIO_PATH+"/"+item.getOldAttFileName()+".mp4";
                                downloadVedio(item.getUrl(),item.getOldAttFileName()+".mp4");
                                item.setUrl(path);
                                imgUrls.add(item);
                            }
                            else if(item.getAttType().equals("txt")||item.getAttType().equals("doc")
                                    ||item.getAttType().equals("pdf")){
                                docList.add(item);
                            }else {
                                ToastUtil.show("未知类型="+item.getAttType());
                            }
                        }

                        if(!imgUrls.isEmpty()){
                             mView.showImgUrls(imgUrls);
                        }
                        if(!docList.isEmpty()){
                            mView.showFileResourse(docList);
                        }

                    }
                });
    add(subscription);
    }
  private void downloadVedio(final String url, final String filename){
      new RxPermissions(mActivity).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
          @Override
          public void call(Boolean aBoolean) {
              if(aBoolean){
                  String fileDir=Environment.getExternalStorageDirectory()+Constants.OEC_VEDIO_PATH+"/";
                  mHelper.downFile(url,fileDir,filename);
              }
          }
      });
  }
}
