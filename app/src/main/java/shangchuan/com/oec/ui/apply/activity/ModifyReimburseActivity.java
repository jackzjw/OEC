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

public class ModifyReimburseActivity extends ModifyBaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar_title)
    TextView mToolBarTitle;
    @BindView(R.id.reimburse_type)
    TextView mType;
    @BindView(R.id.reimburse_client)
    EditText mClient;
    @BindView(R.id.reimburse_project)
    EditText mProject;
    @BindView(R.id.reimburse_time)
    TextView mTime;
    @BindView(R.id.reimburse_sum)
    EditText mSum;
    @BindView(R.id.reimburse_content)
    EditText mContent;
    public static Intent getInstance(Context context, OaDetailsBean bean){
        Intent intent=new Intent(context,ModifyReimburseActivity.class);
        intent.putExtra("data",bean);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_reimburse;
    }

    @Override
    protected void initEventData() {
        mToolBarTitle.setText("修改报销申请");
        data=getIntent().getParcelableExtra("data");
        mType.setText(data.getOrderType());
        mTime.setText(data.getCreateTime());
        mContent.setText(data.getOrderContent());
        mType.setOnClickListener(this);
        mTime.setOnClickListener(this);

        super.initEventData();
    }
    @OnClick(R.id.btn_submit)
    void submit(){
        if(TextUtils.isEmpty(getType())){
            ToastUtil.show("请填写报销类型");
            return;
        }
        if(TextUtils.isEmpty(getSum())){
            ToastUtil.show("请填写报销金额");
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
        switch (v.getId()){
            case R.id.reimburse_type:
                PickerUtil.getInstance().setOptionPick(this,new String[]{"加班餐补","业务招待费","市内交通费","差旅费"
                        ,"其他"}).setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        mType.setText(item);
                    }
                });
                break;
            case R.id.reimburse_time:
                PickerUtil.getInstance().setDateTimePick(this).setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        mTime.setText(year+"-"+month+"-"+day);
                    }
                });
                break;
        }
    }
    private String getType(){
        return mType.getText().toString();
    }
    private String getSum(){
        return mSum.getText().toString();
    }
    @Override
    public void upLoadFileSuccess(String filename) {
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("ClassId",5);
        hashMap.put("CustomerName",mClient.getText().toString());
        hashMap.put("ProjectName",mProject.getText().toString());
        hashMap.put("OrderTime",mTime.getText().toString());
        hashMap.put("OrderContent",mContent.getText().toString().trim());
        hashMap.put("OrderAmount",mSum.getText().toString().trim());
        hashMap.put("OrderType",getType());
        for(int id:ownId){
            hashMap.put("Handlers",id);
        }
        hashMap.put("ImgList",filename);
        mPresent.submitData(hashMap);
    }
}
