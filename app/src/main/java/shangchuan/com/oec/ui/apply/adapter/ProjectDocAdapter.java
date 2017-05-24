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
import shangchuan.com.oec.model.bean.ProjectDocumentBean;

/**
 * Created by sg280 on 2017/5/19.
 */

public class ProjectDocAdapter extends RecyclerView.Adapter<ProjectDocAdapter.DocViewHolder> {
    private Context mContext;
    private List<ProjectDocumentBean> mList;

    public ProjectDocAdapter(Context context,List<ProjectDocumentBean> list){
        this.mContext=context;
        this.mList=list;
    }
    @Override
    public ProjectDocAdapter.DocViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_project_docunment,parent,false);
        DocViewHolder viewHolder=new DocViewHolder(mview);
         return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProjectDocAdapter.DocViewHolder holder, int position) {
        String date= mList.get(position).getCreateTime().substring(5,10).replace("-","月").concat("日创建");
       holder.mCreateTime.setText(date);
        holder.mCreateName.setText(mList.get(position).getCreateUserName());
        holder.mName.setText(mList.get(position).getDocumentTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class DocViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.doc_name)
        TextView mName;
        @BindView(R.id.create_name)
        TextView mCreateName;
        @BindView(R.id.create_time)
        TextView mCreateTime;


        public DocViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
