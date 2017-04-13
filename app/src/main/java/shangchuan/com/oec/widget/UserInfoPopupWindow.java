package shangchuan.com.oec.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.UserInfoBean;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.Glides;

/**
 * Created by sg280 on 2017/4/12.
 */

public class UserInfoPopupWindow extends BasePopupWindow {
   private Context mContext;
    @BindView(R.id.user_avater)
    CircleImageView mUserAvater;
    @BindView(R.id.member_name)
    TextView mName;
    @BindView(R.id.character)
    TextView mDesc;
    @BindView(R.id.gender)
    ImageView mGender;
    @BindView(R.id.member_tel)
    TextView mTel;
    @BindView(R.id.member_email)
    TextView mEmail;
    @BindView(R.id.call_phone)
    ImageView mCall;
    public UserInfoPopupWindow(Context context) {
        super(context);
        this.mContext=context;
    }

    public void setData(final UserInfoBean bean){
        View mview = LayoutInflater.from(mContext).inflate(R.layout.dialog_team_member_info, null);
        ButterKnife.bind(this,mview);
        Glides.getInstance().loadCircle(mContext,bean.getUserAvatar(),mUserAvater);
        mName.setText(bean.getUserTrueName());
        mDesc.setText(bean.getUserTitle());
        mTel.setText(bean.getUserMobile());
        mEmail.setText(bean.getUserEmail());
        mGender.setImageResource(bean.getUserGender()==1?R.drawable.team_icon_male:R.drawable.team_icon_female);
        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.callPhone(mContext,bean.getUserMobile());
            }
        });
        setContentView(mview);

    }
}
