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
import shangchuan.com.oec.model.bean.ProjectListBean;
import shangchuan.com.oec.ui.apply.activity.ProjectDetailsActivity;
import shangchuan.com.oec.widget.HorizontalProgressBar;

/**
 * Created by sg280 on 2017/5/5.
 */

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

   private Context mContext;
    private List<ProjectListBean> mList;
    public ProjectListAdapter(Context context,List<ProjectListBean> list){
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_project_list,parent,false);
        final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=viewHolder.getAdapterPosition();
              mContext.startActivity(ProjectDetailsActivity.getInstance(mContext,mList.get(pos).getId()+""));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mProjectName.setText(mList.get(position).getProjectName());
            holder.mProgressBar.setMax(mList.get(position).getQuantityTotal());
            holder.mProgressBar.setProgress(mList.get(position).getQuantityDone());
            holder.mTag.setVisibility(mList.get(position).getUnDoneCount()==0?View.GONE:View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.project_name)
        TextView mProjectName;
        @BindView(R.id.task_tag)
        TextView mTag;
        @BindView(R.id.progressBar)
        HorizontalProgressBar mProgressBar;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
