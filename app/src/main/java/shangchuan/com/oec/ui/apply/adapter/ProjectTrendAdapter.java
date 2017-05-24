package shangchuan.com.oec.ui.apply.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.ProjectTrendsBean;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.widget.DividerDecoration;

/**
 * Created by sg280 on 2017/5/19.
 */

public class ProjectTrendAdapter extends RecyclerView.Adapter<ProjectTrendAdapter.ViewHolder> {

    private Context mContext;
    private List<ProjectTrendsBean> mList;
    private SimpleDateFormat sdf;
    private String currentDate;
    private String yesterday;

    public ProjectTrendAdapter(Context context,List<ProjectTrendsBean> list){
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(mContext).inflate(R.layout.item_project_trend, parent, false);
       sdf=new SimpleDateFormat("MM-dd");
        currentDate= sdf.format(Calendar.getInstance().getTime());
        String month;
        int Month=Calendar.getInstance().get(Calendar.MONTH)+1;
        if(Month<10){
            month="0"+Month;
        }else {
            month=Month+"";
        }
        yesterday=month+"-"+ (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1);
        LogUtil.i(yesterday);
        LogUtil.i(currentDate);
        return new ViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
     String date= mList.get(position).getTheDate().substring(5,10);
          if(date.equals(currentDate)){
              holder.mDate.setText("今天");
          }else if(yesterday.equals(date)){
              holder.mDate.setText("昨天");
          }else {
              holder.mDate.setText(date.replace("-","月").concat("日"));
          }
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.mRecyclerView.addItemDecoration(new DividerDecoration(mContext));
        holder.mRecyclerView.setAdapter(new ProjectTrendListAdapter(mContext,mList.get(position).getProjectProcessList()));



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.trend_date)
    TextView mDate;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;


    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
}
