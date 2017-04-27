package shangchuan.com.oec.ui.team.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.RoleListBean;

/**
 * Created by sg280 on 2017/4/27.
 */

public class RoleListAdapter extends RecyclerView.Adapter<RoleListAdapter.ViewHolder> {

    private Context mContext;
    private List<RoleListBean> mList;
    private HashSet<RoleListBean> hashSet;
    private boolean isChecked;
    public RoleListAdapter(Context context, List<RoleListBean> list){
        this.mContext=context;
        this.mList=list;
        hashSet=new HashSet<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_role_list,parent,false);
        final ViewHolder viewHolder = new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                isChecked=!isChecked;
                viewHolder.mTag.setVisibility(isChecked?View.VISIBLE:View.GONE);
                RoleListBean bean = mList.get(position);
                if(isChecked) {
                    hashSet.add(bean);
                }else {
                    hashSet.remove(bean);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
             holder.mTag.setImageResource(R.drawable.user_icon_choose_selected);
           holder.mName.setText(mList.get(position).getRoleName());
           holder.mRemark.setText("备注："+mList.get(position).getRemark());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
   public HashSet<RoleListBean> getSelectedRoles(){
       return hashSet;
   }
    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.role_name)
        TextView mName;
        @BindView(R.id.role_remark)
        TextView mRemark;
        @BindView(R.id.role_tag)
        ImageView mTag;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
