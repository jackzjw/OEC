package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.NewsClassifyBean;
import shangchuan.com.oec.model.bean.NewsListBean;

/**
 * Created by sg280 on 2017/4/26.
 */

public interface NewListContract {
    interface View extends BaseView{
        void showContent(List<NewsListBean> bean);
        void showMoreContent(List<NewsListBean> bean,int start,int end);
        void showNewsClassify(NewsClassifyBean bean);
        void updateReadStatus(int position);
    }
    interface Present extends BasePresent<View>{
        void getNewsClassify();
        void getNewsList(String classId,int status,String keyword);
        void getMoreNews(String classid,int status,String keyword);
    }
}
