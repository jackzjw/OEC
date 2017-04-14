package shangchuan.com.oec.ui.apply.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luck.picture.lib.model.LocalMediaLoader;
import com.luck.picture.lib.model.PictureConfig;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.SelectOwnerBean;
import shangchuan.com.oec.present.AddApplyPresent;
import shangchuan.com.oec.present.contact.AddApplyContract;
import shangchuan.com.oec.ui.apply.adapter.DelApproverAdapter;
import shangchuan.com.oec.ui.apply.adapter.GridImgAdapter;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.FullyGridLayoutManager;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.ActionSheetDialog;
import shangchuan.com.oec.widget.LoadingView;

public class WorkOutsideActivity extends BaseActivity<AddApplyPresent> implements AddApplyContract.View {

    @BindView(R.id.toolbar_title)
    TextView mToolBarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.outside_type)
    TextView mType;
    @BindView(R.id.outside_destination)
    EditText mDest;
    @BindView(R.id.leave_start)
    TextView mStart;
    @BindView(R.id.leave_end)
    TextView mEnd;
    @BindView(R.id.leave_client)
    EditText mClient;
    @BindView(R.id.outside_conpany)
    EditText mCompany;
    @BindView(R.id.content)
    EditText mContent;
    @BindView(R.id.img_recycleview)
    RecyclerView ImgRec;
    @BindView(R.id.approver_recycleview)
    RecyclerView ApproveRec;
    @BindView(R.id.img_link)
    ImageView mLink;
    @BindView(R.id.rel_add_owner)
    RelativeLayout mAddOwner;

    private GridImgAdapter adapter;
    private List<LocalMedia> selectMedia = new ArrayList<>();
    private List<SelectOwnerBean> ownerList=new ArrayList<>();
    private int[] ownId;
    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_work_outside;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        mToolBarTitle.setText(getResources().getString(R.string.apply_work_outside));
        initToolBar(mToolbar);
        RecyclerView.LayoutManager layoutManager=new FullyGridLayoutManager(this,4, GridLayoutManager.VERTICAL,false);
        ImgRec.setLayoutManager(layoutManager);
        adapter=new GridImgAdapter(this, new GridImgAdapter.onDelPickClickListener() {
            @Override
            public void delClickPick(int position) {
                selectMedia.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        ImgRec.setAdapter(adapter);
        //添加图片
        mLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(WorkOutsideActivity.this).builder().addSheetItem("选择图片", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        openMedia(LocalMediaLoader.TYPE_IMAGE);
                    }
                }).addSheetItem("选择视频", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        openMedia(LocalMediaLoader.TYPE_VIDEO);
                    }
                }).show();
            }
        });
        //添加审批人
        mAddOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(WorkOutsideActivity.this,ApproverActivity.class), Constants.REQUEST_CODE);
            }
        });
    }
    private void openMedia(int MediaType){
        if(selectMedia.size()==9){
            ToastUtil.show("最多选择9张图片或视频");
            return;
        }
        PictureConfig.init(CommonUtil.configParams(MediaType,selectMedia));
        PictureConfig.getPictureConfig().openPhoto(this, new PictureConfig.OnSelectResultCallback() {
            @Override
            public void onSelectSuccess(List<LocalMedia> list) {
                selectMedia=list;
                if(selectMedia!=null){
                    adapter.setData(selectMedia);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        //预览照片
        adapter.setOnItemClickListener(new GridImgAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                // 这里可预览图片
                PictureConfig.getPictureConfig().externalPicturePreview(WorkOutsideActivity.this, position, selectMedia);
            }
        });

    }
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.show(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constants.REQUEST_CODE&&resultCode==RESULT_OK){
            ownerList=(ArrayList<SelectOwnerBean>) data.getSerializableExtra("ownerlist");
            ownId=new int[ownerList.size()];
            for(int i=0;i<ownerList.size();i++){
                ownId[i]=ownerList.get(i).getId();
            }

            ApproveRec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            final DelApproverAdapter delAdapter = new DelApproverAdapter(this, ownerList);
            ApproveRec.setAdapter(delAdapter);
            delAdapter.setOwnerClickListener(new DelApproverAdapter.OnDelOwnerClickListener() {
                @Override
                public void delClick(int position) {
                    ownerList.remove(position);
                    delAdapter.notifyDataSetChanged();
                }
            });
        }
    }
    private String type(){
        return mType.getText().toString();
    }
    private String startTime(){
        return mStart.getText().toString();
    }
    private String endTime(){
        return mEnd.getText().toString();
    }
    private String destnation(){
        return mDest.getText().toString().trim();
    }
    private String clientName(){
        return mClient.getText().toString().trim();
    }
    private String companyName(){
        return mCompany.getText().toString();
    }
    private String content(){
        return mContent.getText().toString();
    }
    @OnClick(R.id.btn_submit)
    void submit(){
       if(TextUtils.isEmpty(type())){
           ToastUtil.show("请选择类型");
           return;
       }
        if(TextUtils.isEmpty(startTime())||TextUtils.isEmpty(endTime())){
            ToastUtil.show("请选择时间段");
            return;
        }
        if(TextUtils.isEmpty(destnation())){
            ToastUtil.show("请选择目的地");
            return;
        }
        if(ownerList.isEmpty()){
            ToastUtil.show("请选择审批人");
            return;
        }
        LoadingView.showProgress(this);
        if(selectMedia.isEmpty()) {
            uploadData("");
        }else{
            mPresent.upLoadFile(selectMedia);

        }



    }
    private void uploadData(String filename){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("ClassId",4);
        hashMap.put("StartTime",startTime());
        hashMap.put("EndTime",endTime());
        hashMap.put("OrderType",type());
        hashMap.put("OrderContent",content());
        hashMap.put("Handlers",ownId);
        hashMap.put("CustomerName",mClient.getText().toString().trim());
        hashMap.put("Destination",destnation());
        hashMap.put("companion",mCompany.getText().toString().trim());
        hashMap.put("ImgList",filename);
        mPresent.submitData(hashMap);
    }
    @Override
    public void AddSuccess() {
        LoadingView.dismissProgress();
        ToastUtil.show("提交成功");
        finish();
    }

    @Override
    public void upLoadFileSuccess(String filename) {
         uploadData(filename);

    }
}
