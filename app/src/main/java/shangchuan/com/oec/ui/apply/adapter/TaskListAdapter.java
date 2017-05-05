package shangchuan.com.oec.ui.apply.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.TaskListBean;

/**
 * Created by sg280 on 2017/5/5.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private Context mContext;
    private List<TaskListBean> mList;
    private boolean isFinished;
    private static final int TODAY_TASK=1;
    private static final int FUNTURE_TASK=2;
    public TaskListAdapter(Context context,List<TaskListBean> list){
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public int getItemViewType(int position) {
        String dateLine=mList.get(position).getDeadline();
        long t=dealDate(dateLine);
        if(t>0){
            return TODAY_TASK;
        }else {
            return FUNTURE_TASK;
        }
    }
    private long dealDate(String dateLine){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long t=0l;
        try{
            t=sdf.parse(dateLine).getTime();
        }catch (Exception e){
            e.printStackTrace();
        }
     return t-System.currentTimeMillis();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_project,parent,false);
        ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mContent.setText(mList.get(position).getTaskTitle());
        holder.mResponseName.setText(mList.get(position).getUserName());
        holder.mProjectName.setText(mList.get(position).getProjectName());
        holder.mDate.setText(mList.get(position).getDeadline());
        holder.mDate.setTextColor(isFinished? ContextCompat.getColor(mContext,R.color.project_gray_text_color):ContextCompat.getColor(mContext,R.color.apply_wait_approve_text_color));
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
