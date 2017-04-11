package shangchuan.com.oec.model.bean;

import java.io.Serializable;

/**
 * Created by sg280 on 2017/4/10.
 */

public class ContactListBean implements Serializable{

    private int Id;
    private int CustomerId;
    private String ContactName ;
    private String ContactDept ;
    private String ContactTitle ;
    private String  ContactTel;
    private String  ContactEmail;
    private String  ContactWX;
    private String  ContactBirthday;
    private String Remark ;
    private String  CreateUserId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

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

    public String getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(String createUserId) {
        CreateUserId = createUserId;
    }
}
