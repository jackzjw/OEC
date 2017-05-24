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

public class ModifyOverTimeActivity extends ModifyBaseActivity implements View.OnClickListener{
    @BindView(R.id.toolbar_title)
    TextView mToolBarTitle;
    @BindView(R.id.overtime_start)
    TextView mStart;
    @BindView(R.id.overtime_end)
    TextView mEnd;
    @BindView(R.id.overtime_hours)
    TextView mDuration;
    @BindView(R.id.et_content)
    EditText mContent;
    public static Intent getInstance(Context context, OaDetailsBean bean){
        Intent intent=new Intent(context,ModifyOverTimeActivity.class);
        intent.putExtra("data",bean);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_work_overtime;
    }

    @Override
    protected void initEventData() {
        mToolBarTitle.setText("修改加班申请");
        data=getIntent().getParcelableExtra("data");
        mDuration.setText(data.getOrderPeriod());
        mStart.setText(data.getStartTime());
        mEnd.setText(data.getEndTime());
        mContent.setText(data.getOrderContent());
        mStart.setOnClickListener(this);
        mEnd.setOnClickListener(this);
        mDuration.setOnClickListener(this);
        super.initEventData();

    }

    @Override
    public void upLoadFileSuccess(String filename) {
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("ClassId",2);
        hashMap.put("StartTime",mStart.getText().toString());
        hashMap.put("EndTime",mEnd.getText().toString());
        hashMap.put("OrderPeriod",mDuration.getText().toString());
        hashMap.put("OrderContent",mContent.getText().toString().trim());
        for(int id:ownId){
            hashMap.put("Handlers",id);
        }
        hashMap.put("ImgList",filename);
        mPresent.submitData(hashMap);
    }
    @OnClick(R.id.btn_submit)
    void submit(){
        String startTime=mStart.getText().toString();
        String endTime=mEnd.getText().toString();
        String hours=mDuration.getText().toString();
        if(TextUtils.isEmpty(startTime)||TextUtils.isEmpty(endTime)||TextUtils.isEmpty(hours)){
            ToastUtil.show("请填写加班时间段、时长");
            return;
        }
        if(ownerList.size()==0){
            ToastUtil.show("请添加责任人");
            return;
        }
        mPresent.upLoadFile(selectMedia,filePathList);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.overtime_start:
                PickerUtil.getInstance().setDateTimePick(this).setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        mStart.setText(year+"-"+month+"-"+day+" "+hour+":00");
                    }
                });
                break;
            case R.id.overtime_end:
                PickerUtil.getInstance().setDateTimePick(this).setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        mEnd.setText(year+"-"+month+"-"+day+" "+hour+":00");
                    }
                });
                break;
            case R.id.overtime_hours:
                PickerUtil.getInstance().setOptionPickLabel(this,new String[]{"1","2","3","4","5","6","7","8"},"小时")
                        .setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                            @Override
                            public void onOptionPicked(int index, String item) {
                                mDuration.setText(item);
                            }
                        });
                break;
        }
    }
}
