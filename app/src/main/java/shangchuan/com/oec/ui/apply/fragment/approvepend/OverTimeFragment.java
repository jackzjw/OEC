package shangchuan.com.oec.ui.apply.fragment.approvepend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sg280 on 2017/4/20.
 */

public class OverTimeFragment extends BaseApproveListFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mType="加班";
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
