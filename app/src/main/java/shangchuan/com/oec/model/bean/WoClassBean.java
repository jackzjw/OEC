package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/1.
 */

public class WoClassBean {

    private int id;
    private int pid;
    private String className;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
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
