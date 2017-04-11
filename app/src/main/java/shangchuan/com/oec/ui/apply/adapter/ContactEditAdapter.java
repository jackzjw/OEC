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
import shangchuan.com.oec.model.bean.ContactListBean;

/**
 * Created by sg280 on 2017/4/11.
 */

public class ContactEditAdapter extends RecyclerView.Adapter<ContactEditAdapter.ViewHold> {
    private Context mContext;
    private List<ContactListBean> mList;
    private boolean isEdit;
    private OnItemEditClickListener mEditClickListener;
    public ContactEditAdapter(Context context,List<ContactListBean> list){
        this.mContext=context;
        this.mList=list;
    }
    public interface OnItemEditClickListener{
        void onDeleteClick(int pos);
        void onModifyClick(int pos);
    }
    public void setEditClickListener(OnItemEditClickListener listener){
        this.mEditClickListener=listener;
    }


    public void setEdit(boolean edit){
        this.isEdit=edit;
        notifyDataSetChanged();
    }
    public void updateData(List<ContactListBean> list){
        this.mList=list;
        notifyDataSetChanged();
    }


    @Override
    public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_edit_contacter_list,parent,false);
        final ViewHold viewHold=new ViewHold(mview);
        viewHold.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     mEditClickListener.onDeleteClick(viewHold.getAdapterPosition());
            }
        });
        viewHold.mModeify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditClickListener.onModifyClick(viewHold.getAdapterPosition());
            }
        });
        return viewHold;
    }

    @Override
    public void onBindViewHolder(ViewHold holder, int position) {
        holder.mName.setText(mList.get(position).getContactName());
        holder.mJob.setText(mList.get(position).getContactDept());
        holder.mEmail.setText(mList.get(position).getContactEmail());
        holder.mTel.setText(mList.get(position).getContactTel());
        holder.mDelete.setVisibility(isEdit?View.VISIBLE:View.INVISIBLE);
        holder.mModeify.setVisibility(isEdit?View.VISIBLE:View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHold extends RecyclerView.ViewHolder{

        @BindView(R.id.user_name)
        TextView mName;
        @BindView(R.id.user_job)
        TextView mJob;
        @BindView(R.id.user_email)
        TextView mEmail;
        @BindView(R.id.tv_delete)
        TextView mDelete;
        @BindView(R.id.tv_modify)
        TextView mModeify;
        @BindView(R.id.user_call)
        TextView mTel;

        public ViewHold(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
