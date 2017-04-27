package shangchuan.com.oec.present;

import java.util.HashMap;
import java.util.HashSet;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.component.RxBus;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.model.event.RoleSelectEvent;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.AddUserContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/27.
 */

public class AddUserPresent extends RxPresent<AddUserContract.View> implements AddUserContract.Present {

    private RetrofitHelper mHelper;
    @Inject
    public AddUserPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }

    @Override
    public void addUser(HashMap<String, Object> hashMap) {
        Subscription subscription = mHelper.getApiSevice().addUser(hashMap, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<WoSuccessBean>>scheduleRxHelper())
                .compose(RxUtil.<WoSuccessBean>handleResult()).subscribe(new CommonSubscriber<WoSuccessBean>(mView) {
                    @Override
                    public void onNext(WoSuccessBean bean) {
                        mView.addSuccess();
                    }
                });
        add(subscription);
    }
    public void  registerRoleEvent(){
        Subscription sub = RxBus.getDefault().toDefaultObservable(RoleSelectEvent.class, new Action1<RoleSelectEvent>() {
            @Override
            public void call(RoleSelectEvent event) {
                mView.showRoleList(event.getRoleBeans());
            }
        });
        add(sub);
       // sub.unsubscribe();
    }

    public void registerDeptEvent(){
        Subscription sub = RxBus.getDefault().toDefaultObservable(HashSet.class, new Action1<HashSet>() {
            @Override
            public void call(HashSet set) {
                    mView.showDeptList(set);
            }
        });
      add(sub);
    }
}
