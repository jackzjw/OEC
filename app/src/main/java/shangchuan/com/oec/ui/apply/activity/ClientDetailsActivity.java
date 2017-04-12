package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.ClientDetailsBasicBean;
import shangchuan.com.oec.model.bean.CustomerListBean;
import shangchuan.com.oec.present.ClientDetailsPresent;
import shangchuan.com.oec.present.contact.ClientDetailContract;
import shangchuan.com.oec.ui.apply.adapter.ClientDetailsAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;

public class ClientDetailsActivity extends BaseActivity<ClientDetailsPresent> implements ClientDetailContract.View {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar_right_title)
    TextView mToolbartRight;
    @BindView(R.id.short_name)
    TextView mShortName;
    @BindView(R.id.client_type)
    TextView mClientType;
    @BindView(R.id.file_time)
    TextView mDate;
    @BindView(R.id.client_code)
    TextView mCode;
    @BindView(R.id.client_tel)
    TextView mTel;
    @BindView(R.id.client_full_name)
    TextView mFullName;
    @BindView(R.id.client_simple_address)
    TextView mSimpleAddress;
    @BindView(R.id.client_address_details)
    TextView mDetailAddress;
    @BindView(R.id.client_postcode)
    TextView mPostCode;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    private int mId;

    public static Intent getInstance(Context context,int id){
        Intent intent=new Intent(context,ClientDetailsActivity.class);
        intent.putExtra("id",id);
        return intent;
    }


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_client_details;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("客户详情");
        mToolbartRight.setText("修改");
        initToolBar(mToolbar);
        mId=getIntent().getIntExtra("id",0);
        mPresent.getClientDetails(mId);
        mToolbartRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=ModifyClientDetailsActivity.getInstance(ClientDetailsActivity.this,mId);
                startActivity(intent);
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

    @Override
    public void showContent(ClientDetailsBasicBean result) {
        CustomerListBean clientInfo = result.getCinfo();
        mShortName.setText(clientInfo.getCustomerShortName());
        mClientType.setText(clientInfo.getCustomerType());
        mCode.setText(clientInfo.getCustomerCode());
        mDate.setText(clientInfo.getCreateTime());
        mFullName.setText(clientInfo.getCustomerName());
        mDetailAddress.setText(clientInfo.getCustomerAddress());
        mSimpleAddress.setText(clientInfo.getCustomerCity());
        mPostCode.setText(clientInfo.getCustomerPostcode());
        mTel.setText(clientInfo.getCustomerTel());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerDecoration(this));
        mRecyclerView.setAdapter(new ClientDetailsAdapter(this,result.getCclist()));
    }
}
