package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/4/1.
 */

public class WoListBean {
    private int Id;
    private int ClassId;
    private String ClassNameA ;
    private String ClassNameB ;
    private int OrderStatus;
    private int OrderFlag;
    private String  OrderTitle;
    private int hasAtt  ;
    private String CreateTime;

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int classId) {
        ClassId = classId;
    }

    public String getClassNameA() {
        return ClassNameA;
    }

    public void setClassNameA(String classNameA) {
        ClassNameA = classNameA;
    }

    public String getClassNameB() {
        return ClassNameB;
    }

    public void setClassNameB(String classNameB) {
        ClassNameB = classNameB;
    }

    public int getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        OrderStatus = orderStatus;
    }

    public int getOrderFlag() {
        return OrderFlag;
    }

    public void setOrderFlag(int orderFlag) {
        OrderFlag = orderFlag;
    }

    public String getOrderTitle() {
        return OrderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        OrderTitle = orderTitle;
    }

    public int getHasAtt() {
        return hasAtt;
    }

    public void setHasAtt(int hasAtt) {
        this.hasAtt = hasAtt;
    }
}
