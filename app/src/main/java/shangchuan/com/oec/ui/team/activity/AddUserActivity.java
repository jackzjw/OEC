package shangchuan.com.oec.ui.team.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.OptionPicker;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.GroupListBean;
import shangchuan.com.oec.model.bean.RoleListBean;
import shangchuan.com.oec.present.AddUserPresent;
import shangchuan.com.oec.present.contact.AddUserContract;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.PickerUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.LoadingView;

public class AddUserActivity extends BaseActivity<AddUserPresent> implements AddUserContract.View,View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.user_nick)
    EditText mNick;
    @BindView(R.id.user_name)
    EditText mName;
    @BindView(R.id.user_gender)
    TextView mGender;
    @BindView(R.id.user_department)
    TextView mDept;
    @BindView(R.id.user_job)
    EditText mJob;
    @BindView(R.id.user_tel)
    EditText mTel;
    @BindView(R.id.user_password)
    EditText mPwd;
    @BindView(R.id.user_email)
    EditText mEmail;
    @BindView(R.id.role_list)
    TextView mRoles;
    @BindView(R.id.rel_add_role)
    RelativeLayout mAddRole;
    @BindView(R.id.add_role_desc)
    TextView mRoleDesc;
     private HashSet<RoleListBean> mHashSet=new HashSet<>();
    private HashSet<GroupListBean> mGroups=new HashSet<>();
    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_add_user;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("新增用户");
        initToolBar(mToolbar);
        mAddRole.setOnClickListener(this);
        mGender.setOnClickListener(this);
        mDept.setOnClickListener(this);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
        ToastUtil.shortShow(msg);
        LoadingView.dismissProgress();

    }
    private String getGender(){
        return mGender.getText().toString();
    }
    private String getDept(){
        return mDept.getText().toString();
    }
    private String getRoleName(){
        return mRoles.getText().toString();
    }
    @Override
    public void addSuccess() {
           LoadingView.dismissProgress();
        ToastUtil.shortShow("添加成功");
        finish();
    }

    @Override
    public void showRoleList(HashSet<RoleListBean> bean) {
          if(bean.isEmpty()) return;
        Iterator<RoleListBean> it = bean.iterator();
       mRoleDesc.setText("修改角色");
        StringBuilder roleBuilder=new StringBuilder();
       while(it.hasNext()){
               mHashSet=bean;
               roleBuilder.append(( it.next()).getRoleName()).append(",");
       }
            mRoles.setText(roleBuilder.substring(0, roleBuilder.length() - 1));


    }

    @Override
    public void showDeptList(HashSet<GroupListBean> bean) {
        if(bean.isEmpty())return;
        mGroups=bean;
        Iterator<GroupListBean> it = bean.iterator();
        StringBuilder stringBuilder=new StringBuilder();
        while(it.hasNext()){
            stringBuilder.append(it.next().getGroupName()).append(",");
        }
        mDept.setText(stringBuilder.substring(0,stringBuilder.length()-1));
    }

    @OnClick(R.id.user_info_submit)
    void submit(){
        if(CommonUtil.isEmpty(mNick)||CommonUtil.isEmpty(mName)||CommonUtil.isEmpty(mTel)
                ||CommonUtil.isEmpty(mPwd)){
            return;
        }
        if(TextUtils.isEmpty(getGender())){
            ToastUtil.shortShow("请选择性别");
            return;
        }
        if(TextUtils.isEmpty(getDept())){
            ToastUtil.shortShow("请选择部门");
            return;
        }
        if(TextUtils.isEmpty(getRoleName())){
            ToastUtil.shortShow("请选择角色");
            return;
        }
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("UserMobile",mTel.getText().toString().trim());
        hashMap.put("UserPassword",mPwd.getText().toString().trim());
        hashMap.put("UserTitle",mJob.getText().toString());
        hashMap.put("UserNickName",mNick.getText().toString());
        hashMap.put("UserTrueName",mName.getText().toString());
        hashMap.put("UserGender",getGender().equals("男")?1:0);
        hashMap.put("UserEmail",mEmail.getText().toString());
        for(RoleListBean item:mHashSet){
            hashMap.put("RoleIds",item.getId());
        }
        for (GroupListBean item:mGroups){
            hashMap.put("GroupIds",item.getId());
        }
        LoadingView.showProgress(this);
        mPresent.addUser(hashMap);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_gender:
                PickerUtil.getInstance().setOptionPick(this,new String[]{"男","女"})
                        .setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                            @Override
                            public void onOptionPicked(int index, String item) {
                                mGender.setText(item);
                            }
                        });
                break;
            case R.id.user_department:
                mPresent.registerDeptEvent();
                startActivity(new Intent(this,GroupListActivity.class));
                break;
            case R.id.rel_add_role:
                mPresent.registerRoleEvent();
                startActivity(new Intent(this,RoleListActivity.class));
                break;



        }
    }
}
