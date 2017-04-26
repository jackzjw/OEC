package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/26.
 */

public class NewsDetailsBean {
    private int Id;
    private String NewsTitle;
    private String NewsContent;
    private int HitCount;
    private String CreateUserId;
    private String CreateTime;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNewsTitle() {
        return NewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        NewsTitle = newsTitle;
    }

    public String getNewsContent() {
        return NewsContent;
    }

    public void setNewsContent(String newsContent) {
        NewsContent = newsContent;
    }

    public int getHitCount() {
        return HitCount;
    }

    public void setHitCount(int hitCount) {
        HitCount = hitCount;
    }

    public String getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(String createUserId) {
        CreateUserId = createUserId;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
}
