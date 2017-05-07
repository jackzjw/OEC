package shangchuan.com.oec.present;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.component.RxBus;
import shangchuan.com.oec.model.bean.GroupBasicBean;
import shangchuan.com.oec.model.bean.GroupListBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.RectDeptBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.GroupListContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/5/6.
 */

public class GroupListPresent extends RxPresent<GroupListContract.View> implements GroupListContract.Present {
  private RetrofitHelper mHelper;
    @Inject
    public GroupListPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }


    @Override
    public void getGroupList() {
        Subscription subscription=mHelper.getApiSevice().getOrGroupList(SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<GroupBasicBean>>scheduleRxHelper())
                .compose(RxUtil.<GroupBasicBean>handleResult()).subscribe(new CommonSubscriber<GroupBasicBean>(mView) {
                    @Override
                    public void onNext(GroupBasicBean bean) {
                        List<RectDeptBean> mList=new ArrayList<RectDeptBean>();
                        RectDeptBean firstFloor=new RectDeptBean();
                        firstFloor.setDeptName(bean.getTenantName());
                        firstFloor.setFloor(1);
                        mList.add(firstFloor);
                        for(GroupListBean item:bean.getGroup_list()){
                            RectDeptBean secondFloor=new RectDeptBean();
                            secondFloor.setFloor(2);
                            secondFloor.setDeptName(item.getGroupName());
                            secondFloor.setId(item.getId());
                            secondFloor.setRemark(item.getRemark());
                            secondFloor.setNum(item.getGroup_list().size());
                            mList.add(secondFloor);
                            for(GroupListBean items:item.getGroup_list()){
                                RectDeptBean thirdFloor=new RectDeptBean();
                                thirdFloor.setFloor(3);
                                thirdFloor.setDeptName(items.getGroupName());
                                thirdFloor.setId(items.getId());
                                thirdFloor.setRemark(items.getRemark());
                                mList.add(thirdFloor);
                            }
                        }
                        mView.showGroupList(mList);

                    }
                });
        add(subscription);

    }

    @Override
    public void registerRefresh() {
        Subscription subscription=RxBus.getDefault().toDefaultObservable(String.class, new Action1<String>() {
            @Override
            public void call(String s) {
                if(s.equals("addStructer")){
                    getGroupList();
                }
            }
        });
        add(subscription);

    }
}
