package shangchuan.com.oec.ui.user.activity;

import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.SimpleActivity;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.MD5Util;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.SharePreferenceUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.SaveToken;

public class ModifyPwdActivity extends SimpleActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.et_old_phone_number)
    EditText mOldNum;
    @BindView(R.id.et_new_phone_number)
    EditText mPwd;
    @BindView(R.id.et_confirm_new_phone_number)
    EditText mConfirmPwd;



    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.shortShow(msg);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    protected void initEventAndData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("修改密码");
        initToolBar(mToolbar);

    }
    private String oldNum(){
        return mOldNum.getText().toString().trim();
    }
    private String newPwd(){
        return mPwd.getText().toString().trim();
    }
    private String confirmPwd(){
        return mConfirmPwd.getText().toString().trim();
    }
    @OnClick(R.id.sure)
    void submit(){
        if(CommonUtil.isEmpty(mOldNum)||CommonUtil.isEmpty(mPwd)||CommonUtil.isEmpty(mConfirmPwd)){
            return;
        }
        if(!newPwd().equals(confirmPwd())){
            ToastUtil.shortShow("两次密码不一致");
            return;
        }
        LoadingView.showProgress(this);
        Subscription subscription = getActivityComponent().getHttpHelper().getApiSevice().modifyPwd(MD5Util.getStringMD5(oldNum() + "OEC"),
                MD5Util.getStringMD5(confirmPwd() + "OEC"), SaveToken.mToken).compose(RxUtil.<HttpDataResult<WoSuccessBean>>scheduleRxHelper())
                .compose(RxUtil.<WoSuccessBean>handleResult()).subscribe(new CommonSubscriber<WoSuccessBean>(this) {
                    @Override
                    public void onNext(WoSuccessBean bean) {
                          LoadingView.dismissProgress();
                        SharePreferenceUtil.setUserPwd(confirmPwd());
                        ToastUtil.shortShow("密码修改成功");
                        finish();
                    }
                });
           add(subscription);
    }
}
