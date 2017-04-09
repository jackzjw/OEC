package shangchuan.com.oec.ui.apply.fragment.approvepend;

import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;

/**
 * Created by sg280 on 2017/3/23.
 */

public class OverTimeFragment extends BaseFragment {
    @BindView(R.id.approvepend_type)
    TextView mTextView;
    @Override
    public void loadData() {
        if(!isPrepared||!isVisible){
            return;
        }
        Log.e("mtextview","="+mTextView);
        mTextView.setText("加班");
    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_approvepend_overtime;
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }
}
