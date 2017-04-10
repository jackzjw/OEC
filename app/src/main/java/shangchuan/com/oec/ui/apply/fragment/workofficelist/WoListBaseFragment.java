package shangchuan.com.oec.ui.apply.fragment.workofficelist;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseFragment;
import shangchuan.com.oec.model.bean.WoClassBean;
import shangchuan.com.oec.model.bean.WoListBean;
import shangchuan.com.oec.present.WoListPresent;
import shangchuan.com.oec.present.contact.WoListContract;
import shangchuan.com.oec.ui.apply.activity.CreateWorkOrderActivity;
import shangchuan.com.oec.ui.apply.adapter.WoClassAdapter;
import shangchuan.com.oec.ui.apply.adapter.WoListAdapter;
import shangchuan.com.oec.util.DensityUtil;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

/**
 * Created by sg280 on 2017/3/24.
 */

public abstract class WoListBaseFragment extends BaseFragment<WoListPresent> implements WoListContract.View{


    @BindView(R.id.create_wo)
    TextView mCreateWO;
    @BindView(R.id.spinner1)
    Spinner sParent;
    @BindView(R.id.spinner2)
    Spinner spChild;
    @BindView(R.id.spinner3)
    Spinner spStatus;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    private List<WoClassBean> NameParentList=new ArrayList<>();
    private List<WoClassBean> NameChildList=new ArrayList<>();
    private int parentId;
    private int childId;
    private int statusId;
    protected int mType=0;
    private WoClassAdapter parentAdapter;
    private WoClassAdapter childAdapter;
    private String[] statusArrays={"已撤销","待处理","处理中","已完成"};
    private boolean isFirst=true;
    private WoListAdapter contentAdapter;
    private boolean isLoadingMore;


    @Override
    public void loadData() {
        LoadingView.showProgress(mActivity);
        mPresent.getClassName();
        parentAdapter=new WoClassAdapter(mActivity,NameParentList);
        sParent.setAdapter(parentAdapter);
        childAdapter = new WoClassAdapter(mActivity, NameChildList);
        spChild.setAdapter(childAdapter);
        sParent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parentId=NameParentList.get(position).getId();
                mPresent.parentToChild(parentId);
                LogUtil.i("父类");
              //  LoadingView.Show(mActivity);
               // LoadingView.showProgress(mActivity);
                mPresent.getWoList(parentId,childId,statusId,mType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spChild.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                childId=NameChildList.get(position).getId();
                  //   LoadingView.Show(mActivity);
                LogUtil.i("子类");

            //    LoadingView.showProgress(mActivity);
                mPresent.getWoList(parentId,childId,statusId,mType);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(mActivity,R.layout.item_wo_list_class_type, statusArrays);
         spStatus.setAdapter(statusAdapter);
         spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            statusId=position;
            LogUtil.i("状态类");
      //   LoadingView.showProgress(mActivity);
           mPresent.getWoList(parentId,childId,statusId,mType);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
        mCreateWO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, CreateWorkOrderActivity.class));
            }
        });
    }

    @Override
    public int getResourcesLayout() {
        return R.layout.fragment_work_office_list;
    }

    @Override
    protected void initInject() {
       getFragmentComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.show(msg);
    }


    @Override
    public void showParentName(List<WoClassBean> bean) {
        if(bean.isEmpty()) return;
           parentId=bean.get(0).getId();
           parentAdapter.updateData(bean);


    }

    @Override
    public void showChildName(List<WoClassBean> Bean) {
        if(Bean.isEmpty()) return;
        childId=Bean.get(0).getId();
        childAdapter.updateData(Bean);
        if(isFirst){
            LogUtil.i("第一次");
            isFirst=false;
            mPresent.getWoList(parentId,childId,statusId,mType);
        }

    }

    @Override
    public void showContentList(List<WoListBean> bean) {

           LoadingView.dismissProgress();
        contentAdapter=new WoListAdapter(mActivity,bean);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(contentAdapter);
        mRecyclerView.addItemDecoration(new DividerDecoration(
                ContextCompat.getColor(mActivity,R.color.theme_divide_color), DensityUtil.dp2px(mActivity,1f)));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisiblePosition=((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount=recyclerView.getLayoutManager().getItemCount();
                if(lastVisiblePosition>totalItemCount-2&&dy>0){
                    if(!isLoadingMore){
                        isLoadingMore=true;
                    //    LoadingView.showProgress(mActivity);
                        mPresent.getMoreWoList(parentId,childId,statusId,mType);
                    }
                }

            }
        });
    }

    @Override
    public void showMoreContent(List<WoListBean> bean,int start,int end) {
        LoadingView.dismissProgress();
        isLoadingMore=false;
           contentAdapter.updateData(bean);
        contentAdapter.notifyItemRangeInserted(start,end);

    }


}
