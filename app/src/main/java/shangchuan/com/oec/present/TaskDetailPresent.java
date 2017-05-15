package shangchuan.com.oec.present;

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
import shangchuan.com.oec.model.bean.ResultBean;
import shangchuan.com.oec.model.bean.TaskDetailsBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.TaskDetailContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

import static shangchuan.com.oec.model.http.RetrofitHelper.makeRootDirectory;

/**
 * Created by sg280 on 2017/5/15.
 */

public class TaskDetailPresent extends RxPresent<TaskDetailContract.View> implements TaskDetailContract.Present {
   private RetrofitHelper mHelper;
    @Inject
    public TaskDetailPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }
    @Override
    public void getTaskDetails(String id) {
        Subscription subscription=mHelper.getApiSevice().getTaskDetails(id, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ResultBean<TaskDetailsBean>>>scheduleRxHelper())
                .compose(RxUtil.<ResultBean<TaskDetailsBean>>handleResult()).subscribe(new CommonSubscriber<ResultBean<TaskDetailsBean>>(mView) {
                    @Override
                    public void onNext(ResultBean<TaskDetailsBean> bean) {
                        List<AttchmentBean> fileList=new ArrayList<AttchmentBean>();
                        List<AttchmentBean> imgList=new ArrayList<AttchmentBean>();
                        for(AttchmentBean item:bean.getResult().getAttachment_list()){
                            String attType=item.getAttType().toLowerCase();
                            if(attType.equals("pdf")||attType.equals("xls")||attType.equals("doc")){
                                fileList.add(item);
                            }else if(attType.equals("jpg")||attType.equals("mp4")||attType.equals("png")){
                                imgList.add(item);
                            }
                        }
                        mView.showContent(bean.getResult());
                        mView.showFileContent(fileList);
                        mView.showImgContent(imgList);
                        mView.showRemark(bean.getResult().getComment_list());
                    }
                });
        add(subscription);
    }

    @Override
    public void sendRemark(String taskId, String Pid, String toUserId, String remark) {

    }

    @Override
    public void downFile(String url) {
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
}
