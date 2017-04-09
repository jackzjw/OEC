package shangchuan.com.oec.ui.apply.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sg280 on 2017/3/14.
 */

public class ReimburseListFragment extends OaListBaseFragment {
    public static ReimburseListFragment getInstance(){
        ReimburseListFragment fragment=new ReimburseListFragment();
        Bundle bundle=new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mType="报销";
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
