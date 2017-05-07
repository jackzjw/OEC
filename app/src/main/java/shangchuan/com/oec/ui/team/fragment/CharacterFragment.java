package shangchuan.com.oec.ui.team.fragment;

import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.util.LogUtil;

/**
 * Created by sg280 on 2017/3/8.
 */

public class CharacterFragment extends BaseFragment {
    @Override
    public void loadData() {
        if(!isVisible||!isPrepared){
            return;
        }


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.i("CharacterFragment="+isVisibleToUser);
    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_team_character;
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }
}
