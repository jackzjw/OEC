package shangchuan.com.oec.ui.apply.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.util.Glides;

/**
 * Created by sg280 on 2017/4/18.
 */

public class ScanImgAdapter extends RecyclerView.Adapter<ScanImgAdapter.ViewHolder> {

    private List<String> urls;
    private Context mContext;
    private OnItemClickListener mItemClickListener;
    private int mediaType=1;
    public ScanImgAdapter(Context context,List<String> list){
        this.urls=list;
        this.mContext=context;
    }
    public interface  OnItemClickListener{
        void imgClick(int position,int mediaType);
    }
    public void setItemClickListener(OnItemClickListener listener){
        this.mItemClickListener=listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_scan_img,parent,false);
        final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type=urls.get(viewHolder.getAdapterPosition()).split(".")[1];
                if(type.equals("mp4")){
                    mItemClickListener.imgClick(viewHolder.getAdapterPosition(), Constants.VEDIO_TYPE);
                }else {
                    mItemClickListener.imgClick(viewHolder.getAdapterPosition(),Constants.IMAGE_TYPE);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glides.getInstance().load(mContext,urls.get(position),holder.img);

           if(urls.get(position).split(".")[1].equals("mp4")){
                holder.mPlay.setVisibility(View.VISIBLE);
           }else {
               holder.mPlay.setVisibility(View.GONE);
           }
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imageview)
        ImageView img;
        @BindView(R.id.play_icon)
        ImageView mPlay;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
