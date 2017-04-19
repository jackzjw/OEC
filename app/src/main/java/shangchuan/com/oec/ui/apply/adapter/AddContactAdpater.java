package shangchuan.com.oec.ui.apply.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.ContactInfoBean;

/**
 * Created by sg280 on 2017/4/19.
 */

public class AddContactAdpater extends RecyclerView.Adapter<AddContactAdpater.ViewHolder>{
    private Context mContext;
    private List<ContactInfoBean> mList;
    public AddContactAdpater(Context context,List<ContactInfoBean> list){
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_contact_name,parent,false);
        return new ViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
           holder.mName.setText(mList.get(position).getContactName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.contact_name)
        TextView mName;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
