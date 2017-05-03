package shangchuan.com.oec.ui.user.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.LocalMediaLoader;
import com.luck.picture.lib.model.PictureConfig;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.component.RxBus;
import shangchuan.com.oec.model.bean.MyInfoBean;
import shangchuan.com.oec.model.bean.MySelfInfo;
import shangchuan.com.oec.present.ModifyUserInfoPresent;
import shangchuan.com.oec.present.contact.ModifyUserInfoContract;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.SharePreferenceUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CircleImageView;
import shangchuan.com.oec.widget.LoadingView;

public class UserInfoActivity extends BaseActivity<ModifyUserInfoPresent> implements ModifyUserInfoContract.View,View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.user_avater)
    CircleImageView mUserAvater;
    @BindView(R.id.user_nick)
    EditText mUserNick;
    @BindView(R.id.user_name)
    EditText mUserName;
    @BindView(R.id.user_gender)
    TextView mUserGender;
    @BindView(R.id.user_job)
    EditText mUserJob;
    @BindView(R.id.user_email)
    EditText mEmail;
    @BindView(R.id.user_tel)
    EditText mTel;
    private MyInfoBean userInfo;
    private String mImgPath="";
    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("个人信息");
        initToolBar(mToolbar);
        LoadingView.showProgress(this);
        mPresent.getUserInfoDetail(MySelfInfo.getInstance().getUserId());
        //上传头像
        mUserAvater.setOnClickListener(this);
    }

    @Override
    protected void initInject() {
         getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
       LoadingView.dismissProgress();
        ToastUtil.shortShow(msg);
    }

    @Override
    public void showUserInfoDetail(MyInfoBean bean) {
        LoadingView.dismissProgress();
        userInfo=bean;
        Glides.getInstance().loadCircle(this,bean.getUserAvatar(),mUserAvater);
        mUserNick.setText(bean.getUserNickName());
        mUserGender.setText(bean.getUserGender()==1?"男":"女");
        mTel.setText(bean.getUserMobile());
        mEmail.setText(bean.getUserEmail());
        mUserName.setText(bean.getUserTrueName());
        mUserJob.setText(bean.getUserTitile());

    }

    @Override
    public void modifySuccess() {
             LoadingView.dismissProgress();
        ToastUtil.shortShow("修改成功");
        MySelfInfo.getInstance().setPhone(mTel.getText().toString());
        MySelfInfo.getInstance().setAvatar(mImgPath);
        MySelfInfo.getInstance().setNickName(mUserNick.getText().toString());
        MySelfInfo.getInstance().writeToCache(this);
        RxBus.getDefault().post("updateUserInfo");
        finish();
    }

    @Override
    public void upLoadSuccess(String fileName) {
        LogUtil.i(fileName);
        uploadData(fileName);
    }

    @OnClick(R.id.sure)
    void submit(){

        if(CommonUtil.isEmpty(mTel)||CommonUtil.isEmpty(mUserName)||CommonUtil.isEmpty(mUserNick)){
            return;
        }
        LoadingView.showProgress(this);
        if(TextUtils.isEmpty(mImgPath)){
            uploadData("");
        }else {
            mPresent.upLoadImg(mImgPath);
        }


    }
    private void uploadData(String imgPath){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("UserMobile",mTel.getText().toString());
        hashMap.put("UserPassword", SharePreferenceUtil.getUserPwd());
        hashMap.put("UserTitle",mUserJob.getText().toString());
        hashMap.put("UserNickName",mUserNick.getText().toString());
        hashMap.put("UserTrueName",mUserName.getText().toString());
        hashMap.put("UserGender",mUserGender.getText().toString().equals("男")?1:0);
        hashMap.put("UserEmail",mEmail.getText().toString());
        hashMap.put("UserAvatar",imgPath);
        for(int i=0;i<userInfo.getRoleIds().length;i++){
            hashMap.put("RoleIds",userInfo.getRoleIds()[i]);
        }
        for(int i=0;i<userInfo.getGroupIds().length;i++){
            hashMap.put("GroupIds",userInfo.getGroupIds()[i]);
        }
        hashMap.put("UTId",SharePreferenceUtil.getTenantId());
        hashMap.put("UserId",SharePreferenceUtil.getUserId());
        mPresent.modifyUserInfo(hashMap);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_avater:
                FunctionConfig config=new FunctionConfig();
                config.setSelectMode(FunctionConfig.MODE_SINGLE);
                config.setType(LocalMediaLoader.TYPE_IMAGE);
                config.setEnableCrop(true);
                config.setCopyMode(FunctionConfig.CROP_MODEL_3_2);
                config.setShowCamera(true);
                PictureConfig.init(config);
                PictureConfig.getPictureConfig().openPhoto(this, new PictureConfig.OnSelectResultCallback() {
                    @Override
                    public void onSelectSuccess(List<LocalMedia> list) {
                        if(!list.isEmpty()){
                            mImgPath=list.get(0).getCutPath();
                            LogUtil.i("路径="+mImgPath);
                            Glides.getInstance().load(UserInfoActivity.this,mImgPath,mUserAvater);
                        }
                    }
                });
                break;
        }
    }
}
