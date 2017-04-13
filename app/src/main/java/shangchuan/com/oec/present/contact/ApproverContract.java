package shangchuan.com.oec.present.contact;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;
import shangchuan.com.oec.model.bean.OrgListBean;

/**
 * Created by sg280 on 2017/4/13.
 */

public interface ApproverContract {
      interface View extends BaseView{
          void showContent(List<OrgListBean> bean);
      }
      interface Present extends BasePresent<View>{
          void getOrgList();
      }
}
