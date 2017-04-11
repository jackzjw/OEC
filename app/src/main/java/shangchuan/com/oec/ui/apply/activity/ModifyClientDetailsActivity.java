package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.ClientDetailsBasicBean;
import shangchuan.com.oec.model.bean.ContactListBean;
import shangchuan.com.oec.model.bean.CustomerListBean;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.present.ModifyClientDetailPresent;
import shangchuan.com.oec.present.contact.ModifyClientDetailsContract;
import shangchuan.com.oec.ui.apply.adapter.ContactEditAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.LoadingView;

public class ModifyClientDetailsActivity extends BaseActivity<ModifyClientDetailPresent> implements ModifyClientDetailsContract.View {
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
    @BindView(R.id.client_tel)
    TextView mTel;
    @BindView(R.id.edit_contact)
    TextView editContact;
    private int CustomerId;
    private ContactEditAdapter adapter;
    private boolean isEdit;
    private ArrayList<ContactListBean> contactList=new ArrayList<>();
    private int REQUEST_CODE=98;
    public static Intent getInstance(Context context, int id){
        Intent intent=new Intent(context,ModifyClientDetailsActivity.class);
        intent.putExtra("id",id);
        return intent;
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_add_client;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("修改");
        initToolBar(mToolbar);
        LoadingView.showProgress(this);
      mPresent.getClientDetails(getIntent().getIntExtra("id",-1));
        adapter=new ContactEditAdapter(this,contactList);
        contactRec.setLayoutManager(new LinearLayoutManager(this));
        contactRec.setAdapter(adapter);
        editContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEdit=!isEdit;
                editContact.setText(isEdit?"取消编辑":"编辑");
                adapter.setEdit(isEdit);
            }
        });
        adapter.setEditClickListener(new ContactEditAdapter.OnItemEditClickListener() {
            @Override
            public void onDeleteClick(int pos) {
                LoadingView.showProgress(ModifyClientDetailsActivity.this);
                mPresent.deleteContact(contactList.get(pos).getId());
            }

            @Override
            public void onModifyClick(int pos) {
                Intent intent=ModifyContactActivity.getInstace(ModifyClientDetailsActivity.this,contactList.get(pos));
                      startActivityForResult(intent,REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK){
            mPresent.getContactList(CustomerId);

        }
    }

    @Override
    protected void initInject() {
          getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
         ToastUtil.show(msg);
    }

    @Override
    public void saveSuccess(WoSuccessBean result) {

    }

    @Override
    public void showContent(ClientDetailsBasicBean bean) {
         LoadingView.dismissProgress();
        CustomerListBean result = bean.getCinfo();
        CustomerId=result.getId();
        mType.setText(result.getCustomerType());
        etCode.setText(result.getCustomerCode());
        etFullName.setText(result.getCustomerName());
        etShortAddress.setText(result.getCustomerCity());
        etDetailAdress.setText(result.getCustomerAddress());
        etPostCode.setText(result.getCustomerPostcode());
        etShortName.setText(result.getCustomerShortName());
        mTel.setText(result.getCustomerTel());
       contactList = bean.getCclist();
        if(contactList.isEmpty()) editContact.setVisibility(View.INVISIBLE);
         adapter.updateData(contactList);
    }

    @Override
    public void deleteSuccess() {
        LoadingView.dismissProgress();
        ToastUtil.show("删除成功");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showContactList(List<ContactListBean> bean) {
        adapter.updateData(bean);
    }
}
