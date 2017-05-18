package shangchuan.com.oec.ui.home.adapter;

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
import shangchuan.com.oec.model.bean.TrendsListBean;
import shangchuan.com.oec.ui.apply.activity.ApplyOfficeDetailsActivity;
import shangchuan.com.oec.ui.apply.activity.ProjectDetailsActivity;
import shangchuan.com.oec.ui.apply.activity.WoDetailsActivity;
import shangchuan.com.oec.ui.apply.activity.WorkReportDetailActivity;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.widget.CircleImageView;

/**
 * Created by sg280 on 2017/4/21.
 */

public class TrendsListAdapter extends RecyclerView.Adapter<TrendsListAdapter.ViewHolder> {

    private Context mContext;
    private List<TrendsListBean> mList;
    public TrendsListAdapter(Context context,List<TrendsListBean> list){
        this.mContext=context;
        this.mList=list;
    }
   public void updateData(List<TrendsListBean> list){
       this.mList=list;
   }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_dynimic_list,parent,false);
        final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                int jobType=mList.get(position).getJobType();
                int id=mList.get(position).getOrderId();
                switch (jobType){
                    case 1://工单
                        mContext.startActivity(WoDetailsActivity.getInstance(mContext,id,position));
                        break;
                    case 2://办公申请
                        //传的2表示从审核跳转过去的
                        mContext.startActivity(ApplyOfficeDetailsActivity.newIntent(mContext,id,2,position));
                        break;
                    case 3://工作报告
                        //2表示审阅
                        mContext.startActivity(WorkReportDetailActivity.getInstance(mContext,id,2));
                        break;
                    case 4://项目任务
                        mContext.startActivity(ProjectDetailsActivity.getInstance(mContext,id+""));
                        break;
                }

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glides.getInstance().loadCircle(mContext, MySelfInfo.getInstance().getAvatar(),holder.mUserAvater);
        holder.mTitle.setText(MySelfInfo.getInstance().getNickName()+"["+mList.get(position).getJobTitle()+"]");
        holder.mContent.setText(mList.get(position).getJobContent());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.user_avatar)
        CircleImageView mUserAvater;
        @BindView(R.id.dynamic_title)
        TextView mTitle;
        @BindView(R.id.dynamic_content)
        TextView mContent;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
