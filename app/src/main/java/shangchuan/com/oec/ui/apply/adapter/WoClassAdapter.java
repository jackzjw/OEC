package shangchuan.com.oec.ui.apply.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import shangchuan.com.oec.model.bean.WoClassBean;

/**
 * Created by sg280 on 2017/4/5.
 */

public class WoClassAdapter extends BaseAdapter {

    private List<WoClassBean> mlist;
    private Context mContext;

  public WoClassAdapter(Context context, List<WoClassBean> list){
      this.mContext=context;
      this.mlist=list;
  }
    public void updateData(List<WoClassBean> bean){
          mlist.clear();
         mlist.addAll(bean);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setTextSize(13);
        tv.setText(mlist.get(position).getClassName());
        return convertView;
    }




}
