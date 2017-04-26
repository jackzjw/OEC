package shangchuan.com.oec.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/4/26.
 */

public class NewsClassifyBean {

    private int Id;
    private int PId;
    private String ClassName;
    private String OrderBy;
    private ArrayList<NewsClassifyBean> list;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPId() {
        return PId;
    }

    public void setPId(int PId) {
        this.PId = PId;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(String orderBy) {
        OrderBy = orderBy;
    }

    public ArrayList<NewsClassifyBean> getList() {
        return list;
    }

    public void setList(ArrayList<NewsClassifyBean> list) {
        this.list = list;
    }
}
