package shangchuan.com.oec.ui.team.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.UserInfoBean;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.widget.CircleImageView;

/**
 * Created by sg280 on 2017/4/12.
 */

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.ViewHolder> implements SectionIndexer{

    private Context mContext;
    private List<UserInfoBean> mList;
    private OnItemClickListener mOnItemClickListener;
    public SortAdapter(Context context,List<UserInfoBean> bean){
        this.mContext=context;
        this.mList=bean;
    }
    public interface OnItemClickListener{
        void itemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener=listener;
    }
    public void updateData(List<UserInfoBean> list){
        this.mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_sort_user,parent,false);
        final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.itemClick(viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glides.getInstance().loadCircle(mContext,mList.get(position).getUserAvatar(),holder.mAvater);
            holder.mName.setText(mList.get(position).getUserNickName()+"("+mList.get(position).getUserTrueName()+")");
            if(mList.get(position).getIsAdmin()==1){
                holder.mIsAdmin.setVisibility(View.VISIBLE);
            }else {
                holder.mIsAdmin.setVisibility(View.GONE);
            }
         int section=getSectionForPosition(position);
        if(position==getPositionForSection(section)){
              holder.mFirstLetter.setVisibility(View.VISIBLE);
            holder.mFirstLetter.setText(mList.get(position).getSortLetter());
        }else {
               holder.mFirstLetter.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for(int i=0;i<mList.size();i++){
            if(mList.get(i).getSortLetter().charAt(0)==sectionIndex){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return mList.get(position).getSortLetter().charAt(0);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.user_avater)
        CircleImageView mAvater;
        @BindView(R.id.first_letter)
        TextView mFirstLetter;
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
