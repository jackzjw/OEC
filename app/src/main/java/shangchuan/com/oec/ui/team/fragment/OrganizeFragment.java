package shangchuan.com.oec.ui.team.fragment;

import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.model.bean.RectDeptBean;
import shangchuan.com.oec.present.GroupListPresent;
import shangchuan.com.oec.present.contact.GroupListContract;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.OrganizationView;

import static shangchuan.com.oec.widget.LoadingView.dismissProgress;

/**
 * Created by sg280 on 2017/3/8.
 */

public class OrganizeFragment extends BaseFragment<GroupListPresent> implements GroupListContract.View {
  @BindView(R.id.organize_view)
  OrganizationView mOrgView;


    @Override
    public void showError(String msg) {
           dismissProgress();
        ToastUtil.shortShow(msg);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.i("organizeFragment="+isVisibleToUser);
    }

    @Override
    public void loadData() {
        if(!isVisible||!isPrepared){
            return;
        }
           LoadingView.showProgress(mActivity);
        mPresent.getGroupList();
        //添加组织结构监听
        mPresent.registerRefresh();
    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_team_organize;
    }

    @Override
    protected void initInject() {
         getFragmentComponent().inject(this);
    }

    @Override    public void showGroupList(List<RectDeptBean> bean) {
        LogUtil.i(bean.toString());
        LoadingView.dismissProgress();
        mOrgView.setData(bean);

    }

}
