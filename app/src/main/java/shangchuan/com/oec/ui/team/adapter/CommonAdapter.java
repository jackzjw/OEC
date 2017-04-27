package shangchuan.com.oec.ui.team.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by sg280 on 2017/4/27.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter {
    protected Context mContext;
    protected List<T> mList;
    public CommonAdapter(Context context,List<T> list){
        this.mContext=context;
        this.mList=list;
    }


}
