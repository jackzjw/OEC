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
import shangchuan.com.oec.model.bean.ProcessListBean;

/**
 * Created by sg280 on 2017/4/18.
 */

public class WrRemarkAdapter extends RecyclerView.Adapter<WrRemarkAdapter.ViewHolder> {

    private Context mContext;
    private List<ProcessListBean> mList;
    public WrRemarkAdapter(Context context, List<ProcessListBean> list){
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_remark,parent,false);
        return new ViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //0:未阅读,1:已阅读，2：发消息，3：转他人阅读
        int processResult=mList.get(position).getProcessResult();
        if(processResult==2||processResult==3){
            holder.mRemark.setVisibility(View.VISIBLE);
            holder.mRemark.setText(mList.get(position).getRemark());
        }else {
            holder.mRemark.setVisibility(View.GONE);
        }
        String userName=mList.get(position).getUserName();
        switch (processResult){
            case 0: holder.mUserName.setText(userName+"未阅读"); break;
            case 1: holder.mUserName.setText(userName+"已阅读"); break;
            case 2: holder.mUserName.setText(userName+"发送以下消息"); break;
            case 3:
                holder.mUserName.setText(userName+"转给");
                holder.mResultName.setText(mList.get(position).getToUserName()+"处理");

        }
        holder.mCreateTime.setText(mList.get(position).getCreateTime());
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
