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
import shangchuan.com.oec.model.bean.ProjectTrendsListBean;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.widget.CircleImageView;

/**
 * Created by sg280 on 2017/5/19.
 */

public class ProjectTrendListAdapter extends RecyclerView.Adapter<ProjectTrendListAdapter.ViewHolder> {
    private Context mContext;
    private List<ProjectTrendsListBean> mList;
    public ProjectTrendListAdapter(Context context,List<ProjectTrendsListBean> list){
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(mContext).inflate(R.layout.item_project_trend_list, parent, false);
        return new ViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glides.getInstance().loadCircle(mContext,mList.get(position).getPreUrl(),holder.mUserAvater);
        holder.mContent.setText(mList.get(position).getUserTrueName()+mList.get(position).getProcessInfo());
        if(mList.get(position).getIsComment().equals("1")){
            holder.mRemark.setText(mList.get(position).getRemark());
        }else {
            holder.mRemark.setVisibility(View.GONE);
        }
        String date=mList.get(position).getCreateTime();
        String hours=date.substring(date.lastIndexOf(" ")+1,date.length());
        holder.mDate.setText(hours);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
     @BindView(R.id.user_avater)
        CircleImageView mUserAvater;
        @BindView(R.id.content1)
        TextView mContent;
        @BindView(R.id.content2)
        TextView mRemark;
        @BindView(R.id.date)
        TextView mDate;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
