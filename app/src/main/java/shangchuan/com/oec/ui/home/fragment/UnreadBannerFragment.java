package shangchuan.com.oec.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sg280 on 2017/3/10.
 */

public class UnreadBannerFragment extends BannersBaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         readStatus=0;
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
