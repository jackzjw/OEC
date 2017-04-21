package shangchuan.com.oec.present.contact;

import com.yalantis.ucrop.entity.LocalMedia;

import java.util.HashMap;
import java.util.List;

import shangchuan.com.oec.base.BasePresent;
import shangchuan.com.oec.base.BaseView;

/**
 * Created by sg280 on 2017/4/14.
 */

public interface AddApplyContract {
    interface View extends BaseView{
        void AddSuccess();
        void upLoadFileSuccess(String filename);
    }
    interface  Present extends BasePresent<View>{
        void submitData(HashMap<String,Object> map);
        void upLoadFile(List<LocalMedia> medias,List<String> fileList);
    }
}
