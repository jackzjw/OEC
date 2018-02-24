package shangchuan.com.oec.ui.apply.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.OptionPicker;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.ContactInfoBean;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.present.AddClientPresent;
import shangchuan.com.oec.present.contact.AddClientContract;
import shangchuan.com.oec.ui.apply.adapter.AddContactAdpater;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.PickerUtil;
import shangchuan.com.oec.util.ToastUtil;

public class AddClientActivity extends BaseActivity<AddClientPresent> implements AddClientContract.View,View.OnClickListener {
  @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.client_type)
    TextView mType;
    @BindView(R.id.client_code)
    EditText etCode;
    @BindView(R.id.client_full_name)
    EditText etFullName;
    @BindView(R.id.client_simple_name)
    EditText etShortName;
    @BindView(R.id.client_simple_address)
    EditText etShortAddress;
    @BindView(R.id.client_address_details)
    EditText etDetailAdress;
    @BindView(R.id.client_postcode)
    EditText etPostCode;
    @BindView(R.id.recycleview)
    RecyclerView contactRec;
    @BindView(R.id.rel_add_owner)
    RelativeLayout mAddOwner;
    @BindView(R.id.edit_contact)
    TextView mEdit;

    private List<ContactInfoBean> contacts=new ArrayList<>();
    private AddContactAdpater adapter;


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_add_client;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText(R.string.apply_add_client);
        initToolBar(mToolbar);
        mEdit.setVisibility(View.INVISIBLE);
        mType.setOnClickListener(this);
        mAddOwner.setOnClickListener(this);
       contactRec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
         adapter=new AddContactAdpater(this,contacts);
        contactRec.setAdapter(adapter);
    }



    @Override
    protected void initInject() {
          getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
        ToastUtil.show(msg);
    }
    @OnClick(R.id.btn_submit)
    void submit(){
       if(CommonUtil.isEmpty(etCode)&&CommonUtil.isEmpty(etDetailAdress)&&CommonUtil.isEmpty(etFullName)&&CommonUtil.isEmpty(etPostCode)
               &&CommonUtil.isEmpty(etShortAddress)&&CommonUtil.isEmpty(etShortName)){
           return;
       }
        //还需要添加联系人

    }


    @Override
    public void saveSuccess(WoSuccessBean result) {
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constants.REQUEST_CODE&&resultCode==RESULT_OK){
            ContactInfoBean bean = (ContactInfoBean) data.getParcelableExtra("info");
            contacts.add(bean);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.client_type:
                PickerUtil.getInstance().setOptionPick(AddClientActivity.this,new String[]{"公司","个人"})
                        .setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                            @Override
                            public void onOptionPicked(int index, String item) {
                                mType.setText(item);
                            }
                        });
                break;
            case R.id.rel_add_owner:
                startActivityForResult(new Intent(this,AddContactsActivity.class), Constants.REQUEST_CODE);
                break;
        }
    }
}
