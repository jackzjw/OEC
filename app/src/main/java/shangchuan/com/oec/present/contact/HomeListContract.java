package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.NewsListBean;
import shangchuan.com.oec.model.bean.TrendsListBean;

/**
 * Created by sg280 on 2017/4/21.
 */

public interface HomeListContract {
    interface View extends BaseView{
        void showNewsList(List<NewsListBean> bean);
        void showTrendsList(List<TrendsListBean> bean);
        void updateReadStatus(int position);
    }
    interface Present extends BasePresent<View>{
        void getNewsList();
        void getTrendsList();
    }
}
