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
import shangchuan.com.oec.model.bean.TaskCommentBean;
import shangchuan.com.oec.present.contact.OnItemClickListener;

/**
 * Created by sg280 on 2017/5/15.
 */

public class TaskCommentAdapter extends RecyclerView.Adapter<TaskCommentAdapter.ViewHolder> {
    private Context mContext;
    private List<TaskCommentBean> mList;
    private OnItemClickListener mOnItemClickListener;
    public TaskCommentAdapter(Context context,List<TaskCommentBean> list){
        this.mContext=context;
        this.mList=list;
    }
    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.mOnItemClickListener=clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_remark,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(mList.get(position).getIsComment().equals("1")){
            holder.mRemark.setText(mList.get(position).getRemark());
            holder.mRemark.setVisibility(View.VISIBLE);
        }else {
            holder.mRemark.setVisibility(View.GONE);
        }
        holder.mUserName.setText(mList.get(position).getUserName());
        holder.mResultName.setText(mList.get(position).getProcessInfo());
        holder.mCreateTime.setText(mList.get(position).getCreateTime().replace("-","/"));
        holder.mRemark.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(mOnItemClickListener!=null){
                 mOnItemClickListener.onClick(position);
             }
         }
     });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.user_name)
        TextView mUserName;
        @BindView(R.id.result_name)
        TextView mResultName;
        @BindView(R.id.create_time)
        TextView mCreateTime;
        @BindView(R.id.remark)
        TextView mRemark;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
