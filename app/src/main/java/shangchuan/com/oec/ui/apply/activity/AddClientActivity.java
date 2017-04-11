package shangchuan.com.oec.ui.apply.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.present.AddClientPresent;
import shangchuan.com.oec.present.contact.AddClientContract;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.WheelDialogFragment;

import static shangchuan.com.oec.util.CommonUtil.isEmpty;

public class AddClientActivity extends BaseActivity<AddClientPresent> implements AddClientContract.View {
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
    private String[] types={"公司","个人"};


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_add_client;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText(R.string.apply_add_client);
        initToolBar(mToolbar);
       mType.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final WheelDialogFragment wheel = WheelDialogFragment.newInstance(types);
               wheel.setWheelDialogListener(new WheelDialogFragment.OnWheelDialogListener() {
                   @Override
                   public void onClickRight(String value) {
                       mType.setText(value);
                       wheel.dismiss();
                   }
               });
           }
       });

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


}
