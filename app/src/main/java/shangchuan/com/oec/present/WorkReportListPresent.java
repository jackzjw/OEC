package shangchuan.com.oec.present;

import javax.inject.Inject;

import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.present.contact.OaListContract;

/**
 * Created by sg280 on 2017/4/18.
 */

public class WorkReportListPresent extends RxPresent<OaListContract.View> implements OaListContract.Present {

    private RetrofitHelper mHelper;
    @Inject
    public WorkReportListPresent(RetrofitHelper helper){
        this.mHelper=helper;
    }



    @Override
    public void getApplyType(String type) {

    }

    @Override
    public void getMoreContent() {

    }
}
