package shangchuan.com.oec.ui.apply.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yalantis.ucrop.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;

/**
 * Created by sg280 on 2017/4/7.
 */

public class GridImgAdapter extends RecyclerView.Adapter<GridImgAdapter.ViewHolder> {
  private Context mContext;
  private onDelPickClickListener mOnDelPickClickListener;
    private List<LocalMedia> mList=new ArrayList<>();
    public interface  onDelPickClickListener{
        void delClickPick(int position);
    }
    public GridImgAdapter(Context context,onDelPickClickListener listener){
        this.mContext=context;
        this.mOnDelPickClickListener=listener;
    }
     public void setData(List<LocalMedia> list){
         mList=list;
         notifyDataSetChanged();
     }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.gv_filter_image,parent,false);
       final ViewHolder viewHolder=new ViewHolder(mview);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(viewHolder.getAdapterPosition(),v);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.ll_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDelPickClickListener.delClickPick(holder.getAdapterPosition());
            }
        });
        LocalMedia media = mList.get(position);
        int type = media.getType();
        String path = "";
        if (media.isCut() && !media.isCompressed()) {
            // 裁剪过
            path = media.getCutPath();
        } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
            path = media.getCompressPath();
        } else {
            // 原图
            path = media.getPath();
        }

        switch (type) {
            case 1:
                // 图片
                if (media.isCompressed()) {
                    Log.i("compress image result", new File(media.getCompressPath()).length() / 1024 + "k");
                }

                Glide.with(mContext)
                        .load(path)
                        .asBitmap().centerCrop()
                        .placeholder(R.color.color_f6)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.Img);
                break;
            case 2:
                // 视频
              //  Glide.with(mContext).load(Uri.fromFile(new File(path))).into(holder.Img);
                //thunbnail是缩略的倍数，0.5f表示缩小为原来像素的一半
                Glide.with(mContext).load(path).thumbnail(0.5f).into(holder.Img);
                break;
            default:

                break;
        }




    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
       @BindView(R.id.fiv)
        ImageView Img;
        @BindView(R.id.ll_del)
        LinearLayout ll_del;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
