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
import shangchuan.com.oec.model.bean.AttchmentBean;

/**
 * Created by sg280 on 2017/4/1.
 */

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocViewHolder> {
    private List<AttchmentBean> mList;
    private Context mContext;
    private OnItemClickListener mListener;
    public DocumentAdapter(Context context,List<AttchmentBean> bean){
        this.mContext=context;
        this.mList=bean;
    }
    public interface OnItemClickListener{
        void ItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.mListener=itemClickListener;
    }


    @Override
    public DocViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_document,parent,false);
        return new DocViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(final DocViewHolder holder, int position) {
        holder.mName.setText(mList.get(position).getOldAttFileName());
        if(mListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.ItemClick(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class DocViewHolder extends RecyclerView.ViewHolder{
      @BindView(R.id.doc_name)
        TextView mName;
        public DocViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
