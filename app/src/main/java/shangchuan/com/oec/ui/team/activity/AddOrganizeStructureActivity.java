package shangchuan.com.oec.ui.team.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.SimpleActivity;
import shangchuan.com.oec.model.bean.GroupListBean;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.SaveToken;

public class AddOrganizeStructureActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.et_pre_node)
    TextView mPreNode;
    @BindView(R.id.et_department_name)
    EditText mDeptName;
    @BindView(R.id.et_describe)
    EditText mDesc;
    private GroupListBean bean;




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constants.REQUEST_CODE&&resultCode==RESULT_OK){

            bean=(GroupListBean) data.getSerializableExtra("selectNode");
            LogUtil.i(bean.toString());
            if(bean!=null){
                mPreNode.setText(bean.getGroupName());
            }
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_add_organize_structure;
    }

    @Override
    protected void initEventAndData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("新增组织架构");
        initToolBar(mToolbar);
        mPreNode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AddOrganizeStructureActivity.this,AddGroupNodeActivity.class), Constants.REQUEST_CODE);
            }
        });

    }

    @Override
    public void showError(String msg) {
           ToastUtil.shortShow(msg);
        LoadingView.dismissProgress();
    }
    @OnClick(R.id.add_organize_structure)
    void submit(){
        String name=mDeptName.getText().toString();
        String remark=mDesc.getText().toString();
        if(bean==null){
            ToastUtil.shortShow("请选择上级节点");
            return;
        }
        if(TextUtils.isEmpty(name)){
            ToastUtil.shortShow("请填写部门名称");
            return;
        }
        LoadingView.showProgress(this);
        Subscription subscription = getActivityComponent().getHttpHelper().getApiSevice().addGroupNode(bean.getPId(), name, remark, SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<WoSuccessBean>>scheduleRxHelper())
                .compose(RxUtil.<WoSuccessBean>handleResult()).subscribe(new CommonSubscriber<WoSuccessBean>(this) {
                    @Override
                    public void onNext(WoSuccessBean bean) {
                        LoadingView.dismissProgress();
                         ToastUtil.shortShow("添加成功");
                        finish();
                    }
                });
        add(subscription);

    }
}
