package shangchuan.com.oec.ui.user.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.MySelfInfo;
import shangchuan.com.oec.present.LoginPresent;
import shangchuan.com.oec.present.contact.LoginContract;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.LoadingView;

public class LoginActivity extends BaseActivity<LoginPresent> implements LoginContract.View{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.et_phone_number)
    EditText mTelphone;
    @BindView(R.id.et_password)
    EditText mPwd;


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("登录");
        initToolBar(mToolbar);

    }
  private String getTel(){
      return mTelphone.getText().toString().trim();
  }
    private String getPwd(){
        return mPwd.getText().toString().trim();
    }
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @OnClick(R.id.btn_login)
    void login(){

        if(TextUtils.isEmpty(getPwd())){
            ToastUtil.shortShow("请输入密码");
            return;
        }
        if(!CommonUtil.isMobileNO(getTel())){
            ToastUtil.shortShow("请输入正确的手机号");

            return;
        }
        LoadingView.showProgress(this);
        mPresent.login(getTel(),getPwd());


    }

    @Override
    public void loginSuccess() {
         LoadingView.dismissProgress();
        ToastUtil.shortShow("登录成功");
        MySelfInfo.getInstance().setPhone(getTel());
        MySelfInfo.getInstance().setPwd(getPwd());
        MySelfInfo.getInstance().writeToCache(this);
        MySelfInfo.getInstance().getCache(this);
        startActivity(new Intent(this,OrganizationListActivity.class));
        finish();
    }

    @Override
    public void showError(String msg) {
      LoadingView.dismissProgress();
        ToastUtil.show(msg);
    }
}
