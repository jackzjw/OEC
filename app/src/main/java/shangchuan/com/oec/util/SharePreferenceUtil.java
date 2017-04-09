package shangchuan.com.oec.util;

import android.content.Context;
import android.content.SharedPreferences;

import shangchuan.com.oec.app.App;
import shangchuan.com.oec.app.Constants;


/**
 * Created by sg280 on 2017/3/28.
 */

public class SharePreferenceUtil {

    private static SharedPreferences getAppSp(){
        return App.getInstance().getSharedPreferences(Constants.SHAREPREFERENCE_NAME, Context.MODE_PRIVATE);
    }
    public static void setUserNick(String nick){
       getAppSp().edit().putString(Constants.USER_NICK_NAME,nick).commit();
    }
    public static String getUserNick(){
      return   getAppSp().getString(Constants.USER_NICK_NAME,"");
    }
    public static void setUserAvater(String avaterUrl){
        getAppSp().edit().putString(Constants.USER_AVATER,avaterUrl).commit();
    }
    public static String getUserAvater(){
        return getAppSp().getString(Constants.USER_AVATER,"");
    }
    public static void setUserTel(String tel){
        getAppSp().edit().putString(Constants.USER_TELPHONE_NUMBER,tel).commit();
    }
    public static String getUserTel(){
        return getAppSp().getString(Constants.USER_TELPHONE_NUMBER,"");
    }
    public static void setLogin(boolean isLogin){
        getAppSp().edit().putBoolean(Constants.IS_LOGIN,isLogin).commit();
    }
    public static boolean getLoginStatus(){
        return getAppSp().getBoolean(Constants.IS_LOGIN,false);
    }
    public static void setUserId(int userId){
        getAppSp().edit().putInt(Constants.USER_ID,userId).commit();
    }
    public static int getUserId(){
        return getAppSp().getInt(Constants.USER_ID,0);
    }
    public static void setTenantId(int tenantId){
        getAppSp().edit().putInt(Constants.CURRENT_TENANT_ID,tenantId).commit();
    }
    public static int getTenantId(){
        return getAppSp().getInt(Constants.CURRENT_TENANT_ID,0);
    }

    public static void setUserPwd(String pwd){
        getAppSp().edit().putString(Constants.USER_PASSWORD,pwd).commit();
    }
    public static String getUserPwd(){
        return getAppSp().getString(Constants.USER_PASSWORD,"");
    }

}
