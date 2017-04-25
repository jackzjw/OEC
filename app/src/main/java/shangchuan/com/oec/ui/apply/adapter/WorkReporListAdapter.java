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
import shangchuan.com.oec.model.bean.MySelfInfo;
import shangchuan.com.oec.model.bean.WorkReportListBean;
import shangchuan.com.oec.ui.apply.activity.WorkReportDetailActivity;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.widget.CircleImageView;

/**
 * Created by sg280 on 2017/4/18.
 */

public class WorkReporListAdapter extends RecyclerView.Adapter<WorkReporListAdapter.ViewHolder> {
    private List<WorkReportListBean> mList;
    private Context mContext;
    private int mType;
    public WorkReporListAdapter(Context context, List<WorkReportListBean> list,int type){
        this.mList=list;
        this.mContext=context;
        this.mType=type;
    }
    public void updateData(List<WorkReportListBean> list){
        this.mList=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(mContext).inflate(R.layout.item_work_report_list, parent, false);
        final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=mList.get(viewHolder.getAdapterPosition()).getId();
                mContext.startActivity(WorkReportDetailActivity.getInstance(mContext,id,mType));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glides.getInstance().load(mContext, MySelfInfo.getInstance().getAvatar(),holder.mUserAvater);
        holder.mTitle.setText(MySelfInfo.getInstance().getNickName()+"["+mList.get(position).getReportType()+"]");
        if(mList.get(position).getReportType().equals("周报")){
            String duration= CommonUtil.formatDate(mList.get(position).getStartDate())+"-"+CommonUtil.formatDate(mList.get(position).getEndDate());
            holder.mDuration.setText("周报日期 ("+duration+")");
        }else {
            holder.mDuration.setText("日报日期 ("+CommonUtil.formatDate(mList.get(position).getStartDate())+")");
        }
        holder.mContent.setText(mList.get(position).getReportTitle());
        holder.mDate.setText(CommonUtil.formatDate(mList.get(position).getCreateTime()));
        if(mList.get(position).getReportStatus()==0){
            holder.redDot.setVisibility(View.VISIBLE);
        }else {
            holder.redDot.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.user_avater)
        CircleImageView mUserAvater;
        @BindView(R.id.report_type)
        TextView mTitle;
        @BindView(R.id.report_duration)
        TextView mDuration;
        @BindView(R.id.report_content)
        TextView mContent;
        @BindView(R.id.report_create_time)
        TextView mDate;
         @BindView(R.id.red_dot)
         TextView redDot;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
