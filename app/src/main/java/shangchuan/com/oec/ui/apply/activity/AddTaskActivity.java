package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DateTimePicker;
import rx.Subscription;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.SimpleActivity;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.ResultBean;
import shangchuan.com.oec.model.bean.WoSuccessBean;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.PickerUtil;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.SaveToken;

public class AddTaskActivity extends SimpleActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.project_name)
    EditText mTitle;
    @BindView(R.id.project_date)
    TextView mDeadLine;
    @BindView(R.id.task_desc)
    EditText mContent;

    @BindView(R.id.owner_recycleview)
    TextView mOwnerName;
    @BindView(R.id.rel_add_owner)
    RelativeLayout mAddOwner;
    private String mId;
    private String classid;
    private String userid="";

    public static Intent getInstance(Context context,String projectid,String classid){
        Intent intent =new Intent(context,AddTaskActivity.class);
        intent.putExtra("projectid",projectid);
        intent.putExtra("classid",classid);
        return intent;
    }
   private String Title(){
       return mTitle.getText().toString().trim();
   }
    private String deadLine(){
       return mDeadLine.getText().toString();
    }
    private String Content(){
        return mContent.getText().toString();
    }
    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.shortShow(msg);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_add_task;
    }

    @Override
    protected void initEventAndData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("创建新任务");
        initToolBar(mToolbar);
        mId=getIntent().getStringExtra("projectid");
        classid=getIntent().getStringExtra("classid");
        mDeadLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickerUtil.getInstance().setDateTimePick(AddTaskActivity.this)
                       .setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                           @Override
                           public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                               mDeadLine.setText(year+"-"+month+"-"+day);
                           }
                       });
            }
        });
        mAddOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(ProjectMemberListActivity.getInstance(AddTaskActivity.this,mId,1),Constants.REQUEST_CODE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constants.REQUEST_CODE&&resultCode==RESULT_OK){
            userid=data.getStringExtra("userid");
            String userName=data.getStringExtra("username");
              mOwnerName.setText(userName);
            LogUtil.i("userid="+userid);
        }
    }

    @OnClick(R.id.project_submit)
    void submit(){
        if(TextUtils.isEmpty(mOwnerName.getText().toString())){
            ToastUtil.shortShow("请选择负责人");
            return;
        }
        if(TextUtils.isEmpty(Title())){
            ToastUtil.shortShow("请填写标题");
            return;
        }
        if(TextUtils.isEmpty(deadLine())){
            ToastUtil.shortShow("请填写截止时间");
            return;
        }

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("ProjectId",mId);
        hashMap.put("TaskClassId",classid);
        hashMap.put("TaskTitle",Title());
        hashMap.put("TaskContent",Content());
        hashMap.put("UserID",userid);
        hashMap.put("Deadline",deadLine());
        hashMap.put("token", SaveToken.mToken);
        LoadingView.showProgress(this);
        Subscription subscription=getActivityComponent().getHttpHelper().getApiSevice().addProjectTask(hashMap)
                .compose(RxUtil.<HttpDataResult<ResultBean<WoSuccessBean>>>scheduleRxHelper())
                .compose(RxUtil.<ResultBean<WoSuccessBean>>handleResult()).subscribe(new CommonSubscriber<ResultBean<WoSuccessBean>>(this) {
            @Override
            public void onNext(ResultBean<WoSuccessBean> bean) {
                LoadingView.dismissProgress();
                ToastUtil.shortShow("添加成功");
                finish();
            }
        });
        add(subscription);


    }
}
