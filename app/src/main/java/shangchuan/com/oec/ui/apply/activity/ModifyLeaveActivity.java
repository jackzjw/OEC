package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

public class ModifyLeaveActivity extends ModifyBaseActivity implements View.OnClickListener{
    @BindView(R.id.toolbar_title)
    TextView mToolBarTitle;
    @BindView(R.id.leave_type)
    TextView mType;
    @BindView(R.id.leave_start)
    TextView mStart;
    @BindView(R.id.leave_end)
    TextView mEnd;
    @BindView(R.id.leave_duration)
    TextView mDuration;
    @BindView(R.id.content)
    TextView mContent;
    public static Intent getInstance(Context context, OaDetailsBean bean){
        Intent intent=new Intent(context,ModifyLeaveActivity.class);
        intent.putExtra("data",bean);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initEventData() {
        mToolBarTitle.setText("修改请假申请");
        data=getIntent().getParcelableExtra("data");
        mType.setText(data.getOrderType());
        mStart.setText(data.getStartTime());
        mEnd.setText(data.getEndTime());
        mDuration.setText(data.getOrderPeriod());
        mContent.setText(data.getOrderContent());
        mType.setOnClickListener(this);
        mStart.setOnClickListener(this);
        mEnd.setOnClickListener(this);
        mDuration.setOnClickListener(this);
        super.initEventData();
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_apply_leave;
    }
    private String getType(){
        return mType.getText().toString();
    }
    private String getDuration(){
        return mDuration.getText().toString();
    }
    private String getStartTime(){
        return mStart.getText().toString();
    }
    private String getEndTime(){
        return mEnd.getText().toString();
    }
    @Override
    public void upLoadFileSuccess(String filename) {
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("ClassId",3);
        hashMap.put("OrderType",getType());
        hashMap.put("StartTime",getStartTime());
        hashMap.put("EndTime",getEndTime());
        hashMap.put("OrderContent",mContent.getText().toString().trim());
        hashMap.put("OrderPeriod",getDuration());
        for(int id:ownId){
            hashMap.put("Handlers",id);
        }
        hashMap.put("ImgList",filename);
        mPresent.submitData(hashMap);
    }
    @OnClick(R.id.btn_submit)
    void submit(){
        if(TextUtils.isEmpty(getStartTime())|TextUtils.isEmpty(getEndTime())){
            ToastUtil.show("请填写时间段");
            return;
        }
        if(TextUtils.isEmpty(getDuration())){
            ToastUtil.show("请填写时长");
            return;
        }
        if(TextUtils.isEmpty(getType())){
            ToastUtil.show("请填写请假类型");
            return;
        }
        if(ownerList.size()==0){
            ToastUtil.show("请添加责任人");
            return;
        }
        LoadingView.showProgress(this);
        mPresent.upLoadFile(selectMedia,filePathList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leave_type:
                PickerUtil.getInstance().setOptionPick(this, new String[]{"病假", "事假", "年假", "调休"})
                        .setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                            @Override
                            public void onOptionPicked(int index, String item) {
                                mType.setText(item);
                            }
                        });
                break;
            case R.id.leave_duration:
                PickerUtil.getInstance().setOptionPickLabel
                        (this, new String[]{"0.5", "1", "1.5", "2", "2.5", "3"
                                , "3.5", "4", "4.5", "5"}, "天").setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        mDuration.setText(item);
                    }
                });
                break;
            case R.id.leave_start:
                PickerUtil.getInstance().setDateTimePick(this).setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        mStart.setText(year + "-" + month + "-" + day + " " + hour + ":00");
                    }
                });
                break;
            case R.id.leave_end:
                PickerUtil.getInstance().setDateTimePick(this).setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        mEnd.setText(year + "-" + month + "-" + day + " " + hour + ":00");
                    }
                });
                break;
        }
    }

}
