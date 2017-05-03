package shangchuan.com.oec.widget;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.ui.apply.activity.ApprovePendListActivity;

/**
 * Created by sg280 on 2017/4/25.
 */

public class ApprovePendPopupWindow extends BasePopupWindow implements View.OnClickListener{
    @BindView(R.id.overtime_approve)
    TextView mOverTimeApprove;
    @BindView(R.id.leave_approve)
    TextView mLeaveApprove;
    @BindView(R.id.outside_approve)
    TextView mOusideApprove;
    @BindView(R.id.reimburse_approve)
    TextView mReimburseApprove;
    @BindView(R.id.common_approve)
    TextView mCommonApprove;

    private Context mContext;
    public ApprovePendPopupWindow(Context context) {
        super(context);
        this.mContext=context;
        init(context);
    }

    private void init(Context context) {
        View mview= LayoutInflater.from(context).inflate(R.layout.popupwindow_approve_pend,null);
        setContentView(mview);
        ButterKnife.bind(this,mview);
        mOverTimeApprove.setOnClickListener(this);
        mLeaveApprove.setOnClickListener(this);
        mCommonApprove.setOnClickListener(this);
        mOusideApprove.setOnClickListener(this);
        mReimburseApprove.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.overtime_approve:
                mContext.startActivity(new Intent(ApprovePendListActivity.getInstance(mContext,"加班")));
                dismiss();
                break;
            case R.id.leave_approve:
                mContext.startActivity(new Intent(ApprovePendListActivity.getInstance(mContext,"请假")));
                dismiss();
                break;
            case R.id.outside_approve:
                mContext.startActivity(ApprovePendListActivity.getInstance(mContext,"外勤/出差"));
                dismiss();
                break;
            case R.id.reimburse_approve:
                mContext.startActivity(ApprovePendListActivity.getInstance(mContext,"报销"));
                dismiss();
                break;
            case R.id.common_approve:
                mContext.startActivity(ApprovePendListActivity.getInstance(mContext,"通用申请"));
                dismiss();
                break;
        }
    }
}
