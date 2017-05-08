package shangchuan.com.oec.ui.home.adapter;

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
import shangchuan.com.oec.model.bean.NewsListBean;
import shangchuan.com.oec.ui.home.activity.BannerDetailsActivity;
import shangchuan.com.oec.util.CommonUtil;

/**
 * Created by sg280 on 2017/4/21.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
   private Context mContext;
    private List<NewsListBean> mList;

    public NewsListAdapter(Context context,List<NewsListBean> list){
        this.mContext=context;
        this.mList=list;
    }
    public void updateData(List<NewsListBean> list){
        this.mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_banner_list,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  int id=mList.get(viewHolder.getAdapterPosition()).getId();
                   mContext.startActivity(BannerDetailsActivity.getInstance(mContext,id,viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
          holder.mTitle.setText(mList.get(position).getClassName());
          holder.mContent.setText(mList.get(position).getInfo());
          holder.mStatus.setImageResource(mList.get(position).getReadStatus()==0?R.drawable.home_icon_news_unread:R.drawable.home_icon_news_read);
          holder.mDate.setText(CommonUtil.formatDate(mList.get(position).getCreateTime()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
      @BindView(R.id.banner_status)
        ImageView mStatus;
        @BindView(R.id.banner_title)
        TextView mTitle;
        @BindView(R.id.banner_content)
        TextView mContent;
        @BindView(R.id.darily_time)
        TextView mDate;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
