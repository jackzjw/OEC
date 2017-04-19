package shangchuan.com.oec.ui.apply.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.ContactInfoBean;
import shangchuan.com.oec.util.CommonUtil;

public class AddContactsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.contact_name)
    EditText mName;
    @BindView(R.id.contacts_department)
    EditText mDept;
    @BindView(R.id.contacts_job)
    EditText mJob;
    @BindView(R.id.contacts_tel)
    EditText mTel;
    @BindView(R.id.contacts_email)
    EditText mEmail;



    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_add_contacts;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("新增联系人");
        initToolBar(mToolbar);

    }


    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }
    @OnClick(R.id.btn_submit)
    void submit(){
        if(CommonUtil.isEmpty(mName)||CommonUtil.isEmpty(mDept)||CommonUtil.isEmpty(mEmail)
                ||CommonUtil.isEmpty(mTel)||CommonUtil.isEmpty(mJob)) return;
        ContactInfoBean bean=new ContactInfoBean();
        bean.setContactName(mName.getText().toString().trim());
        bean.setContactEmail(mEmail.getText().toString().trim());
        bean.setContactDept(mDept.getText().toString().trim());
        bean.setContactTitle(mJob.getText().toString().trim());
        Intent intent=new Intent();
        intent.putExtra("info",bean);
        setResult(RESULT_OK,intent);
        finish();

    }
}
