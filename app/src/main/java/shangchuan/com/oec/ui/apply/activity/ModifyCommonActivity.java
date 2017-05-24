package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DateTimePicker;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.OaDetailsBean;
import shangchuan.com.oec.util.PickerUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.LoadingView;

public class ModifyCommonActivity extends ModifyBaseActivity {
    @BindView(R.id.toolbar_title)
    TextView mToolBarTitle;
    @BindView(R.id.common_time)
    TextView mTime;
    @BindView(R.id.common_title)
    EditText mTitle;
    @BindView(R.id.common_content)
    EditText mContent;


    public static Intent getInstance(Context context, OaDetailsBean bean){
        Intent intent=new Intent(context,ModifyCommonActivity.class);
        intent.putExtra("data",bean);
        return intent;
    }


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_apply_common;
    }

    @Override
    protected void initEventData() {
        mToolBarTitle.setText("修改通用申请");
        data=getIntent().getParcelableExtra("data");
        mTime.setText(data.getOrderTime());
        mTitle.setText(data.getOrderTitle());
        mContent.setText(data.getOrderContent());
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickerUtil.getInstance().setDateTimePick(ModifyCommonActivity.this)
                        .setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                            @Override
                            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                                mTime.setText(year+"-"+month+"-"+day);
                            }
                        });
            }
        });

       super.initEventData();
    }





    @Override
    public void upLoadFileSuccess(String filename) {
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("ClassId",1);
        hashMap.put("OrderTime",mTime.getText().toString());
        hashMap.put("OrderContent",mContent.getText().toString());
        hashMap.put("OrderTitle",mTitle.getText().toString());
        for(int id:ownId){
            hashMap.put("Handlers",id);
        }
        hashMap.put("ImgList",filename);
        mPresent.submitData(hashMap);
    }
    @OnClick(R.id.btn_submit)
    void submit(){
        if(TextUtils.isEmpty(mTime.getText().toString())){
            ToastUtil.show("请开始时间");
            return;
        }

        if(ownerList.size()==0){
            ToastUtil.show("请选择汇报人");
            return;
        }
        LoadingView.showProgress(this);
        mPresent.upLoadFile(selectMedia,filePathList);

    }
}
