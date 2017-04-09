package shangchuan.com.oec.ui.user.adapter;

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
import shangchuan.com.oec.model.bean.OrganizeInfoBean;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.SharePreferenceUtil;
import shangchuan.com.oec.widget.CircleImageView;

/**
 * Created by sg280 on 2017/3/29.
 */

public class OrganizationListAdapter extends RecyclerView.Adapter<OrganizationListAdapter.OlViewHolder> {
    private final int tenantId;
    private Context mContext;
    private List<OrganizeInfoBean> mlist;
    private ItemOnclickListener mItemOnclickListener;
   public OrganizationListAdapter(List<OrganizeInfoBean> list,Context context){
       this.mContext=context;
       this.mlist=list;
       tenantId= SharePreferenceUtil.getTenantId();
   }
    public void setItemOnclickListener(ItemOnclickListener onclickListener){
        this.mItemOnclickListener=onclickListener;
    }
    @Override
    public OlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View mview= LayoutInflater.from(mContext).inflate(R.layout.item_select_organization,parent,false);
        return new OlViewHolder(mview);
    }
    @Override
    public void onBindViewHolder(final OlViewHolder holder,final int position) {
        Glides.getInstance().loadCircle(mContext,mlist.get(position).getTenantLogo(),holder.mAvater);
        holder.mName.setText(mlist.get(position).getTenantName());
        holder.mCode.setText("机构代码："+mlist.get(position).getTenantCode());
        String phoneNum=mlist.get(position).getAdminMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        holder.mAdmin.setText("管理员："+phoneNum);
            if(mlist.get(position).getId()==tenantId){
                holder.mTag.setVisibility(View.VISIBLE);
            }
        if(mItemOnclickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LogUtil.d("holder="+holder.getAdapterPosition());
                    mItemOnclickListener.OnItemclickListener(position);
                }
            });
        }
    }
    public interface ItemOnclickListener{
        void OnItemclickListener(int position);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class OlViewHolder extends RecyclerView.ViewHolder{
      @BindView(R.id.organize_avater)
        CircleImageView mAvater;
        @BindView(R.id.organize_name)
        TextView mName;
        @BindView(R.id.organize_code)
        TextView mCode;
        @BindView(R.id.organize_admin)
        TextView mAdmin;
        @BindView(R.id.select_tag)
        ImageView mTag;
        public OlViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
