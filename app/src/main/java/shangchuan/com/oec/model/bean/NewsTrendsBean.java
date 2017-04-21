package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/21.
 */

public class NewsTrendsBean {
    private OaBasicItemBean<NewsListBean> news;
    private OaBasicItemBean<TrendsListBean> trends;

    public OaBasicItemBean<NewsListBean> getNews() {
        return news;
    }

    public void setNews(OaBasicItemBean<NewsListBean> news) {
        this.news = news;
    }

    public OaBasicItemBean<TrendsListBean> getTrends() {
        return trends;
    }

    public void setTrends(OaBasicItemBean<TrendsListBean> trends) {
        this.trends = trends;
    }
}
