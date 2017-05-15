package shangchuan.com.oec.ui.apply.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
import shangchuan.com.oec.model.bean.TaskListBean;
import shangchuan.com.oec.ui.apply.activity.TaskDetailsActivity;
import shangchuan.com.oec.util.CommonUtil;

/**
 * Created by sg280 on 2017/5/5.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private Context mContext;
    private List<TaskListBean> mList;
    private int mStatus;
    public TaskListAdapter(Context context,List<TaskListBean> list,int status){
        this.mContext=context;
        this.mList=list;
        this.mStatus=status;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_project,parent,false);
        final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=viewHolder.getAdapterPosition();
             mContext.startActivity(TaskDetailsActivity.getInstance(mContext,mList.get(pos).getId()+""));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(mStatus==2){
            //已完成
            holder.mImgFinish.setImageResource(R.drawable.application_button_finish_selected);
        }else {
            holder.mImgFinish.setImageResource(R.drawable.application_button_finish_normal);
        }
        holder.mContent.setText(mList.get(position).getTaskTitle());
        holder.mResponseName.setText(mList.get(position).getUserName());
        holder.mProjectName.setText(mList.get(position).getProjectName());
        holder.mDate.setText(CommonUtil.formatDate(mList.get(position).getDeadline()).replace("/","月").concat("日"));
        holder.mDate.setTextColor(mStatus==0? ContextCompat.getColor(mContext,R.color.apply_wait_approve_text_color):ContextCompat.getColor(mContext,R.color.project_gray_text_color));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.finish_normal)
        ImageView mImgFinish;
        @BindView(R.id.task_content)
        TextView mContent;
        @BindView(R.id.reponse_name)
        TextView mResponseName;
        @BindView(R.id.finish_date)
        TextView mDate;
        @BindView(R.id.project_name)
        TextView mProjectName;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
