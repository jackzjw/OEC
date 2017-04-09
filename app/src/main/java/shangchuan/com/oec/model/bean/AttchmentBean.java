package shangchuan.com.oec.model.bean;

/**
 * Created by sg280 on 2017/3/31.
 */

public class AttchmentBean {

   private  int Id ;
   private  int  TenantId ;
   private  int OrderId ;
   private  String AttType ;
   private  String AttFileName ;
   private  String  AttDir;
   private  String OldAttFileName ;

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
}
