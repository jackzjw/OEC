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

public class OaRemarkAdapter extends RecyclerView.Adapter<OaRemarkAdapter.ViewHolder> {

    private Context mContext;
    private List<ProcessListBean> mList;
    public OaRemarkAdapter(Context context, List<ProcessListBean> list){
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
        //2：通过，3：转他人阅读 4：驳回
        int processResult=mList.get(position).getProcessResult();
        if(processResult==2) {
            holder.mRemark.setVisibility(View.GONE);
        }else {
            holder.mRemark.setVisibility(View.VISIBLE);
            holder.mRemark.setText(mList.get(position).getRemark());

        }
        String userName=mList.get(position).getUserName();
        switch (processResult){
            case 4: holder.mUserName.setText(userName+"驳回了你的申请"); break;
            case 2: holder.mUserName.setText(userName+"通过了你的申请"); break;
            case 3:
                holder.mUserName.setText(userName+"转给");
                holder.mResultName.setText(mList.get(position).getToUserName()+"处理");
                break;

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
