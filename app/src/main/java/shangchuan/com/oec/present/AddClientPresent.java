package shangchuan.com.oec.present;

import java.util.HashMap;

import javax.inject.Inject;

import rx.Subscription;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.AddClientContract;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.widget.CommonSubscriber;

/**
 * Created by sg280 on 2017/4/11.
 */

public class AddClientPresent extends RxPresent<AddClientContract.View> implements AddClientContract.Present {

    private RetrofitHelper mHelper;
    @Inject
    public AddClientPresent(RetrofitHelper helper){
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


}
