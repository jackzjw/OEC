package shangchuan.com.oec.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.WoClassBean;
import shangchuan.com.oec.ui.apply.adapter.WoClassAdapter;
import shangchuan.com.oec.util.LogUtil;

/**
 * Created by sg280 on 2017/5/8.
 */

public class MySpinnerView extends LinearLayout{
    private  View mview;
    private  TextView mSpinnerContent;
    private  ImageView mArrow;
    private List<WoClassBean> mList=new ArrayList<>();
    private Context mContext;
    private WoClassAdapter adapter;
    private ListView listView;
    private PopupWindow popupWindow;
    private SpinnerSelectListener mSelectListener;
    public MySpinnerView(Context context) {
        super(context);
    }

    public MySpinnerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MySpinnerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mview = inflater.inflate(R.layout.widget_spinner_view, this);
        mSpinnerContent=(TextView)mview.findViewById(R.id.spinner_content);
        mArrow=(ImageView)mview.findViewById(R.id.spiner_arrow);
    }
    public void setData(List<WoClassBean> data){
         this.mList=data;
        adapter=new WoClassAdapter(mContext,mList);
        mSpinnerContent.setText(mList.get(0).getClassName());
          mArrow.setOnClickListener(new OnClickListener() {
              @Override
              public void onClick(View v) {
                  mArrow.setImageResource(R.drawable.application_wo_list_arrow_selected);
                  mSpinnerContent.setTextColor(ContextCompat.getColor(mContext,R.color.home_text_light_green));
                  showPopupWindow(v);
              }
          });

    }

    private void showPopupWindow(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.widget_spinner_listview, null);
         listView=(ListView)view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        // 实例化一个PopuWindow对象
        popupWindow = new SpinnerPopupWindow(mContext);
        // 设置所在布局
        popupWindow.setContentView(view);
        // 设置弹框出现的位置，在v的正下方横轴偏移textview的宽度
        LogUtil.i("宽度="+(-getWidth()));
        popupWindow.showAsDropDown(v,-mSpinnerContent.getWidth(), 0);
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 mSpinnerContent.setText(mList.get(position).getClassName());
                 mSelectListener.selected(position);
                 popupWindow.dismiss();
             }
         });
    }
    public void updateData(List<WoClassBean> data){
        adapter.updateData(data);
    }
    public interface SpinnerSelectListener{
        void selected(int position);
    }
    public void setSpinnerSelectListener(SpinnerSelectListener listener){
        this.mSelectListener=listener;
    }
}
