package shangchuan.com.oec.widget;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.ui.apply.activity.ApplyOfficeActivity;
import shangchuan.com.oec.ui.apply.activity.CreateWorkOrderActivity;
import shangchuan.com.oec.ui.apply.activity.CreateWorkReportActivity;

/**
 * Created by sg280 on 2017/4/25.
 */

public class HomePopupWindow extends BasePopupWindow implements View.OnClickListener{
    @BindView(R.id.create_wo)
    TextView mCreateWo;
    @BindView(R.id.create_apply_details)
    TextView mCreateDetails;
    @BindView(R.id.create_work_report)
    TextView mCreateWorkReport;
    private Context mContext;
    public HomePopupWindow(Context context) {
        super(context);
        this.mContext=context;
        init(context);
    }

    private void init(Context context) {
        View mview= LayoutInflater.from(context).inflate(R.layout.popupwindow_home_add_work,null);
        setContentView(mview);
        ButterKnife.bind(this,mview);
        mCreateWo.setOnClickListener(this);
        mCreateDetails.setOnClickListener(this);
        mCreateWorkReport.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_wo:
                mContext.startActivity(new Intent(mContext, CreateWorkOrderActivity.class));
                dismiss();
                break;
            case R.id.create_apply_details:
                mContext.startActivity(new Intent(mContext, ApplyOfficeActivity.class));
                dismiss();
                break;
            case R.id.create_work_report:
                mContext.startActivity(new Intent(mContext, CreateWorkReportActivity.class));
                dismiss();
                break;
        }
    }
}
