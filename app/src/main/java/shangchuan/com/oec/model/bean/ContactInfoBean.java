package shangchuan.com.oec.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sg280 on 2017/4/19.
 */

public class ContactInfoBean implements Parcelable {

    private String  ContactName;
    private String  ContactDept;
    private String  ContactTitle;
    private String  ContactTel;
    private String  ContactEmail;
    private String ContactWX ;
    private String  ContactBirthday;
    private String Remark;

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactDept() {
        return ContactDept;
    }

    public void setContactDept(String contactDept) {
        ContactDept = contactDept;
    }

    public String getContactTitle() {
        return ContactTitle;
    }

    public void setContactTitle(String contactTitle) {
        ContactTitle = contactTitle;
    }

    public String getContactTel() {
        return ContactTel;
    }

    public void setContactTel(String contactTel) {
        ContactTel = contactTel;
    }

    public String getContactEmail() {
        return ContactEmail;
    }

    public void setContactEmail(String contactEmail) {
        ContactEmail = contactEmail;
    }

    public String getContactWX() {
        return ContactWX;
    }

    public void setContactWX(String contactWX) {
        ContactWX = contactWX;
    }

    public String getContactBirthday() {
        return ContactBirthday;
    }

    public void setContactBirthday(String contactBirthday) {
        ContactBirthday = contactBirthday;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ContactName);
        dest.writeString(this.ContactDept);
        dest.writeString(this.ContactTitle);
        dest.writeString(this.ContactTel);
        dest.writeString(this.ContactEmail);
        dest.writeString(this.ContactWX);
        dest.writeString(this.ContactBirthday);
        dest.writeString(this.Remark);
    }

    public ContactInfoBean() {
    }

    protected ContactInfoBean(Parcel in) {
        this.ContactName = in.readString();
        this.ContactDept = in.readString();
        this.ContactTitle = in.readString();
        this.ContactTel = in.readString();
        this.ContactEmail = in.readString();
        this.ContactWX = in.readString();
        this.ContactBirthday = in.readString();
        this.Remark = in.readString();
    }

    public static final Parcelable.Creator<ContactInfoBean> CREATOR = new Parcelable.Creator<ContactInfoBean>() {
        @Override
        public ContactInfoBean createFromParcel(Parcel source) {
            return new ContactInfoBean(source);
        }

        @Override
        public ContactInfoBean[] newArray(int size) {
            return new ContactInfoBean[size];
        }
    };
}
