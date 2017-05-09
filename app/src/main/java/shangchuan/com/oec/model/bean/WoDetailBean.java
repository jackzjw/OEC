package shangchuan.com.oec.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by sg280 on 2017/4/24.
 */

public class WoDetailBean implements Parcelable {

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
    private String CreateUserName;
    private String CreateAvatar;
    private String OrderNo;

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getCreateAvatar() {
        return CreateAvatar;
    }

    public void setCreateAvatar(String createAvatar) {
        CreateAvatar = createAvatar;
    }

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String createUserName) {
        CreateUserName = createUserName;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeInt(this.ClassId);
        dest.writeString(this.ClassNameA);
        dest.writeString(this.ClassNameB);
        dest.writeInt(this.OrderStatus);
        dest.writeInt(this.OrderFlag);
        dest.writeString(this.OrderTitle);
        dest.writeString(this.OrderContent);
        dest.writeInt(this.DealStatus);
        dest.writeInt(this.ReStatus);
        dest.writeList(this.listP);
        dest.writeTypedList(this.listA);
        dest.writeString(this.CreateTime);
    }

    public WoDetailBean() {
    }

    protected WoDetailBean(Parcel in) {
        this.Id = in.readInt();
        this.ClassId = in.readInt();
        this.ClassNameA = in.readString();
        this.ClassNameB = in.readString();
        this.OrderStatus = in.readInt();
        this.OrderFlag = in.readInt();
        this.OrderTitle = in.readString();
        this.OrderContent = in.readString();
        this.DealStatus = in.readInt();
        this.ReStatus = in.readInt();
        this.listP = new ArrayList<ProcessListBean>();
        in.readList(this.listP, ProcessListBean.class.getClassLoader());
        this.listA = in.createTypedArrayList(AttchmentBean.CREATOR);
        this.CreateTime = in.readString();
    }

    public static final Parcelable.Creator<WoDetailBean> CREATOR = new Parcelable.Creator<WoDetailBean>() {
        @Override
        public WoDetailBean createFromParcel(Parcel source) {
            return new WoDetailBean(source);
        }

        @Override
        public WoDetailBean[] newArray(int size) {
            return new WoDetailBean[size];
        }
    };
}
