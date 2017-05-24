package shangchuan.com.oec.ui.apply.fragment.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shangchuan.com.oec.util.LogUtil;

/**
 * Created by sg280 on 2017/5/15.
 */

public class HeResponseFragment extends MyTaskBaseListFragment {

    public static HeResponseFragment getInstance(String userid,String tel){
        HeResponseFragment fragment=new HeResponseFragment();
        Bundle bundle=new Bundle();
        bundle.putString("userid",userid);
        bundle.putString("tel",tel);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mStatus=0;
        finishStatus=1;
        userId=getArguments().getString("userid");
        tel=getArguments().getString("tel");
        LogUtil.i("he tel="+tel);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
