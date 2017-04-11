package shangchuan.com.oec.present;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.ClientDetailsBasicBean;
import shangchuan.com.oec.model.bean.ContactListBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.ModifyClientDetailsContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.SaveToken;

/**
 * Created by sg280 on 2017/4/11.
 */

public class ModifyClientDetailPresent extends RxPresent<ModifyClientDetailsContract.View> implements ModifyClientDetailsContract.Present {


    private RetrofitHelper mHelper;
    @Inject
    public ModifyClientDetailPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }
    @Override
    public void submitClientData(String CustomerType, String CustomerName, String CustomerShortName, String CustomerCode, String CustomerTel, String CustomerCity, String CustomerAddress, String CustomerPostcode, String Remark, int UserId, int GroupId) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("CustomerType",CustomerType);
        map.put("CustomerName",CustomerName);
        map.put("CustomerShortName",CustomerShortName);
        map.put("CustomerCode",CustomerCode);
        map.put("CustomerTel",CustomerTel);
        map.put("CustomerCity",CustomerCity);
        map.put("CustomerAddress",CustomerAddress);
        map.put("Remark",Remark);
        map.put("UserId",UserId);
        map.put("GroupId",GroupId);
        Subscription subscription = mHelper.getApiSevice().addClient(map).
                compose(RxUtil.<HttpDataResult<WoSuccessBean>>scheduleRxHelper())
                .compose(RxUtil.<WoSuccessBean>handleResult()).subscribe(new CommonSubscriber<WoSuccessBean>(mView) {
                    @Override
                    public void onNext(WoSuccessBean bean) {
                        mView.saveSuccess(bean);
                    }
                });
        add(subscription);
    }

    @Override
    public void getClientDetails(int id) {
        Subscription subscription = mHelper.getApiSevice().getClientDetails(id, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ClientDetailsBasicBean>>scheduleRxHelper())
                .compose(RxUtil.<ClientDetailsBasicBean>handleResult()).subscribe(new CommonSubscriber<ClientDetailsBasicBean>(mView) {
                    @Override
                    public void onNext(ClientDetailsBasicBean bean) {
                        mView.showContent(bean);
                    }
                });
        add(subscription);
    }

    @Override
    public void deleteContact(int id) {
        Subscription subscription = mHelper.getApiSevice().deleteContact(id, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<WoSuccessBean>>scheduleRxHelper())
                .compose(RxUtil.<WoSuccessBean>handleResult()).subscribe(new CommonSubscriber<WoSuccessBean>(mView) {
                    @Override
                    public void onNext(WoSuccessBean bean) {
                        mView.deleteSuccess();
                    }
                });
          add(subscription);
    }
    //获取联系人列表
    @Override
    public void getContactList(int id) {
        Subscription subscription = mHelper.getApiSevice().clientContactList(id, SaveToken.mToken)
    .compose(RxUtil.<HttpDataResult<List<ContactListBean>>>scheduleRxHelper())
                .compose(RxUtil.<List<ContactListBean>>handleResult()).subscribe(new CommonSubscriber<List<ContactListBean>>(mView) {
                    @Override
                    public void onNext(List<ContactListBean> list) {
                        mView.showContactList(list);
                    }
                });
              add(subscription);
    }



}
