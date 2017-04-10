package shangchuan.com.oec.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import shangchuan.com.oec.R;

/**
 * Created by sg280 on 2017/3/28.
 */

public class LoadingView extends Dialog {
    private static LoadingView mLoadView;




    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, int themeResId) {
        super(context, themeResId);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public static void showProgress(Context context){

        showProgress(context,"正在加载");

    }
    public static void showProgress(Context context,String message){
        mLoadView=new LoadingView(context,R.style.loading_dialog_style);
        View mview= LayoutInflater.from(context).inflate(R.layout.dialog_loadview,null);
        TextView tvMsg=(TextView)mview.findViewById(R.id.message);
        tvMsg.setText(message);
        mLoadView.setContentView(mview);

        mLoadView.show();


    }

    private void init(){

      //  getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, 50);

    }
   /* public static void Show(Context context){
        if(context instanceof Activity){
            if(!((Activity)context).isFinishing()) {
                if (lv == null) {
                    lv = new LoadingView(context);
                }
                lv.show();
            }
        }
    }*/
    public static void dismissProgress(){
        if(mLoadView!=null){
            mLoadView.dismiss();
        }
    }



}
