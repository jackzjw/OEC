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
import shangchuan.com.oec.model.bean.OaItemBean;
import shangchuan.com.oec.ui.apply.activity.ApplyOfficeDetailsActivity;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.util.SharePreferenceUtil;
import shangchuan.com.oec.widget.CircleImageView;

/**
 * Created by sg280 on 2017/3/30.
 */

public class OaListAdapter extends RecyclerView.Adapter<OaListAdapter.TypeViewHolder> {
    private Context mContext;
    private List<OaItemBean> mList;
    public OaListAdapter(Context context, List<OaItemBean> list){
        this.mContext=context;
        this.mList=list;
    }
    @Override
    public TypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_search_oa,parent,false);
        return new TypeViewHolder(mview);
    }
    public void updateData(List<OaItemBean> data){
        this.mList=data;
    }


    @Override
    public void onBindViewHolder(final TypeViewHolder holder,final int position) {
          holder.mDesc.setText(mList.get(position).getOrderContent());
        Glides.getInstance().loadCircle(mContext,SharePreferenceUtil.getUserAvater(),holder.user_avater);
        holder.mtype.setText(mList.get(position).getOrderTitle());
        holder.mTime.setText(mList.get(position).getOrderTime());
        int status=mList.get(position).getOrderStatus();
        holder.mStatus.setText(CommonUtil.orderStatus(status));
        if(status==1){
            holder.mStatus.setTextColor(ContextCompat.getColor(mContext,R.color.apply_wait_approve_text_color));
        }else {
            holder.mStatus.setTextColor(ContextCompat.getColor(mContext,R.color.theme_dark_text_color));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳到详情界面
                mContext.startActivity(ApplyOfficeDetailsActivity.newIntent(mContext,mList.get(position).getId(),1,position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class TypeViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.user_avater)
        CircleImageView user_avater;
        @BindView(R.id.tv_oa_type)
        TextView mtype;
        @BindView(R.id.tv_oa_describe)
        TextView mDesc;
        @BindView(R.id.tv_wo_status)
        TextView mStatus;
        @BindView(R.id.tv_approve_time)
        TextView mTime;

        public TypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
