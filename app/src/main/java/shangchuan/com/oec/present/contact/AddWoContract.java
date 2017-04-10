package shangchuan.com.oec.present.contact;

import com.yalantis.ucrop.entity.LocalMedia;

import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;

/**
 * Created by sg280 on 2017/4/6.
 */

public interface AddWoContract  {
    interface View extends BaseView{
        void showChildData(String[] childTitles);
        void showParentData(String[] parentTitles);
        void showSuccess();
        void upLoadSuccess(String[] fileName);
    }
    interface Present extends BasePresent<View>{
        void getWoClassType();
        void getChildData(String parentTitle);
        int getChildId(String childTitles);
        void submitWo(int bid,int flag,String orderTitle,String orderContent,int[] handlers,String[] filesName);
        void upLoadFile(List<LocalMedia> selectMedia);
    }

}
