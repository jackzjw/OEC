package shangchuan.com.oec.ui.home.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;

public class SearchActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "SearchActivity";
  @BindView(R.id.cancle_search)
    TextView mCancleSearch;
    @BindView(R.id.et_search)
    EditText mSearch;
    @BindView(R.id.ll_search_category)
    LinearLayout ll_container;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.ll_wo)
    LinearLayout mWorkOrder;
    @BindView(R.id.ll_oa)
    LinearLayout mOffice;
    @BindView(R.id.ll_work_report)
    LinearLayout mWorkReport;
    private boolean isFirst;

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initEventData() {
        mCancleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0,0);
            }
        });
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s)&&!isFirst){
                    isFirst=true;
                 ll_container.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_wo:


                break;
            case R.id.ll_oa:

                break;
            case R.id.ll_work_report:


                break;
        }
    }
}
