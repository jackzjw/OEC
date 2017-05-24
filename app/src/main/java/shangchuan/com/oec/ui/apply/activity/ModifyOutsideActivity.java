package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.OaDetailsBean;
import shangchuan.com.oec.util.PickerUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.LoadingView;

public class ModifyOutsideActivity extends ModifyBaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar_title)
    TextView mToolBarTitle;
    @BindView(R.id.outside_type)
    TextView mType;
    @BindView(R.id.outside_destination)
    EditText mDest;
    @BindView(R.id.leave_start)
    TextView mStart;
    @BindView(R.id.leave_end)
    TextView mEnd;
    @BindView(R.id.leave_client)
    EditText mClient;
    @BindView(R.id.outside_conpany)
    EditText mCompany;
    @BindView(R.id.content)
    EditText mContent;
    public static Intent getInstance(Context context, OaDetailsBean bean){
        Intent intent=new Intent(context,ModifyOutsideActivity.class);
        intent.putExtra("data",bean);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getResourcesLayout() {
        return  R.layout.activity_work_outside;
    }

    @Override
    protected void initEventData() {
        mToolBarTitle.setText("修改外勤申请");
        data=getIntent().getParcelableExtra("data");
        mType.setText(data.getOrderType());
        mStart.setText(data.getStartTime());
        mEnd.setText(data.getEndTime());
        mCompany.setText(data.getCompanion());
        mContent.setText(data.getOrderContent());
        mType.setOnClickListener(this);
        mStart.setOnClickListener(this);
        mEnd.setOnClickListener(this);

        super.initEventData();
    }
    private String type(){
        return mType.getText().toString();
    }
    private String startTime(){
        return mStart.getText().toString();
    }
    private String endTime(){
        return mEnd.getText().toString();
    }
    private String destnation(){
        return mDest.getText().toString().trim();
    }
    private String content(){
        return mContent.getText().toString();
    }
    @OnClick(R.id.btn_submit)
    void submit(){
        if(TextUtils.isEmpty(type())){
            ToastUtil.show("请选择类型");
            return;
        }
        if(TextUtils.isEmpty(startTime())||TextUtils.isEmpty(endTime())){
            ToastUtil.show("请选择时间段");
            return;
        }
        if(TextUtils.isEmpty(destnation())){
            ToastUtil.show("请选择目的地");
            return;
        }
        if(ownerList.isEmpty()){
            ToastUtil.show("请选择审批人");
            return;
        }
        LoadingView.showProgress(this);
        mPresent.upLoadFile(selectMedia,filePathList);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.leave_start:
                PickerUtil.getInstance().setDateTimePick(this).setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        mStart.setText(year+"-"+month+"-"+day+" "+hour+":00");
                    }
                });
                break;
            case R.id.leave_end:
                PickerUtil.getInstance().setDateTimePick(this).setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        mEnd.setText(year+"-"+month+"-"+day+" "+hour+":00");
                    }
                });
                break;
            case R.id.outside_type:
                PickerUtil.getInstance().setOptionPick(this,new String[]{"外勤","出差"})
                        .setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                            @Override
                            public void onOptionPicked(int index, String item) {
                                mType.setText(item);
                            }
                        });
                break;
        }
    }

    @Override
    public void upLoadFileSuccess(String filename) {
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("ClassId",4);
        hashMap.put("StartTime",startTime());
        hashMap.put("EndTime",endTime());
        hashMap.put("OrderType",type());
        hashMap.put("OrderContent",content());
        for(int id:ownId){
            hashMap.put("Handlers",id);
        }
        hashMap.put("CustomerName",mClient.getText().toString().trim());
        hashMap.put("Destination",destnation());
        hashMap.put("companion",mCompany.getText().toString().trim());
        hashMap.put("ImgList",filename);
        mPresent.submitData(hashMap);
    }
}
