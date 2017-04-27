package shangchuan.com.oec.ui.team.adapter;

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
import shangchuan.com.oec.model.bean.GroupListBean;

/**
 * Created by sg280 on 2017/4/27.
 */

public class AddGroupNodeAdapter extends RecyclerView.Adapter<AddGroupNodeAdapter.ViewHolder> {
    private Context mContext;
    private List<GroupListBean> mList;
    private OnSelectOwnerListener mOwnerListener;
    private int prePos;
    public AddGroupNodeAdapter(Context context, List<GroupListBean> list){
        this.mContext=context;
        this.mList=list;
    }
    public interface OnSelectOwnerListener{
        void selectedOwnerClick(GroupListBean bean);
    }
    public void setOwnerListener(OnSelectOwnerListener listener){
        this.mOwnerListener=listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(mContext).inflate(R.layout.item_add_group_node, parent, false);
     final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               prePos=viewHolder.getAdapterPosition();
                mOwnerListener.selectedOwnerClick(mList.get(prePos));
                notifyDataSetChanged();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      holder.mTag.setVisibility(prePos==position?View.VISIBLE:View.GONE);
             holder.mDepName.setText(mList.get(position).getGroupName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.dep_name)
        TextView mDepName;
        @BindView(R.id.img_tag)
        ImageView mTag;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
