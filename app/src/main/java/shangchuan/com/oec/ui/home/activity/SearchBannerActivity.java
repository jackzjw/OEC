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
import shangchuan.com.oec.model.bean.NewsClassifyBean;
import shangchuan.com.oec.model.bean.NewsListBean;
import shangchuan.com.oec.present.NewsListPresent;
import shangchuan.com.oec.present.contact.NewListContract;
import shangchuan.com.oec.ui.home.adapter.NewsListAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

public class SearchBannerActivity extends BaseActivity<NewsListPresent> implements NewListContract.View{
    @BindView(R.id.cancle_search)
    TextView mCancleSearch;
    @BindView(R.id.et_search)
    EditText mSearch;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.view_stub)
    ViewStub mViewStub;

    private NewsListAdapter adapter;
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
        mSearch.setHint("搜索公告");
        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){

                    if(TextUtils.isEmpty(keyWord())){
                        ToastUtil.shortShow("请输入关键字");
                        return false;
                    }
                    LoadingView.showProgress(SearchBannerActivity.this);
                    mPresent.getNewsList("",2,keyWord());
                    return  true;
                }
                return false;
            }
        });
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
    private String keyWord(){
        return mSearch.getText().toString().trim();
    }
    @Override
    public void showContent(List<NewsListBean> bean) {
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
        adapter=new NewsListAdapter(this,bean);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showMoreContent(List<NewsListBean> bean, int start, int end) {
        LoadingView.dismissProgress();
        isLoadingMore=false;
        adapter.updateData(bean);
        adapter.notifyItemRangeInserted(start,end);
    }

    @Override
    public void showNewsClassify(NewsClassifyBean bean) {

    }

    @Override
    public void updateReadStatus(int position) {
        adapter.notifyItemChanged(position);
    }
}
