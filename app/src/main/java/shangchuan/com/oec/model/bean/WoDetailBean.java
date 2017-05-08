package shangchuan.com.oec.model.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sg280 on 2017/4/24.
 */

public class WoDetailBean implements Serializable{
    private int Id ;
    private int ClassId  ;
    private String ClassNameA ;
    private String ClassNameB ;
    private int OrderStatus  ;
    private int OrderFlag  ;
    private String OrderTitle ;
    private String  OrderContent;
    private int DealStatus  ;
    private int ReStatus  ;
    private ArrayList<ProcessListBean> listP;
    private ArrayList<AttchmentBean> listA;
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

    public String getOrderContent() {
        return OrderContent;
    }

    public void setOrderContent(String orderContent) {
        OrderContent = orderContent;
    }

    public int getDealStatus() {
        return DealStatus;
    }

    public void setDealStatus(int dealStatus) {
        DealStatus = dealStatus;
    }

    public int getReStatus() {
        return ReStatus;
    }

    public void setReStatus(int reStatus) {
        ReStatus = reStatus;
    }

    public ArrayList<ProcessListBean> getListP() {
        return listP;
    }

    public void setListP(ArrayList<ProcessListBean> listP) {
        this.listP = listP;
    }

    public ArrayList<AttchmentBean> getListA() {
        return listA;
    }

    public void setListA(ArrayList<AttchmentBean> listA) {
        this.listA = listA;
    }
}
