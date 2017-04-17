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
import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.OptionPicker;
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
import shangchuan.com.oec.util.PickerUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.ActionSheetDialog;
import shangchuan.com.oec.widget.LoadingView;

public class ReimburseActivity extends BaseActivity<AddApplyPresent> implements AddApplyContract.View,View.OnClickListener {
    @BindView(R.id.toolbar_title)
    TextView mToolBarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.reimburse_type)
    TextView mType;
    @BindView(R.id.reimburse_client)
    EditText mClient;
    @BindView(R.id.reimburse_project)
    EditText mProject;
    @BindView(R.id.reimburse_time)
    TextView mTime;
    @BindView(R.id.reimburse_sum)
    EditText mSum;
    @BindView(R.id.reimburse_content)
    EditText mContent;
    @BindView(R.id.img_recycleview)
    RecyclerView ImgRec;
    @BindView(R.id.img_link)
    ImageView mLink;
    @BindView(R.id.approver_recycleview)
    RecyclerView ApproveRec;
    @BindView(R.id.rel_add_owner)
    RelativeLayout mAddOwner;
    private GridImgAdapter adapter;
    private List<LocalMedia> selectMedia = new ArrayList<>();
    private List<SelectOwnerBean> ownerList=new ArrayList<>();
    private int[] ownId;

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_reimburse;
    }

    @Override
    protected void initEventData() {
        mToolBarTitle.setText(getResources().getString(R.string.apply_reimburse));
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
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
                new ActionSheetDialog(ReimburseActivity.this).builder().addSheetItem("选择图片", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
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
                startActivityForResult(new Intent(ReimburseActivity.this,ApproverActivity.class), Constants.REQUEST_CODE);
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
                PictureConfig.getPictureConfig().externalPicturePreview(ReimburseActivity.this, position, selectMedia);
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
    private String getType(){
        return mType.getText().toString();
    }
    private String getSum(){
        return mSum.getText().toString();
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
    private void uploadData(String filename){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("ClassId",5);
        hashMap.put("CustomerName",mClient.getText().toString());
        hashMap.put("ProjectName",mProject.getText().toString());
        hashMap.put("OrderTime",mTime.getText().toString());
        hashMap.put("OrderContent",mContent.getText().toString().trim());
        hashMap.put("OrderAmount",mSum.getText().toString().trim());
        for(int id:ownId){
            hashMap.put("Handlers",id);
        }
        hashMap.put("ImgList",filename);
        mPresent.submitData(hashMap);
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
    @OnClick(R.id.btn_submit)
    void submit(){
        if(TextUtils.isEmpty(getType())){
            ToastUtil.show("请填写报销类型");
            return;
        }
        if(TextUtils.isEmpty(getSum())){
            ToastUtil.show("请填写报销金额");
            return;
        }
        if(ownerList.size()==0){
            ToastUtil.show("请添加责任人");
            return;
        }
        LoadingView.showProgress(this);
        if(selectMedia.isEmpty()) {
            uploadData("");
        }else{
            mPresent.upLoadFile(selectMedia);

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reimburse_type:
                PickerUtil.getInstance().setOptionPick(this,new String[]{"加班餐补","业务招待费","市内交通费","差旅费"
                ,"其他"}).setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        mType.setText(item);
                    }
                });
                break;
            case R.id.reimburse_time:
                PickerUtil.getInstance().setDateTimePick(this).setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                    @Override
                    public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                        mTime.setText(year+"-"+month+"-"+day);
                    }
                });
                break;
        }
    }
}
