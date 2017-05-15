package shangchuan.com.oec.ui.apply.fragment.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sg280 on 2017/5/15.
 */

public class MyResponseFragment extends MyTaskBaseListFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mStatus=0;
        finishStatus=1;
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
