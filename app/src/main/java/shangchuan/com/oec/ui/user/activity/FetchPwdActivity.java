package shangchuan.com.oec.ui.user.activity;

@BindView(R.id.toolbar)
    Toolbar mToolbar;
@BindView(R.id.toolbar_title)
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.widget.VerifyCodeButton;

public class FetchPwdActivity extends BaseActivity {
    TextView mToolbarTitle;
    @BindView(R.id.et_phone_number)
    EditText phoneNumber;
    @BindView(R.id.btn_verify_code)
    VerifyCodeButton mVerifyCodeButton;

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_fetch_pwd;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("找回密码");
        initToolBar(mToolbar);
    }

    @Override
    protected void initInject() {

    }
    @OnClick(R.id.btn_verify_code)
    void getVerifyCode(){
        if(!TextUtils.isEmpty(phoneNumber.getText().toString())){
            mVerifyCodeButton.start();
        }else {
            Toast.makeText(this,"请输入手机号码",Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.btn_next)
    void next(){
        startActivity(new Intent(this,SetPwdActivity.class));
    }

    @Override
    public void showError(String msg) {

    }
}
