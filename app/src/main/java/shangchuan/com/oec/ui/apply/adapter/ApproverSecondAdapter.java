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
import shangchuan.com.oec.model.bean.UserListBean;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.widget.CircleImageView;

/**
 * Created by sg280 on 2017/4/13.
 */

public class ApproverSecondAdapter extends RecyclerView.Adapter<ApproverSecondAdapter.ViewHolder> {

    private Context mContext;
    private List<UserListBean> mList;
    private boolean isFirst;
   private OnSelectOwnerListener mOwnerListener;

    public ApproverSecondAdapter(Context context,List<UserListBean> bean){
        this.mContext=context;
        this.mList=bean;
    }
  public interface OnSelectOwnerListener{
      void selectedOwnerClick(int position);
      void cancleOwnerClick(int position);
  }
    public void setOwnerListener(OnSelectOwnerListener listener){
        this.mOwnerListener=listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View mview= LayoutInflater.from(mContext).inflate(R.layout.item_approver_second,parent,false);
        final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFirst=!isFirst;
                viewHolder.mSelected.setVisibility(isFirst?View.VISIBLE:View.INVISIBLE);
                if(isFirst){
                    mOwnerListener.selectedOwnerClick(viewHolder.getAdapterPosition());
                }else{
                    mOwnerListener.cancleOwnerClick(viewHolder.getAdapterPosition());
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glides.getInstance().loadCircle(mContext,mList.get(position).getUserAvatar(),holder.mUserAvater);
        holder.mName.setText(mList.get(position).getUserTrueName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.user_avater)
        CircleImageView mUserAvater;
        @BindView(R.id.user_name)
        TextView mName;
        @BindView(R.id.img_selected)
        ImageView mSelected;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
