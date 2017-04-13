package shangchuan.com.oec.ui.apply.adapter;

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
import shangchuan.com.oec.model.bean.CustomerListBean;
import shangchuan.com.oec.ui.apply.activity.ClientDetailsActivity;
import shangchuan.com.oec.util.CommonUtil;

/**
 * Created by sg280 on 2017/4/10.
 */

public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ViewHolder> {


    private Context mContext;
    private List<CustomerListBean> mList;
    public ClientListAdapter(Context context,List<CustomerListBean> list){
        this.mContext=context;
        mList=list;

    }
   public void updateData(List<CustomerListBean> bean){
       this.mList=bean;
   }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_personal_list,parent,false);
       final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  mContext.startActivity(ClientDetailsActivity.getInstance(mContext,mList.get(viewHolder.getAdapterPosition()).getId()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

          holder.mName.setText(mList.get(position).getCustomerShortName());
          holder.mCompanyName.setText(mList.get(position).getCustomerName());
        holder.mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final  String phoneNo=mList.get(position).getCustomerTel();
                //拨打电话
                CommonUtil.callPhone(mContext,phoneNo);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_name)
        TextView mName;
        @BindView(R.id.personal_job)
        TextView mCompanyName;
        @BindView(R.id.call_phone)
        ImageView mCall;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
