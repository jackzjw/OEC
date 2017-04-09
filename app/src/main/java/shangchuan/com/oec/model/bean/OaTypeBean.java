package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/3/30.
 */

public class OaTypeBean {

    private int Id;
    private String ClassName;
    private String ClassDesc;
    private int OrderBy;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getClassDesc() {
        return ClassDesc;
    }

    public void setClassDesc(String classDesc) {
        ClassDesc = classDesc;
    }

    public int getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(int orderBy) {
        OrderBy = orderBy;
    }

    @Override
    public String toString() {
        return "OaTypeBean{" +
                "Id=" + Id +
                ", ClassName='" + ClassName + '\'' +
                ", ClassDesc='" + ClassDesc + '\'' +
                ", OrderBy=" + OrderBy +
                '}';
    }
}
