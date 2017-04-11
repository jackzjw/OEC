package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.ContactListBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.model.http.RetrofitHelper;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.HttpErrorUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.SaveToken;

public class ModifyContactActivity extends AppCompatActivity {

    @BindView(R.id.contact_name)
    EditText mName;
    @BindView(R.id.contacts_department)
    EditText mDepartment;
    @BindView(R.id.contacts_job)
    EditText mJob;
    @BindView(R.id.contacts_email)
    EditText mEmail;
    @BindView(R.id.contacts_tel)
    EditText mTel;
    private ContactListBean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        ButterKnife.bind(this);
         result = (ContactListBean) getIntent().getSerializableExtra("bean");
        if(result!=null){
            mName.setText(result.getContactName());
            mDepartment.setText(result.getContactTitle());
            mJob.setText(result.getContactDept());
            mEmail.setText(result.getContactEmail());
            mTel.setText(result.getContactTel());
        }


    }
    public static Intent getInstace(Context context, ContactListBean bean){
        Intent intent=new Intent(context,ModifyContactActivity.class);
        intent.putExtra("bean",bean);
        return intent;
    }

    @OnClick(R.id.btn_submit)
    void submit(){
        if(CommonUtil.isEmpty(mName)&&CommonUtil.isEmpty(mDepartment)&&CommonUtil.isEmpty(mEmail)
                &&CommonUtil.isEmpty(mJob)&&CommonUtil.isEmpty(mTel)) return;

      submitData();

    }
    private void submitData(){
        HashMap<String,Object> map=new HashMap<>();
        map.put("Id",result.getId());
        map.put("ContactName",mName.getText().toString().trim());
        map.put("ContactDept",mDepartment.getText().toString().trim());
        map.put("ContactTitle",mJob.getText().toString().trim());
        map.put("ContactTel",mTel.getText().toString().trim());
        map.put("ContactEmail",mEmail.getText().toString().trim());
        map.put("Remark",result.getRemark());
        map.put("token", SaveToken.mToken);
        RetrofitHelper mHelper=new RetrofitHelper();
        LoadingView.showProgress(this);
        mHelper.getApiSevice().saveContactResult(map)
                .compose(RxUtil.<HttpDataResult<WoSuccessBean>>scheduleRxHelper())
                .compose(RxUtil.<WoSuccessBean>handleResult()).subscribe(new Subscriber<WoSuccessBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                HttpErrorUtil.handCommonError(e);
            }

            @Override
            public void onNext(WoSuccessBean bean) {
                ToastUtil.show("修改成功");
                setResult(RESULT_OK);
                finish();
            }
        });


    }



}
