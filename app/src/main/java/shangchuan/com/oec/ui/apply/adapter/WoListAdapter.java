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
import shangchuan.com.oec.model.bean.WoListBean;
import shangchuan.com.oec.ui.apply.activity.WoDetailsActivity;
import shangchuan.com.oec.util.CommonUtil;

/**
 * Created by sg280 on 2017/4/6.
 */

public class WoListAdapter extends RecyclerView.Adapter<WoListAdapter.WoListViewHolder> {

   private Context mContext;
    private List<WoListBean> mList;
    public WoListAdapter(Context context,List<WoListBean> list){
        this.mContext=context;
        this.mList=list;
    }
    public void updateData(List<WoListBean> bean){
            mList.addAll(bean);
    }
    @Override
    public WoListViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_wo_list,parent,false);
        final WoListViewHolder viewHolder=new WoListViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=mList.get(viewHolder.getAdapterPosition()).getId();
                mContext.startActivity(WoDetailsActivity.getInstance(mContext,id));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WoListViewHolder holder, int position) {
        holder.orderFlag.setImageResource(CommonUtil.orderFlag(mList.get(position).getOrderFlag()));
        holder.orderTitle.setText(mList.get(position).getOrderTitle());
        holder.orderType.setText(mList.get(position).getClassNameA()+" - "+mList.get(position).getClassNameB());
        holder.orderDate.setText(mList.get(position).getCreateTime());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class WoListViewHolder extends RecyclerView.ViewHolder{
      @BindView(R.id.image_tag)
        ImageView orderFlag;
        @BindView(R.id.tv_wo_info)
        TextView orderTitle;
        @BindView(R.id.tv_wo_info_2)
        TextView orderType;
        @BindView(R.id.tv_apply_time)
        TextView orderDate;
        public WoListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
