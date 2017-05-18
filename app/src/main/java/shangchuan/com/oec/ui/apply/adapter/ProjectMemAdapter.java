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
import shangchuan.com.oec.model.bean.UserInfoBean;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.widget.CircleImageView;
import shangchuan.com.oec.present.contact.OnItemClickListener;

/**
 * Created by sg280 on 2017/5/17.
 */

public class ProjectMemAdapter extends RecyclerView .Adapter<ProjectMemAdapter.ViewHolder>{

    private Context mContext;
    private List<UserInfoBean>  mList;
    private OnItemClickListener mListener;
    public ProjectMemAdapter(Context context, List<UserInfoBean> list,int type){
         this.mContext=context;
        this.mList=list;
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener=listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_project_member_user,parent,false);
        final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          if(mListener!=null){
              mListener.onClick(viewHolder.getAdapterPosition());
          }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glides.getInstance().loadCircle(mContext,mList.get(position).getUserAvatar(),holder.mAvater);
        holder.mName.setText(mList.get(position).getUserNickName());
        holder.mIsAdmin.setVisibility(mList.get(position).getIsAdmin()==0?View.GONE:View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.user_avater)
        CircleImageView mAvater;
        @BindView(R.id.user_name)
        TextView mName;
        @BindView(R.id.is_admin)
        TextView mIsAdmin;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
