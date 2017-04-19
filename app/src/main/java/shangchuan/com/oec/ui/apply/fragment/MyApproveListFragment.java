package shangchuan.com.oec.ui.apply.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sg280 on 2017/3/14.
 */

public class MyApproveListFragment extends BaseMyReportListFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         mType=2;
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
