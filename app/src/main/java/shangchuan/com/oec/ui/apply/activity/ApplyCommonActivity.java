package shangchuan.com.oec.ui.apply.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.present.AddApplyPresent;
import shangchuan.com.oec.present.contact.AddApplyContract;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.LoadingView;

public class ApplyCommonActivity extends BaseActivity<AddApplyPresent> implements AddApplyContract.View {

    @BindView(R.id.toolbar_title)
    TextView mToolBarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_time)
    TextView mTime;
    @BindView(R.id.common_title)
    EditText mTitle;
    @BindView(R.id.common_content)
    EditText mContent;
    @BindView(R.id.img_link)
    ImageView mLink;
    @BindView(R.id.rel_add_owner)
    RelativeLayout mAddOwner;
    @BindView(R.id.img_recycleview)
    RecyclerView ImgRec;
    @BindView(R.id.approver_recycleview)
     RecyclerView ApprovaRec;



    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_apply_common;
    }

    @Override
    protected void initEventData() {
        mToolBarTitle.setText(getResources().getString(R.string.apply_common));
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);

    }
    private String getTime(){
        return mTime.getText().toString();
    }
    private String getTitles(){
        return mTitle.getText().toString();
    }
    private String getContent(){
        return mContent.getText().toString();
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.show(msg);
    }

    @Override
    public void AddSuccess() {

    }

    @Override
    public void upLoadFileSuccess(String filename) {

    }
    @OnClick(R.id.btn_submit)
    void submit(){

    }
}
