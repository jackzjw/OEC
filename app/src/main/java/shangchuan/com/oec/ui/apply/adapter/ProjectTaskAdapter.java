package shangchuan.com.oec.ui.apply.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.ClassListBean;
import shangchuan.com.oec.ui.apply.activity.AddTaskActivity;
import shangchuan.com.oec.widget.DividerDecoration;

/**
 * Created by sg280 on 2017/5/17.
 */

public class ProjectTaskAdapter extends RecyclerView.Adapter<ProjectTaskAdapter.ViewHolder>{
   private Context mContext;
    private List<ClassListBean> mList;
    public ProjectTaskAdapter(Context context,List<ClassListBean> list){
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(mContext).inflate(R.layout.item_project_task_list, parent, false);
        final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.mAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String projectId=mList.get(viewHolder.getAdapterPosition()).getProjectId();
                String id=mList.get(viewHolder.getAdapterPosition()).getId();
                mContext.startActivity(AddTaskActivity.getInstance(mContext,projectId,id));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
           holder.mName.setText(mList.get(position).getClassName());
            TaskListAdapter adapter=new TaskListAdapter(mContext,mList.get(position).getTask_list(),0);
            holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
           holder.mRecyclerView.addItemDecoration(new DividerDecoration(mContext));
           holder.mRecyclerView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.class_name)
        TextView mName;
        @BindView(R.id.recycleview)
        RecyclerView mRecyclerView;
        @BindView(R.id.add_new_task)
        TextView mAddTask;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
