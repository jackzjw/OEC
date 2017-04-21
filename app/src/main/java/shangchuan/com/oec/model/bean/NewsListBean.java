package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/21.
 */

public class NewsListBean {

    private int Id  ;
    private String  NewsTitle ;
    private int  ClassId ;
    private String ClassName ;
    private int ReadStatus  ;
    private String  Info;
    private String CreateUserId ;
    private String CreateTime ;

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

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int classId) {
        ClassId = classId;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public int getReadStatus() {
        return ReadStatus;
    }

    public void setReadStatus(int readStatus) {
        ReadStatus = readStatus;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
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
