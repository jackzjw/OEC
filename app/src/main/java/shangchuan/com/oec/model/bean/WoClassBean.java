package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/1.
 */

public class WoClassBean {

    private String id;
    private String pid;
    private String className;
    public WoClassBean(){};
    public WoClassBean(String className){
        this.className=className;
    }
    public WoClassBean(String className,String id){
        this.className=className;
        this.id=id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "WoClassBean{" +
                "id=" + id +
                ", pid=" + pid +
                ", className='" + className + '\'' +
                '}';
    }
}
