package shangchuan.com.oec.util;

import android.app.Activity;

import java.util.Calendar;
import java.util.Locale;

import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;

/**
 * Created by sg280 on 2017/4/17.
 */

public class PickerUtil {

    private static PickerUtil pickUtil;

    public static PickerUtil getInstance(){
        if(pickUtil==null) {
            pickUtil = new PickerUtil();
        }
        return pickUtil;
    }
    public DateTimePicker setDateTimePick(Activity context){
        DateTimePicker picker=new DateTimePicker(context,DateTimePicker.HOUR_24);
        picker.setDateRangeStart(2016,1,1);
        picker.setDateRangeEnd(2017,12,31);
        Calendar c = Calendar.getInstance(Locale.CHINA);
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);
        int hour=c.get(Calendar.HOUR);
        picker.setSelectedItem(year,month+1,day,hour,0);
        picker.show();
        return picker;
    }
   public OptionPicker setOptionPick(Activity activity,String[] data){
       OptionPicker optionPicker=new OptionPicker(activity,data);
       optionPicker.setShadowVisible(false);
       optionPicker.setLineVisible(true);
       optionPicker.setTextSize(14);
       optionPicker.setAnimationStyle(R.style.ActionSheetDialogAnimation);
       optionPicker.show();
       return optionPicker;
   }
    public OptionPicker setOptionPickLabel(Activity activity,String[] data,String label){
        OptionPicker optionPicker=new OptionPicker(activity,data);
        optionPicker.setShadowVisible(false);
        optionPicker.setLineVisible(true);
        optionPicker.setTextSize(14);
        optionPicker.setLabel(label);
        optionPicker.setAnimationStyle(R.style.ActionSheetDialogAnimation);
        optionPicker.show();
        return optionPicker;
    }

   public String getCurrentDate(int mode){
       Calendar c = Calendar.getInstance(Locale.CHINA);
       int year=c.get(Calendar.YEAR);
       int month=c.get(Calendar.MONTH);
       int day=c.get(Calendar.DAY_OF_MONTH);
       int hour=c.get(Calendar.HOUR);
       if(mode== Constants.YEAR_MONTH_DAY_HOUR){
           return year+"-"+month+"-"+day+" "+hour+":00";
       }else if(mode==Constants.YEAR_MONTH_DAY){
           return year+"-"+month+"-"+day;
       }else {
           return year+"-"+month+"-"+day;
       }


   }





}
