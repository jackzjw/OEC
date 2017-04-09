package shangchuan.com.oec.util;

import android.text.TextUtils;

import com.luck.picture.lib.model.FunctionConfig;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.List;

import shangchuan.com.oec.R;

/**
 * Created by sg280 on 2017/3/27.
 */

public class CommonUtil {
    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][2345789]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }
    public static String orderStatus(int status){
        switch (status){
            case 0:   return "已撤销";
            case 1:   return  "待审核";
            case 2:   return   "已通过";
            case 3:   return  "已驳回";

        }
        return "没有此项";
    }

    public static int orderFlag(int flag){
        switch (flag){
            case 1: return R.drawable.application_wo_report_red_normal;
            case 2: return R.drawable.application_wo_report_blue_normal;
            case 3: return R.drawable.application_wo_report_green_normal;
            case 4: return R.drawable.application_wo_report_yellow_normal;
            case 5: return R.drawable.application_wo_report_purple_normal;
        }

         return R.drawable.application_wo_report_purple_normal;

    }
    public static FunctionConfig configParams(int mediaType, List<LocalMedia> selectMedia){
        FunctionConfig config=new FunctionConfig();
        config.setType(mediaType);
        config.setCompress(true);
        config.setEnablePixelCompress(true);
        config.setEnableQualityCompress(true);
        config.setEnableCrop(false);
        config.setPreviewVideo(true);
       // config.setRecordVideoDefinition(FunctionConfig.HIGH);// 视频清晰度
        config.setRecordVideoSecond(30);// 视频秒数
        config.setCompressQuality(80);
        config.setCompressW(300);
        config.setCompressH(300);
        config.setImageSpanCount(4);
        config.setSelectMedia(selectMedia);
        config.setCompressFlag(2);
      return config;
    }

}
