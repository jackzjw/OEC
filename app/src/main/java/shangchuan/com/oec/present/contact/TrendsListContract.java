package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.TrendsListBean;

/**
 * Created by sg280 on 2017/4/26.
 */

public interface TrendsListContract {
    interface  View extends BaseView{
        void showContent(List<TrendsListBean> bean);
        void showMoreContent(List<TrendsListBean> bean,int start,int end);
    }
    interface Present extends BasePresent<View>{
        void getTrendList(int jobType);
        void getMoreTrend(int jobType);
    }
}
