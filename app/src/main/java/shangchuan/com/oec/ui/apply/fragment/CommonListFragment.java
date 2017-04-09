package shangchuan.com.oec.ui.apply.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sg280 on 2017/3/14.
 */

public class CommonListFragment extends OaListBaseFragment {

    public static CommonListFragment getInstance(){
        CommonListFragment fragment=new CommonListFragment();
        Bundle bundle=new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mType="通用申请";
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
