package shangchuan.com.oec.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import shangchuan.com.oec.ui.user.activity.LoginActivity;


/**
 * Created by sg280 on 2016-08-08.
 */
public class LoginUtil extends Activity{
    private int REQUEST_CODE_LOGIN = 1;
    static LoginCallback mCallback;
     private static boolean login=true;
    public interface LoginCallback {
        void onLogin();
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_CODE_LOGIN);
    }


    public static void checkLogin(Context context, LoginCallback callback) {
        //此处检查当前的登录状态
     //   boolean login = MySelfInfo.getInstance().islogin();
        if (login) {
            callback.onLogin();
        } else {
            mCallback = callback;
            Intent intent = new Intent(context, LoginUtil.class);
            context.startActivity(intent);
        }
    }

    public static void checkLogin(Context context, LoginCallback logged, LoginCallback callback) {
        //此处检查当前的登录状态
      //  boolean login = MySelfInfo.getInstance().islogin();
        if (login) {
            logged.onLogin();
        } else {
            mCallback = callback;
            Intent intent = new Intent(context, LoginUtil.class);
            context.startActivity(intent);
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_LOGIN && resultCode == RESULT_OK && mCallback != null) {
            mCallback.onLogin();
        }
        mCallback = null;
        finish();
    }

}
