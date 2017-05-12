package shangchuan.com.oec.ui.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.TrendsListBean;
import shangchuan.com.oec.present.TrendsListPresent;
import shangchuan.com.oec.present.contact.TrendsListContract;
import shangchuan.com.oec.ui.home.adapter.TrendsListAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

public class SearchTrendActivity extends BaseActivity<TrendsListPresent> implements TrendsListContract.View {
    @BindView(R.id.cancle_search)
    TextView mCancleSearch;
    @BindView(R.id.et_search)
    EditText mSearch;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.view_stub)
    ViewStub mViewStub;
    private TrendsListAdapter adapter;
    private boolean isLoadingMore;
    private boolean isFirst;
    private RelativeLayout mRelEmpty;
    private View vs;

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_search_trend;
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
        mSearch.setHint("搜索动态");
        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){

                    if(TextUtils.isEmpty(keyWord())){
                        ToastUtil.shortShow("请输入关键字");
                        return false;
                    }
                    LoadingView.showProgress(SearchTrendActivity.this);
                    mPresent.getTrendList("",keyWord());
                    return  true;
                }
                return false;
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisiblePosition=((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount=recyclerView.getLayoutManager().getItemCount();
                if(lastVisiblePosition>totalItemCount-2&&dy>0){
                    if(!isLoadingMore){
                        isLoadingMore=true;
                        LoadingView.showProgress(SearchTrendActivity.this);
                        mPresent.getMoreTrend("",keyWord());
                    }
                }

            }
        });

    }
   private String keyWord(){
       return mSearch.getText().toString().trim();
   }
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
        isLoadingMore=false;
        LoadingView.dismissProgress();
        ToastUtil.shortShow(msg);
    }

    @Override
    public void showContent(List<TrendsListBean> bean) {
        LoadingView.dismissProgress();
        if(bean.isEmpty()) {
            try {
                vs = mViewStub.inflate();
            } catch (Exception e) {
                vs.setVisibility(View.VISIBLE);
            }
            return;
        }
        if(vs!=null){
            vs.setVisibility(View.GONE);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerDecoration(this));
        adapter=new TrendsListAdapter(this,bean);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showMoreContent(List<TrendsListBean> bean, int start, int end) {
        isLoadingMore=false;
        LoadingView.dismissProgress();
        adapter.updateData(bean);
        adapter.notifyItemRangeInserted(start,end);
    }
}
