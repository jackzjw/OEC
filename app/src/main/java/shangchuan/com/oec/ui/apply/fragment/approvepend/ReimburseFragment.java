package shangchuan.com.oec.ui.apply.fragment.approvepend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sg280 on 2017/3/23.
 */

public class ReimburseFragment extends BaseApproveListFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mType="报销";
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
