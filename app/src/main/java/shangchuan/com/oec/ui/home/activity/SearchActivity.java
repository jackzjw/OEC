package shangchuan.com.oec.ui.home.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.component.RxBus;
import shangchuan.com.oec.model.event.KeyWordEvent;
import shangchuan.com.oec.ui.apply.fragment.SearchOaFragment;
import shangchuan.com.oec.ui.apply.fragment.SearchWoFragment;
import shangchuan.com.oec.ui.team.fragment.SearchUserFragment;
import shangchuan.com.oec.util.ToastUtil;

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
    private List<Fragment> mFragmentList;


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initEventData() {
        mFragmentList=new ArrayList<>();
        mWorkOrder.setOnClickListener(this);
        mOffice.setOnClickListener(this);
        mWorkReport.setOnClickListener(this);
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
                boolean isEmpty=TextUtils.isEmpty(s);
                 ll_container.setVisibility(isEmpty?View.GONE:View.VISIBLE);
                    mViewPager.setVisibility(isEmpty?View.VISIBLE:View.GONE);
                    mTabLayout.setVisibility(isEmpty?View.VISIBLE:View.GONE);
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
        String keyword=mSearch.getText().toString();
        if(TextUtils.isEmpty(keyword)){
            ToastUtil.shortShow("请输入关键字");
            return;
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
        mFragmentList.add(SearchWoFragment.getInstance(keyword));
        mFragmentList.add(SearchOaFragment.getInstance(keyword));
        mFragmentList.add(SearchUserFragment.getInstance(keyword));
        mViewPager.setAdapter(new SearchViewPageAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);

        switch (v.getId()){
            case R.id.ll_wo:
            mViewPager.setCurrentItem(0);
                RxBus.getDefault().post(new KeyWordEvent(keyword));
                break;
            case R.id.ll_oa:
             mViewPager.setCurrentItem(1);
                RxBus.getDefault().post(new KeyWordEvent(keyword));
                break;
            case R.id.ll_work_report:
           mViewPager.setCurrentItem(2);
                RxBus.getDefault().post(new KeyWordEvent(keyword));
                break;
        }
        ll_container.setVisibility(View.GONE);
        mTabLayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
    }
    class SearchViewPageAdapter extends FragmentPagerAdapter {

        public SearchViewPageAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "工单";
                case 1:
                    return "办公";
                case 2:
                    return "团队";
            }
            return super.getPageTitle(position);
        }
    }
}
