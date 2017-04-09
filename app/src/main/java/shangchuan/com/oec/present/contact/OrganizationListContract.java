package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.CharactersTokenBean;
import shangchuan.com.oec.model.bean.OrganizeInfoBean;

/**
 * Created by sg280 on 2017/3/29.
 */

public interface OrganizationListContract {
    interface View extends BaseView{
        void showTenantsResult(List<OrganizeInfoBean> beansList);
        void showCharacterInfo(CharactersTokenBean bean);
    }
    interface Present extends BasePresent<View>{

        void getOzList();
        void enterTenant(int userid,int tenantId);
    }

}
