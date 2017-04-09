package shangchuan.com.oec.ui.home.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;

public class SearchActivity extends BaseActivity {
    private static final String TAG = "SearchActivity";
  @BindView(R.id.cancle_search)
    TextView mCancleSearch;
    @BindView(R.id.et_search)
    EditText mSearch;
    @BindView(R.id.activity_search)
    LinearLayout ll_container;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private View contentView;
    private boolean isFirst;

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initEventData() {
        contentView= LayoutInflater.from(this).inflate(R.layout.item_search_activity,ll_container,false);
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
                Log.e(TAG,"beforeTextChanged="+s);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e(TAG,"onTextChanged="+s+start+before+count);
                if(!TextUtils.isEmpty(s)&&!isFirst){
                    isFirst=true;
                    ll_container.addView(contentView);
                }
                if(TextUtils.isEmpty(s)){
                    ll_container.removeView(contentView);
                    isFirst=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e(TAG,"afterTextChange="+s);

            }
        });
    }

    @Override
    protected void initInject() {

    }

    @Override
    public void showError(String msg) {

    }
}
