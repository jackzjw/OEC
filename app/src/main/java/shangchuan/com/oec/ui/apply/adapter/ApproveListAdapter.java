package shangchuan.com.oec.ui.apply.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.MySelfInfo;
import shangchuan.com.oec.model.bean.OaItemBean;
import shangchuan.com.oec.ui.apply.activity.ApplyOfficeDetailsActivity;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.widget.CircleImageView;

/**
 * Created by sg280 on 2017/4/20.
 */

public class ApproveListAdapter extends RecyclerView.Adapter<ApproveListAdapter.ViewHolder> {

    private Context mContext;
    private List<OaItemBean> mList;
    private int mIndex;
    public ApproveListAdapter(Context context,List<OaItemBean> list,int index){
        this.mContext=context;
        this.mList=list;
        this.mIndex=index;
    }
    public void updateData(List<OaItemBean> bean){
        this.mList=bean;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_search_oa,parent,false);
        final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=mList.get(viewHolder.getAdapterPosition()).getId();
                mContext.startActivity(ApplyOfficeDetailsActivity.newIntent(mContext,id,mIndex,viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glides.getInstance().loadCircle(mContext, MySelfInfo.getInstance().getAvatar(),holder.mUserAvater);
        holder.mTitle.setText(mList.get(position).getCreateUserName()+"["+mList.get(position).getOrderTitle()+"]");
        holder.mDesc.setText(mList.get(position).getOrderContent());
        holder.mStatus.setText(CommonUtil.orderStatus(mList.get(position).getOrderStatus()));
        if(mList.get(position).getOrderStatus()==1){
            holder.mStatus.setTextColor(ContextCompat.getColor(mContext,R.color.apply_wait_approve_text_color));
        }else {
            holder.mStatus.setTextColor(ContextCompat.getColor(mContext,R.color.theme_dark_text_color));
        }
        holder.mDate.setText(CommonUtil.getStandardDate(mList.get(position).getCreateTime()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.user_avater)
        CircleImageView mUserAvater;
        @BindView(R.id.tv_oa_type)
        TextView mTitle;
        @BindView(R.id.tv_oa_describe)
        TextView mDesc;
        @BindView(R.id.tv_approve_time)
        TextView mDate;
        @BindView(R.id.tv_wo_status)
        TextView mStatus;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
