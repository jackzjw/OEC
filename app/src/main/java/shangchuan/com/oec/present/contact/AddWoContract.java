package shangchuan.com.oec.present.contact;

import com.yalantis.ucrop.entity.LocalMedia;

import java.util.HashMap;
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
        void upLoadSuccess(String fileName);
    }
    interface Present extends BasePresent<View>{
        void getWoClassType();
        void getChildData(String parentTitle);
        String getChildId(String childTitles);
        void submitWo(HashMap<String,Object> hashMap);
        void upLoadFile(List<LocalMedia> selectMedia,List<String> fileList);
    }

}
