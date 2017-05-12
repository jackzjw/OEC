package shangchuan.com.oec.ui.apply.fragment.workofficelist;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import shangchuan.com.oec.ui.apply.adapter.WoListAdapter;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.MySpinnerView;

/**
 * Created by sg280 on 2017/3/24.
 */

public abstract class WoListBaseFragment extends BaseFragment<WoListPresent> implements WoListContract.View{


    @BindView(R.id.create_wo)
    TextView mCreateWO;
    @BindView(R.id.spinner1)
    MySpinnerView sParent;
    @BindView(R.id.spinner2)
    MySpinnerView spChild;
    @BindView(R.id.spinner3)
    MySpinnerView spStatus;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    private List<WoClassBean> NameParentList=new ArrayList<>();
    private List<WoClassBean> NameChildList=new ArrayList<>();
    private String parentId;
    private String childId;
    private String statusId;
    protected String mType="0";
    private WoListAdapter contentAdapter;
    private boolean isLoadingMore;
    private List<WoClassBean> statusList=new ArrayList<>();

    @Override
    public void loadData() {
        if(!isVisible||!isPrepared){
            return;
        }
       LoadingView.showProgress(mActivity);
        mPresent.getClassName();
        mPresent.getWoList("","","",mType);
        //注册事件观察者
        mPresent.registerEvent();
         sParent.setSpinnerSelectListener(new MySpinnerView.SpinnerSelectListener() {
             @Override
             public void selected(int position) {
                 parentId=NameParentList.get(position).getId();
                 mPresent.parentToChild(parentId);
                     LoadingView.showProgress(mActivity);
                 mPresent.getWoList(parentId,childId,statusId,mType);
             }
         });
      spChild.setSpinnerSelectListener(new MySpinnerView.SpinnerSelectListener() {
          @Override
          public void selected(int position) {
              childId=NameChildList.get(position).getId();
              LoadingView.showProgress(mActivity);
              mPresent.getWoList(parentId,childId,statusId,mType);

          }
      });
        statusList.add(new WoClassBean("状态",""));
        statusList.add(new WoClassBean("已撤销","0"));
        statusList.add(new WoClassBean("待处理","1"));
        statusList.add(new WoClassBean("处理中","2"));
        statusList.add(new WoClassBean("已完成","3"));
       spStatus.setData(statusList);
        spStatus.setSpinnerSelectListener(new MySpinnerView.SpinnerSelectListener() {
            @Override
            public void selected(int position) {

                statusId=statusList.get(position).getId();
                LoadingView.showProgress(mActivity);
                mPresent.getWoList(parentId,childId,statusId,mType);
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
        WoClassBean classBean=new WoClassBean("父类别","");
        bean.add(0,classBean);
           NameParentList=bean;
           parentId=bean.get(0).getId();
           sParent.setData(bean);


    }

    @Override
    public void showChildName(List<WoClassBean> Bean) {
        WoClassBean classBean=new WoClassBean("子类别","");
        Bean.add(0,classBean);
        NameChildList=Bean;
        childId=Bean.get(0).getId();
        spChild.setData(Bean);
    }

    @Override
    public void showContentList(List<WoListBean> bean) {

           LoadingView.dismissProgress();
        contentAdapter=new WoListAdapter(mActivity,bean);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(contentAdapter);
        mRecyclerView.addItemDecoration(new DividerDecoration(mActivity));
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

    @Override
    public void searchResult(List<WoListBean> bean) {

    }

    @Override
    public void refreshStatus(int position) {
        contentAdapter.notifyItemChanged(position);
    }
}
