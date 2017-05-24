package shangchuan.com.oec.ui.apply.fragment.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sg280 on 2017/5/4.
 */

public class HeLaunchFragment extends MyTaskBaseListFragment {

    public static HeLaunchFragment getInstance(String userid){
        HeLaunchFragment fragment=new HeLaunchFragment();
        Bundle bundle=new Bundle();
        bundle.putString("userid",userid);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mStatus=3;
        finishStatus=4;
        userId=getArguments().getString("userid");

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
