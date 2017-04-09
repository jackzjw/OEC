package shangchuan.com.oec.ui.apply.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sg280 on 2017/3/31.
 */

public class OaOverTimeListFragment extends OaListBaseFragment {
    public static OaOverTimeListFragment getInstance(){
        OaOverTimeListFragment fragment=new OaOverTimeListFragment();
        Bundle bundle=new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mType="加班";
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
