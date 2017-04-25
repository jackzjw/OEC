package shangchuan.com.oec.ui.apply.activity;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.AttendanceListBean;
import shangchuan.com.oec.present.AttendancePresent;
import shangchuan.com.oec.present.contact.AttendanceContract;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CalendarView;
import shangchuan.com.oec.widget.LoadingView;

public class AttendanceActivity extends BaseActivity<AttendancePresent> implements AttendanceContract.View {
  @BindView(R.id.toolbar_right_title)
    TextView mToolbarRight;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.calendar_view)
    CalendarView mCalendarView;
    @BindView(R.id.start_work)
    TextView mStartTime;
    @BindView(R.id.end_work)
    TextView mEndTime;
    @BindView(R.id.input_time)
    TextView mInputTime;
    @BindView(R.id.pre_month)
    TextView mPreMonth;
    @BindView(R.id.next_month)
    TextView mNextMonth;

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_attendance;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarRight.setText("导入");
      Calendar cal = Calendar.getInstance();
        mToolbarTitle.setText(cal.get(Calendar.YEAR)+"年"+cal.get(Calendar.MONTH)+1+"月");
        initToolBar(mToolbar);
      LoadingView.showProgress(this);
      mPresent.getAttendanceList("");
    }

    @Override
    protected void initInject() {
            getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
      LoadingView.dismissProgress();
      ToastUtil.shortShow(msg);
    }

  @Override
  public void showContent(List<AttendanceListBean> bean) {
    LoadingView.dismissProgress();
       mCalendarView.setData(bean);
  }
}
