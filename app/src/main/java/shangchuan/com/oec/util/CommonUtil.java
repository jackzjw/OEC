package shangchuan.com.oec.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.EditText;

import com.luck.picture.lib.model.FunctionConfig;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.yalantis.ucrop.entity.LocalMedia;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rx.functions.Action1;
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
    public static String woStatus(int status){
        switch (status){
            case 0:  return "已撤销";
            case 1:  return "待处理";
            case 2:  return "处理中";
            case 3:  return  "已完成";
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
    public static boolean isNull(String s) {
        if (null == s || s.equals("") || s.equalsIgnoreCase("null")) {
            return true;
        }

        return false;
    }
  //判断EditText是否为空
  public static boolean isEmpty(EditText et){
      String str=et.getText().toString().trim();
      if(TextUtils.isEmpty(str)){
          et.setError("不能为空！");
          return true;
      }
      return false;
  }
    //打电话逻辑
    public static void callPhone(final Context context,final String phoneNo){
        if(context instanceof Activity){
            RxPermissions permissions=new RxPermissions((Activity)context);
            if (!isNull(phoneNo)) {
                permissions.request(Manifest.permission.CALL_PHONE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if(aBoolean) {
                            Intent phoneIntent = new Intent(
                                    "android.intent.action.CALL", Uri.parse("tel:"
                                    + phoneNo));
                            context.startActivity(phoneIntent);
                        }
                    }
                });

            }else {
                ToastUtil.show("暂时未添加联系电话");
            }
        }


    }
    public static String formatDate(String time){
        String monthDay=time.substring(5,10).replace("-","/");
        return monthDay;
    }
    /**
     * 将时间戳转为代表"距现在多久之前"的字符串
     * @param timeStr   时间戳
     * @return
     */
    public static String getStandardDate(String timeStr) {

        StringBuffer sb = new StringBuffer();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long t=0l;
        try {
            Date date = sdf.parse(timeStr);
            t=date.getTime();
        }catch (ParseException e){
            e.printStackTrace();
        }
     //   long t = Long.parseLong(timeStr);
        long time = System.currentTimeMillis() - t;
        long mill = (long) Math.ceil(time /1000);//秒前

        long minute = (long) Math.ceil(time/60/1000.0f);// 分钟前

        long hour = (long) Math.ceil(time/60/60/1000.0f);// 小时

        long day = (long) Math.ceil(time/24/60/60/1000.0f);// 天前

        if (day - 1 > 0) {
            sb.append(day + "天");
        } else if (hour - 1 > 0) {
            if (hour >= 24) {
                sb.append("1天");
            } else {
                sb.append(hour + "小时");
            }
        } else if (minute - 1 > 0) {
            if (minute == 60) {
                sb.append("1小时");
            } else {
                sb.append(minute + "分钟");
            }
        } else if (mill - 1 > 0) {
            if (mill == 60) {
                sb.append("1分钟");
            } else {
                sb.append(mill + "秒");
            }
        } else {
            sb.append("刚刚");
        }
        if (!sb.toString().equals("刚刚")) {
            sb.append("前");
        }
        return sb.toString();
    }
    //打开文本文件
    public static Intent openFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) return null;
        //取得扩展名
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
        if (end.equals("mp4")) {
            return getVideoFileIntent(filePath);
        } else if (end.equals("doc")) {
            return getWordFileIntent(filePath);
        } else if (end.equals("xls")) {
            return getExcelFileIntent(filePath);
        } else if (end.equals("pdf")) {
            return getPdfFileIntent(filePath);
        } else {
            return getAllIntent(filePath);
        }
    }
        public static Intent getAllIntent( String param ) {

            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(new File(param ));
            intent.setDataAndType(uri,"*/*");
            return intent;
        }
    //Android获取一个用于打开VIDEO文件的intent
    public static Intent getVideoFileIntent( String param ) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    //Android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent( String param ){

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }
    //Android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent( String param ){

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }
    //Android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent( String param ){
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }



}
