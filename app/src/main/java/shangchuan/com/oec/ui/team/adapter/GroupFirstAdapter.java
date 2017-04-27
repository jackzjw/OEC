package shangchuan.com.oec.ui.team.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.GroupListBean;
import shangchuan.com.oec.widget.DividerDecoration;

/**
 * Created by sg280 on 2017/4/27.
 */

public class GroupFirstAdapter extends RecyclerView.Adapter<GroupFirstAdapter.ViewHolder> {
    private final HashSet<GroupListBean> Groups;
    private Context mContext;
    private List<GroupListBean> mList;
    private boolean isFold;
    public GroupFirstAdapter (Context context,List<GroupListBean> list){
        this.mContext=context;
        this.mList=list;
        Groups=new HashSet<GroupListBean>();
    }
   public HashSet<GroupListBean> getSelectDept(){
       return Groups;
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
        holder.mParentName.setText(mList.get(position).getGroupName()+"["+mList.get(position).getGroup_list().size()+"]");
        GroupSecondAdapter adapter=new GroupSecondAdapter(mContext,mList.get(position).getGroup_list());
        holder.mRecyclerView.setAdapter(adapter);
        adapter.setOwnerListener(new GroupSecondAdapter.OnSelectOwnerListener() {
            @Override
            public void selectedOwnerClick(GroupListBean bean) {
                if(Groups.contains(bean)){
                    Groups.remove(bean);
                }else {
                    Groups.add(bean);
                }
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
