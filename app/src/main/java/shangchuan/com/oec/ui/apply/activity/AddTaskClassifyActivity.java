package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.SimpleActivity;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.ResultBean;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.SaveToken;

public class AddTaskClassifyActivity extends SimpleActivity {
    @BindView(R.id.classify_name)
    EditText mName;
    @BindView(R.id.remark)
    EditText mRemark;
    @BindView(R.id.order_by)
    EditText mOrderBy;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
public static Intent getInstance(Context context,String id){
    Intent intent=new Intent(context,AddTaskClassifyActivity.class);
    intent.putExtra("id",id);
    return intent;
}
    @Override
    protected int getLayout() {
        return R.layout.activity_add_task_classify;
    }

    @Override
    protected void initEventAndData() {
        toolbarTitle.setText("添加任务分类");
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
    }

    @Override
    public void showError(String msg) {
           LoadingView.dismissProgress();
           ToastUtil.shortShow(msg);
    }
    @OnClick(R.id.project_submit)
    void submit(){
        String clasifyName=mName.getText().toString();
        if(TextUtils.isEmpty(clasifyName)){
            ToastUtil.shortShow("请填写分类名称");
            return;
        }
        String id=getIntent().getStringExtra("id");
        LoadingView.showProgress(this);
        getActivityComponent().getHttpHelper().getApiSevice().addClassTask(id,clasifyName,
                mOrderBy.getText().toString().trim(),mRemark.getText().toString(), SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ResultBean<WoSuccessBean>>>scheduleRxHelper())
                .compose(RxUtil.<ResultBean<WoSuccessBean>>handleResult()).subscribe(new CommonSubscriber<ResultBean<WoSuccessBean>>(this) {
            @Override
            public void onNext(ResultBean<WoSuccessBean> bean) {
                LoadingView.dismissProgress();
                ToastUtil.shortShow("添加成功");
                finish();
            }
        });

    }
}
