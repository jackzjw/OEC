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

/**
 * Created by sg280 on 2017/4/21.
 */

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mList;
    private DelFileClickListener mFileClickListener;
    public FileListAdapter(Context context,List<String> list,DelFileClickListener listener){
        this.mContext=context;
        this.mList=list;
        this.mFileClickListener=listener;
    }
    public interface DelFileClickListener{
        void delClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_file_list,parent,false);
        final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.mDelFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFileClickListener.delClick(viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
             holder.mFileName.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.file_name)
        TextView mFileName;
        @BindView(R.id.del_file)
        ImageView mDelFile;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
