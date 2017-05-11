package shangchuan.com.oec.ui.user.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import rx.Subscription;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.SimpleActivity;
import shangchuan.com.oec.model.bean.HttpDataResult;
import shangchuan.com.oec.model.bean.ResultBean;
import shangchuan.com.oec.model.bean.TanentInfoBean;
import shangchuan.com.oec.util.Glides;
import shangchuan.com.oec.util.RxUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.CircleImageView;
import shangchuan.com.oec.widget.CommonSubscriber;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.SaveToken;

public class OrganizationInfoActivity extends SimpleActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar_img)
    ImageView mToolbartRight;
    @BindView(R.id.user_avatar)
    CircleImageView mUserAvater;
    @BindView(R.id.user_report_type)
    TextView mTanentNanme;
    @BindView(R.id.organize_code)
    TextView mCode;
    @BindView(R.id.admin_tel)
    TextView mTel;
    @BindView(R.id.organize_address)
    TextView mAddress;
    @BindView(R.id.organize_tel)
    TextView mOrgTel;
    @BindView(R.id.organize_describe)
    TextView mDesc;
    @BindView(R.id.organize_members)
    TextView mMemberNum;



    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.shortShow(msg);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_organization_info;
    }

    @Override
    protected void initEventAndData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolbarTitle.setText("机构信息");
        mToolbartRight.setImageResource(R.drawable.user_icon_revise);
        initToolBar(mToolbar);
        mToolbartRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.shortShow("请在PC端添加机构");
            }
        });
        LoadingView.showProgress(OrganizationInfoActivity.this);
        Subscription subscription=getActivityComponent().getHttpHelper().getApiSevice().getTanentInfo(SaveToken.mToken)
                .compose(RxUtil.<HttpDataResult<ResultBean<TanentInfoBean>>>scheduleRxHelper())
                .compose(RxUtil.<ResultBean<TanentInfoBean>>handleResult()).subscribe(new CommonSubscriber<ResultBean<TanentInfoBean>>(this) {
            @Override
            public void onNext(ResultBean<TanentInfoBean> bean) {
                TanentInfoBean result=bean.getResult();
                    LoadingView.dismissProgress();
                Glides.getInstance().loadCircle(OrganizationInfoActivity.this,bean.getResult().getTenantLogo(),mUserAvater);
                 mTanentNanme.setText(result.getCompanyName());
                mCode.setText(result.getTenantCode());
                mAddress.setText(result.getTenantAddress());
                mDesc.setText(result.getRemark());
                mMemberNum.setText(result.getMemberCount());
                mTel.setText(result.getAdminMobile());
                mOrgTel.setText(result.getTenantTel());

            }
        });
        add(subscription);
    }


}
