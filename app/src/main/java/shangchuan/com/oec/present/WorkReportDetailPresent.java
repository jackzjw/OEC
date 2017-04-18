package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.AttchmentBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.WorkReportDetailsBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.WorkReportDeatailsContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/18.
 */

public class WorkReportDetailPresent extends RxPresent<WorkReportDeatailsContract.View> implements WorkReportDeatailsContract.Present {
   private RetrofitHelper mHelper;
    private List<String> imgUrls=new ArrayList<>();
    private List<AttchmentBean> docList=new ArrayList<>();
    @Inject
    public WorkReportDetailPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }


    @Override
    public void getWrDetails(int id) {
        Subscription subscription = mHelper.getApiSevice().getWorkReportDeatails(id, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<WorkReportDetailsBean>>scheduleRxHelper())
                .compose(RxUtil.<WorkReportDetailsBean>handleResult()).subscribe(new CommonSubscriber<WorkReportDetailsBean>(mView) {
                    @Override
                    public void onNext(WorkReportDetailsBean bean) {
                             mView.showContent(bean);
                        for(AttchmentBean item:bean.getAttachmentList_new()){
                            if(item.getAttType().equals("jpg")||item.getAttType().equals("mp4")){
                                imgUrls.add(item.getUrl());
                            }else if(item.getAttType().equals("txt")||item.getAttType().equals("doc")
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

}
