package shangchuan.com.oec.ui.apply.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.OrgListBean;
import shangchuan.com.oec.model.bean.UserListBean;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.widget.DividerDecoration;

/**
 * Created by sg280 on 2017/4/13.
 */

public class SelectApproveAdapter extends RecyclerView.Adapter<SelectApproveAdapter.ViewHolder> {

    private Context mContext;
    private List<OrgListBean> mList;
    private boolean isFold;
    private HashMap<Integer,String> selectMap;
    public SelectApproveAdapter(Context context,List<OrgListBean> list){
        this.mContext=context;
        this.mList=list;
        selectMap=new HashMap<>();
    }
   public HashMap<Integer,String> getSelectedOwner(){
       return selectMap;
   }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_approver_first,parent,false);
        final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.mLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFold=!isFold;
                viewHolder.mAdd.setText(isFold?"-":"+");
                viewHolder.mRecyclerView.setVisibility(isFold?View.VISIBLE:View.GONE);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.mRecyclerView.addItemDecoration(new DividerDecoration(mContext));
        final List<UserListBean> userList=mList.get(position).getUser_list();
        holder.mParentName.setText(mList.get(position).getOrganizationName()+"("+userList.size()+")");
        ApproverSecondAdapter secondAdapter = new ApproverSecondAdapter(mContext,userList);
        holder.mRecyclerView.setAdapter(secondAdapter);
        secondAdapter.setOwnerListener(new ApproverSecondAdapter.OnSelectOwnerListener() {
            @Override
            public void selectedOwnerClick(int position) {
                LogUtil.i("添加审批人="+userList.get(position).getUserId());
                selectMap.put(userList.get(position).getUserId(),userList.get(position).getUserTrueName());
            }

            @Override
            public void cancleOwnerClick(int position) {
                LogUtil.i("取消审批人="+userList.get(position).getId());
                selectMap.remove(userList.get(position).getUserId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

      @BindView(R.id.tv_add)
        TextView mAdd;
        @BindView(R.id.parent_name)
        TextView mParentName;
        @BindView(R.id.child_recycleview)
        RecyclerView mRecyclerView;
        @BindView(R.id.ll_parent)
        LinearLayout mLinear;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
