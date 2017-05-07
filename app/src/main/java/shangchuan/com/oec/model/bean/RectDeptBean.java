package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/5/6.
 */

public class RectDeptBean {
    //0:第一级，1：第二级，2：第三级
    public int floor;
    //部门名称
    public String deptName;
    //第三级的个数
    public int num;
    //描述
    public String remark;
    //id
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "RectDeptBean{" +
                "floor=" + floor +
                ", deptName='" + deptName + '\'' +
                ", num=" + num +
                ", remark='" + remark + '\'' +
                ", id=" + id +
                '}';
    }
}
