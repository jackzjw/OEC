package shangchuan.com.oec.ui.user.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.base.RxPresent;
import shangchuan.com.oec.model.bean.MySelfInfo;
import shangchuan.com.oec.ui.user.activity.LoginActivity;
import shangchuan.com.oec.ui.user.activity.SwitchOrganizationActivity;
import shangchuan.com.oec.ui.user.activity.UserInfoActivity;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.SharePreferenceUtil;
import shangchuan.com.oec.widget.CircleImageView;

/**
 * Created by sg280 on 2017/3/7.
 */

public class UserFragment<T extends RxPresent> extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.rel_switch_organize)
    RelativeLayout mSwitchOrganize;
    @BindView(R.id.rel_user_info)
    RelativeLayout mUserInfo;
    @BindView(R.id.tv_logoff)
    TextView mLogOff;
    @BindView(R.id.user_avater)
    CircleImageView userAvater;
    @BindView(R.id.user_nick)
    TextView userNick;
    @BindView(R.id.user_phone_number)
    TextView phoneNumber;
    @BindView(R.id.current_tenant_name)
    TextView mTenantName;
    private int REQUEST_CODE=1;

    @Override
    public void loadData() {
        mSwitchOrganize.setOnClickListener(this);
        mUserInfo.setOnClickListener(this);
        mLogOff.setOnClickListener(this);
        showUserInfo();


    }
    private void showUserInfo(){
     //   MySelfInfo.getInstance().getCache(mActivity);
        Glide.with(mActivity).load(MySelfInfo.getInstance().getAvatar())
                       .error(R.drawable.user_img_avatar).into(userAvater);
            userNick.setText(MySelfInfo.getInstance().getNickName());
            //手机号中间四位替换成星号
         LogUtil.i(MySelfInfo.getInstance().getNickName()+"昵称");
            String phoneNum="13932233333"
                    .replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            phoneNumber.setText(phoneNum);
        mTenantName.setText(MySelfInfo.getInstance().getTenantName());
    }



    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initInject() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rel_switch_organize:
                startActivityForResult(new Intent(mActivity, SwitchOrganizationActivity.class),REQUEST_CODE);
                break;
            case R.id.rel_user_info:
                startActivity(new Intent(mActivity, UserInfoActivity.class));
                break;
            case R.id.tv_logoff:
               SharePreferenceUtil.setLogin(false);
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE&&resultCode==mActivity.RESULT_OK){
            Glide.with(mActivity).load(data.getStringExtra(Constants.USER_AVATER))
                    .error(R.drawable.user_img_avatar).into(userAvater);
            userNick.setText(data.getStringExtra(Constants.USER_NICK_NAME));
            mTenantName.setText(data.getStringExtra(Constants.CURRENT_TENANT_NAME));
        }
    }

    @Override
    public void showError(String msg) {

    }
}
