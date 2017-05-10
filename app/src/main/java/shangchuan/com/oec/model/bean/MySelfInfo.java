package shangchuan.com.oec.model.bean;

import android.content.Context;
import android.content.SharedPreferences;

import shangchuan.com.oec.app.Constants;

/**
 * Created by sg280 on 2016-07-25.
 *
 */
public class MySelfInfo {

    private static final String TAG = MySelfInfo.class.getSimpleName();
    private int userId;

    private String nickName;    // 呢称
    private String avatar;      // 头像
     private int tenantId ;   // 机构ID
    private String tenantName;//机构名称

    private boolean islogin;
    private String phone;
    private String pwd;
    private String trueName;

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean islogin() {
        return islogin;
    }

    public void setIslogin(boolean islogin) {
        this.islogin = islogin;
    }



    private static MySelfInfo ourInstance = new MySelfInfo();

    public static MySelfInfo getInstance() {

        return ourInstance;
    }





    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    //将个人信息写进数据库
    public void writeToCache(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Constants.SHAREPREFERENCE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(Constants.USER_ID,userId);
        editor.putInt(Constants.CURRENT_TENANT_ID, tenantId);
        editor.putString(Constants.USER_NICK_NAME, nickName);
        editor.putString(Constants.USER_AVATER, avatar);
        editor.putString(Constants.USER_TELPHONE_NUMBER, phone);
        editor.putString(Constants.CURRENT_TENANT_NAME,tenantName);
        editor.putBoolean(Constants.IS_LOGIN, islogin);
        editor.apply();
    }

    public void clearCache(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Constants.SHAREPREFERENCE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public void getCache(Context context) {
        SharedPreferences sharedata = context.getSharedPreferences(Constants.SHAREPREFERENCE_NAME,
                Context.MODE_PRIVATE);
        userId= sharedata.getInt(Constants.USER_ID, 0);
        tenantId = sharedata.getInt(Constants.CURRENT_TENANT_ID, 0);
        nickName = sharedata.getString(Constants.USER_NICK_NAME, "");
        avatar = sharedata.getString(Constants.USER_AVATER, "");
        phone = sharedata.getString(Constants.USER_TELPHONE_NUMBER, "");
        islogin=sharedata.getBoolean(Constants.IS_LOGIN, false);
        tenantName=sharedata.getString(Constants.CURRENT_TENANT_NAME,"");
        pwd=sharedata.getString(Constants.USER_PASSWORD,"");
//sdfsefs
    }


}
