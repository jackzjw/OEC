package shangchuan.com.oec.ui;

import android.content.Intent;

import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.MySelfInfo;
import shangchuan.com.oec.present.LoginPresent;
import shangchuan.com.oec.present.contact.LoginContract;
import shangchuan.com.oec.ui.user.activity.LoginActivity;
import shangchuan.com.oec.util.ToastUtil;

public class WelcomeActivity extends BaseActivity<LoginPresent> implements LoginContract.View {



    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initEventData() {
        MySelfInfo.getInstance().getCache(this);
        if(MySelfInfo.getInstance().islogin()){
            //帮用户自动登录

            mPresent.login(MySelfInfo.getInstance().getPhone(),MySelfInfo.getInstance().getPwd());
        }else {
            startActivity(new Intent(this,LoginActivity.class));
            overridePendingTransition(0,0);
            finish();
        }

    }

    @Override
    protected void initInject() {
         getActivityComponent().inject(this);
    }

    @Override
    public void loginSuccess() {

        startActivity(new Intent(this,MainActivity.class));
        overridePendingTransition(0,0);
        finish();
    }

    @Override
    public void showError(String msg) {

        ToastUtil.show(msg);
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}
