package shangchuan.com.oec.ui.apply.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.SelectOwnerBean;

/**
 * Created by sg280 on 2017/4/13.
 */

public class DelApproverAdapter extends RecyclerView.Adapter<DelApproverAdapter.ViewHolder> {

    private Context mContext;
    private List<SelectOwnerBean> mList;
    private OnDelOwnerClickListener mOwnerClickListener;
    public DelApproverAdapter(Context context,List<SelectOwnerBean> list){
        this.mContext=context;
        this.mList=list;
    }
    public interface OnDelOwnerClickListener{
        void delClick(int position);
    }
    public void setOwnerClickListener(OnDelOwnerClickListener listener){
        this.mOwnerClickListener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(mContext).inflate(R.layout.item_delete_approve, parent, false);
       final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.mDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOwnerClickListener.delClick(viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mOwnerName.setText(mList.get(position).getOwnerName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

     @BindView(R.id.owner_name)
        TextView mOwnerName;
        @BindView(R.id.delete_name)
        ImageView mDel;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
