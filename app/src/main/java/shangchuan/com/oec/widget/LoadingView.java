package shangchuan.com.oec.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import shangchuan.com.oec.R;

/**
 * Created by sg280 on 2017/3/28.
 */

public class LoadingView extends Dialog {
    private  Context mContext;
    private static LoadingView lv;



    public LoadingView(Context context) {
        this(context,R.style.loading_dialog_style);
    }

    public LoadingView(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext=context;
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void init(){
        View mview= LayoutInflater.from(mContext).inflate(R.layout.dialog_loadview,null);
        setContentView(mview);
      //  getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, 50);

    }
    public static void Show(Context context){
        if(context instanceof Activity){
            if(!((Activity)context).isFinishing()) {
                if (lv == null) {
                    lv = new LoadingView(context);
                }
                lv.show();
            }
        }
    }
    public static void Dismiss(){
        if(lv!=null){
            lv.dismiss();
        }
    }



}
