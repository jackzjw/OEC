package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.WoClassBasicBean;
import shangchuan.com.oec.model.bean.WoClassBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.AddWoContract;
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

    }
}
