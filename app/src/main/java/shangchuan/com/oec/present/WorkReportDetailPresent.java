package shangchuan.com.oec.present;

import android.app.Activity;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Subscription;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.AttchmentBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.model.bean.WorkReportDetailsBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.WorkReportDeatailsContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

import static shangchuan.com.oec.model.http.RetrofitHelper.makeRootDirectory;

/**
 * Created by sg280 on 2017/4/18.
 */

public class WorkReportDetailPresent extends RxPresent<WorkReportDeatailsContract.View> implements WorkReportDeatailsContract.Present {
    private RetrofitHelper mHelper;
    private List<AttchmentBean> imgUrls = new ArrayList<>();
    private List<AttchmentBean> docList = new ArrayList<>();
    private Activity mActivity;

    @Inject
    public WorkReportDetailPresent(RetrofitHelper helper, Activity activity) {
        this.mHelper = helper;
        this.mActivity = activity;
    }


    @Override
    public void getWrDetails(int id, int type) {
        Subscription subscription = mHelper.getApiSevice().getWorkReportDeatails(id, type, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<WorkReportDetailsBean>>scheduleRxHelper())
                .compose(RxUtil.<WorkReportDetailsBean>handleResult()).subscribe(new CommonSubscriber<WorkReportDetailsBean>(mView) {
                    @Override
                    public void onNext(WorkReportDetailsBean bean) {
                        mView.showContent(bean);
                        for (AttchmentBean item : bean.getAttachmentList_new()) {
                            if (item.getAttType().equals("jpg") || item.getAttType().equals("mp4")) {
                                imgUrls.add(item);
                            } else if (item.getAttType().equals("xls") || item.getAttType().equals("doc")
                                    || item.getAttType().equals("pdf")) {
                                docList.add(item);
                            } else {
                                ToastUtil.show("未知类型=" + item.getAttType());
                            }
                        }

                        if (!imgUrls.isEmpty()) {
                            mView.showImgUrls(imgUrls);
                        }
                        if (!docList.isEmpty()) {
                            mView.showFileResourse(docList);
                        }

                    }
                });
        add(subscription);
    }


    @Override
    public void downloadFile(String url) {
        final String  fileName=url.substring(url.lastIndexOf("/")+1,url.length());
        final String  fileDir= Environment.getExternalStorageDirectory()+ Constants.OEC_VEDIO_PATH;
        //如果之前已经下载过，则直接显示
        File[] files=new File(fileDir).listFiles();
        for(File file:files){
            if(file.getName().equals(fileName)){
                LogUtil.i(fileDir+fileName);
                mView.openFile(fileDir+"/"+fileName);
                return;
            }
        }
        mHelper.downFile(url, new Callback() {
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
                LogUtil.i(fileDir+fileName);
                mView.openFile(fileDir+"/"+fileName);
            }

        });
    }

    @Override
    public void oaDealResult(int orderId, int resultId, String remark, String toUserId) {
        Subscription subscription = mHelper.getApiSevice().wrDealResult(orderId, resultId, remark, toUserId, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<WoSuccessBean>>scheduleRxHelper())
                .compose(RxUtil.<WoSuccessBean>handleResult()).subscribe(new CommonSubscriber<WoSuccessBean>(mView) {
                    @Override
                    public void onNext(WoSuccessBean bean) {
                         mView.dealSuccess();
                    }
                });
        add(subscription);
    }

    @Override
    public void delWR(int id) {
        Subscription subscription = mHelper.getApiSevice().delWR(id,SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<WoSuccessBean>>scheduleRxHelper())
                .compose(RxUtil.<WoSuccessBean>handleResult()).subscribe(new CommonSubscriber<WoSuccessBean>(mView) {
                    @Override
                    public void onNext(WoSuccessBean bean) {
                        mView.deleteSucc();
                    }
                });
        add(subscription);
    }

}
