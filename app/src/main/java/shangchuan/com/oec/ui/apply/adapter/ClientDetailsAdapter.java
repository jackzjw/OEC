package shangchuan.com.oec.ui.apply.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.ContactListBean;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.ToastUtil;

/**
 * Created by sg280 on 2017/4/10.
 */

public class ClientDetailsAdapter extends RecyclerView.Adapter<ClientDetailsAdapter.ViewHold> {

    private Context mContext;
    private List<ContactListBean> mList;
    private RxPermissions mRxPermissions;
    public ClientDetailsAdapter(Context context,List<ContactListBean> list){
        this.mContext=context;
        this.mList=list;
        mRxPermissions=new RxPermissions((Activity)context);
    }



    @Override
    public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview= LayoutInflater.from(mContext).inflate(R.layout.item_contacter_list,parent,false);
        final ViewHold viewHold=new ViewHold(mview);
        viewHold.mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String phoneNo=mList.get(viewHold.getAdapterPosition()).getContactTel();
                if (!CommonUtil.isNull(phoneNo)) {
                    mRxPermissions.request(Manifest.permission.CALL_PHONE).subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean aBoolean) {
                            if(aBoolean) {
                                Intent phoneIntent = new Intent(
                                        "android.intent.action.CALL", Uri.parse("tel:"
                                        + phoneNo));
                                mContext.startActivity(phoneIntent);
                            }
                        }
                    });

                }else {
                    ToastUtil.show("暂时未添加联系电话");
                }
            }
        });
        return viewHold;
    }

    @Override
    public void onBindViewHolder(ViewHold holder, int position) {
        holder.mName.setText(mList.get(position).getContactName());
        holder.mJob.setText(mList.get(position).getContactDept());
        holder.mEmail.setText(mList.get(position).getContactEmail());


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
        @BindView(R.id.img_call)
        ImageView mCall;

        public ViewHold(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
