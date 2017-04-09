package shangchuan.com.oec.ui.user.activity;

import android.app.ProgressDialog;
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
import shangchuan.com.oec.util.SharePreferenceUtil;
import shangchuan.com.oec.util.ToastUtil;

public class LoginActivity extends BaseActivity<LoginPresent> implements LoginContract.View{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.et_phone_number)
    EditText mTelphone;
    @BindView(R.id.et_password)
    EditText mPwd;
    private ProgressDialog progressDialog;


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

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
    @OnClick(R.id.btn_login)
    void login(){
       String tel=mTelphone.getText().toString();
        String pwd=mPwd.getText().toString();
        if(TextUtils.isEmpty(pwd)){
            ToastUtil.shortShow("请输入密码");
            return;
        }
        if(!CommonUtil.isMobileNO(tel)){
            ToastUtil.shortShow("请输入正确的手机号");

            return;
        }
         progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("加载中");
        progressDialog.show();
        mPresent.login(tel,pwd);


    }

    @Override
    public void loginSuccess() {
       progressDialog.dismiss();
        ToastUtil.shortShow("登录成功");
        SharePreferenceUtil.setUserTel(mTelphone.getText().toString());
        SharePreferenceUtil.setUserPwd(mPwd.getText().toString());
        MySelfInfo.getInstance().getCache(this);
       startActivity(new Intent(this,OrganizationListActivity.class));
        finish();
    }

    @Override
    public void showError(String msg) {
        progressDialog.dismiss();
        ToastUtil.show(msg);
    }
}
