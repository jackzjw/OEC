package shangchuan.com.oec.ui.apply.fragment;

import java.util.List;

import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.model.bean.OaItemBean;
import shangchuan.com.oec.present.WorkReportListPresent;
import shangchuan.com.oec.present.contact.OaListContract;

/**
 * Created by sg280 on 2017/3/14.
 */

public class MyCreateReportListFragment extends BaseFragment<WorkReportListPresent> implements OaListContract.View {
    @Override
    public void loadData() {
        if(!isPrepared||!isVisible) return;


    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_my_create_report_list;
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(List<OaItemBean> bean) {

    }

    @Override
    public void showMoreContent(List<OaItemBean> bean, int start, int end) {

    }
}
