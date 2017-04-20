package shangchuan.com.oec.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sg280 on 2017/3/31.
 */

public class AttchmentBean implements Parcelable {

   private  int Id ;
   private  int  TenantId ;
   private  int OrderId ;
   private  String AttType ;
   private  String AttFileName ;
   private  String  AttDir;
   private  String OldAttFileName ;
    private String Url;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTenantId() {
        return TenantId;
    }

    public void setTenantId(int tenantId) {
        TenantId = tenantId;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public String getAttType() {
        return AttType;
    }

    public void setAttType(String attType) {
        AttType = attType;
    }

    public String getAttFileName() {
        return AttFileName;
    }

    public void setAttFileName(String attFileName) {
        AttFileName = attFileName;
    }

    public String getAttDir() {
        return AttDir;
    }

    public void setAttDir(String attDir) {
        AttDir = attDir;
    }

    public String getOldAttFileName() {
        return OldAttFileName;
    }

    public void setOldAttFileName(String oldAttFileName) {
        OldAttFileName = oldAttFileName;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    @Override
    public String toString() {
        return "AttchmentBean{" +
                "Id=" + Id +
                ", TenantId=" + TenantId +
                ", OrderId=" + OrderId +
                ", AttType='" + AttType + '\'' +
                ", AttFileName='" + AttFileName + '\'' +
                ", AttDir='" + AttDir + '\'' +
                ", OldAttFileName='" + OldAttFileName + '\'' +
                ", Url='" + Url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeInt(this.TenantId);
        dest.writeInt(this.OrderId);
        dest.writeString(this.AttType);
        dest.writeString(this.AttFileName);
        dest.writeString(this.AttDir);
        dest.writeString(this.OldAttFileName);
        dest.writeString(this.Url);
    }

    public AttchmentBean() {
    }

    protected AttchmentBean(Parcel in) {
        this.Id = in.readInt();
        this.TenantId = in.readInt();
        this.OrderId = in.readInt();
        this.AttType = in.readString();
        this.AttFileName = in.readString();
        this.AttDir = in.readString();
        this.OldAttFileName = in.readString();
        this.Url = in.readString();
    }

    public static final Parcelable.Creator<AttchmentBean> CREATOR = new Parcelable.Creator<AttchmentBean>() {
        @Override
        public AttchmentBean createFromParcel(Parcel source) {
            return new AttchmentBean(source);
        }

        @Override
        public AttchmentBean[] newArray(int size) {
            return new AttchmentBean[size];
        }
    };
}
