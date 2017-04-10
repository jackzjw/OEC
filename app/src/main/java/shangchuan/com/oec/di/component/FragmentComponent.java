package shangchuan.com.oec.di.component;

import android.app.Activity;

import dagger.Subcomponent;
import shangchuan.com.oec.di.FragmentScope;
import shangchuan.com.oec.ui.apply.fragment.BaseClientListFragment;
import shangchuan.com.oec.ui.apply.fragment.OaListBaseFragment;
import shangchuan.com.oec.ui.apply.fragment.workofficelist.WoListBaseFragment;

/**
 * Created by sg280 on 2017/3/6.
 */
@FragmentScope
@Subcomponent
public interface FragmentComponent {
   Activity getActiity();
 //  void inject(HomeFragment fragment);

    void inject (OaListBaseFragment fragment);
    void inject(WoListBaseFragment fragment);
    void inject(BaseClientListFragment fragment);

}
