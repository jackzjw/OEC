package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.OaBasicItemBean;
import shangchuan.com.oec.model.bean.WoClassBasicBean;
import shangchuan.com.oec.model.bean.WoClassBean;
import shangchuan.com.oec.model.bean.WoListBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.WoListContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/5.
 */

public class WoListPresent extends RxPresent<WoListContract.View> implements WoListContract.Present {

    private  ArrayList<WoClassBean> childlist;
    private RetrofitHelper mHelper;
    private List<WoClassBean> totalClassList;
    private int currentPage=1;
    private List<WoListBean> contentList;
    @Inject
    public WoListPresent(RetrofitHelper helper){
        this.mHelper=helper;
        totalClassList=new ArrayList<>();
        childlist = new ArrayList<WoClassBean>();
        contentList=new ArrayList<>();
    }



    @Override
    public void getClassName() {
        Subscription subscription = mHelper.getApiSevice().getWoType(SaveToken.mToken)
          .compose(RxUtil.<HttpDataResult<WoClassBasicBean>>scheduleRxHelper())
                .compose(RxUtil.<WoClassBasicBean>handleResult()).subscribe(new CommonSubscriber<WoClassBasicBean>(mView) {
                    @Override
                    public void onNext(WoClassBasicBean bean) {
                             totalClassList.addAll(bean.getClassInfo());
                        ArrayList<WoClassBean> parentlist = new ArrayList<WoClassBean>();
                        for (WoClassBean item:bean.getClassInfo()
                             ) {
                            if(item.getPid().equals("0")){
                                parentlist.add(item);
                            }
                            if(item.getPid().equals("1")){
                                childlist.add(item);
                            }

                        }
                        //取出父类的集合
                      mView.showParentName(parentlist);
                        //显示默认的子类别集合
                        mView.showChildName(childlist);

                    }
                });
        add(subscription);
    }

    @Override
    public void getWoList(String aid, String bid, String orderStatus,String status) {
        currentPage=1;
        Subscription subscription = mHelper.getApiSevice().getWoList(aid, bid, orderStatus, status, currentPage++, SaveToken.mToken)
              .compose(RxUtil.<HttpDataResult<OaBasicItemBean<WoListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<WoListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<WoListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<WoListBean> bean) {
                        LogUtil.i("列表="+bean.getItems());
                        contentList.addAll(bean.getItems());
                        mView.showContentList(bean.getItems());
                    }
                });
        add(subscription);
    }

    @Override
    public void getMoreWoList(String aid, String bid, String orderStatus, String status) {
        Subscription subscription = mHelper.getApiSevice().getWoList(aid, bid, orderStatus, status, currentPage++, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<OaBasicItemBean<WoListBean>>>scheduleRxHelper())
                .compose(RxUtil.<OaBasicItemBean<WoListBean>>handleResult()).subscribe(new CommonSubscriber<OaBasicItemBean<WoListBean>>(mView) {
                    @Override
                    public void onNext(OaBasicItemBean<WoListBean> bean) {
                        contentList.addAll(bean.getItems());
                        mView.showMoreContent(bean.getItems(),contentList.size(),contentList.size()+bean.getSize());
                    }
                });
        add(subscription);
    }

    @Override
    public void parentToChild(String pid) {
           childlist.clear();
        for (WoClassBean item:totalClassList
             ) {
            if(item.getPid().equals(pid)){
                childlist.add(item);
            }
        }
          mView.showChildName(childlist);
    }


}
