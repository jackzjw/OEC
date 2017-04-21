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
import cn.qqtheme.framework.picker.FilePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.MySelfInfo;
import shangchuan.com.oec.model.bean.SelectOwnerBean;
import shangchuan.com.oec.present.AddWorkReportPresent;
import shangchuan.com.oec.present.contact.AddApplyContract;
import shangchuan.com.oec.ui.apply.adapter.DelApproverAdapter;
import shangchuan.com.oec.ui.apply.adapter.FileListAdapter;
import shangchuan.com.oec.ui.apply.adapter.GridImgAdapter;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.FullyGridLayoutManager;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.PickerUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

public class CreateWorkReportActivity extends BaseActivity<AddWorkReportPresent> implements AddApplyContract.View,View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_img)
    ImageView mReportList;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.report_type)
    TextView mType;
    @BindView(R.id.report_start)
    TextView mStart;
    @BindView(R.id.report_end)
    TextView mEnd;
    @BindView(R.id.rel_add_owner)
    RelativeLayout mAddOwner;
    @BindView(R.id.report_content)
    EditText mContent;
    @BindView(R.id.img_recycleview)
    RecyclerView ImgRec;
    @BindView(R.id.et_next_plan)
    EditText mNextPlan;
    @BindView(R.id.et_question_recommend)
    EditText mRecomend;
    @BindView(R.id.approver_recycleview)
    RecyclerView ApproveRec;
    @BindView(R.id.img_link)
    ImageView mLink;
    @BindView(R.id.report_title)
    EditText mTitle;
    @BindView(R.id.file_recycleview)
    RecyclerView fileRec;
    private GridImgAdapter adapter;
    private List<LocalMedia> selectMedia = new ArrayList<>();
    private List<SelectOwnerBean> ownerList=new ArrayList<>();
    private int[] ownId;
    private List<String> filePathList=new ArrayList<>();
    private List<String> fileNameList=new ArrayList<>();
    private FileListAdapter fileAdapter;


    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_create_work_report;
    }

    @Override
    protected void initEventData() {
       mToolbarTitle.setText("创建工作报告");
        mReportList.setImageResource(R.drawable.application_icon_record);
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        mReportList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateWorkReportActivity.this,WorkReportListActivity.class));
            }
        });
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
                PickerUtil.getInstance().setOptionPick(CreateWorkReportActivity.this,new String[]{"图片","视频","文件"})
                        .setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                            @Override
                            public void onOptionPicked(int index, String item) {
                                switch (index){
                                    case 0:        openMedia(LocalMediaLoader.TYPE_IMAGE); break;
                                    case 1:        openMedia(LocalMediaLoader.TYPE_VIDEO); break;
                                    case 2:           chooseFile();  break;
                                }
                            }
                        });
            }
        });
        //添加审批人
        mAddOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(CreateWorkReportActivity.this,ApproverActivity.class), Constants.REQUEST_CODE);
            }
        });
        //文件列表
        fileRec.setLayoutManager(new LinearLayoutManager(this));
        fileRec.addItemDecoration(new DividerDecoration(this));
        fileAdapter = new FileListAdapter(this, fileNameList, new FileListAdapter.DelFileClickListener() {
            @Override
            public void delClick(int position) {
                fileNameList.remove(position);
                filePathList.remove(position);
                fileAdapter.notifyDataSetChanged();
            }
        });
        fileRec.setAdapter(fileAdapter);
        mStart.setOnClickListener(this);
        mEnd.setOnClickListener(this);
        mType.setOnClickListener(this);

    }
    private String getType(){
        return mType.getText().toString();
    }
    private String getContent(){
        return mContent.getText().toString();
    }
    private String getStartTime(){
        return mStart.getText().toString();
    }
    private String getEndTime(){
        return mEnd.getText().toString();
    }
    private String gettitle(){
        return mTitle.getText().toString().trim();
    }
    private void chooseFile(){

        PickerUtil.getInstance().setFilePick(this)
                .setOnFilePickListener(new FilePicker.OnFilePickListener() {
                    @Override
                    public void onFilePicked(String currentPath) {
                        String docType=currentPath.substring(currentPath.lastIndexOf(".")+1,currentPath.length());
                        if(docType.equals("doc")||docType.equals("xls")||docType.equals("pdf")){
                            filePathList.add(currentPath);
                            String name=currentPath.substring(currentPath.lastIndexOf("/")+1,currentPath.length());
                            fileNameList.add(name);
                            fileAdapter.notifyDataSetChanged();
                        }else {
                            ToastUtil.show("目前只支持doc、xls、pdf格式");
                            return;
                        }
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
                PictureConfig.getPictureConfig().externalPicturePreview(CreateWorkReportActivity.this, position, selectMedia);
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
    public void AddSuccess() {
        LoadingView.dismissProgress();
        ToastUtil.show("提交成功");
        finish();
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
        if(TextUtils.isEmpty(getStartTime())){
            ToastUtil.show("请开始时间");
            return;
        }
        if(TextUtils.isEmpty(gettitle())){
            ToastUtil.show("请填写标题");
            return;
        }
        if(TextUtils.isEmpty(getContent())){
            ToastUtil.show("请填写工作内容");
            return;
        }
        if(ownerList.size()==0){
            ToastUtil.show("请选择汇报人");
            return;
        }
        LoadingView.showProgress(this);
            mPresent.upLoadFile(selectMedia,filePathList);

    }
    private void uploadData(String filename){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("UserID", MySelfInfo.getInstance().getUserId());
        hashMap.put("ReportType",getType());
        hashMap.put("StartDate",getStartTime());
        hashMap.put("EndDate",getEndTime());
        hashMap.put("ReportTitle",gettitle());
        hashMap.put("ReportContent",getContent());
        hashMap.put("ReportPlan",mNextPlan.getText().toString());
        hashMap.put("ReportQuestion",mRecomend.getText().toString());
        for(int id:ownId){
            hashMap.put("ToUserID",id);
        }
        hashMap.put("ImgList",filename);
        mPresent.submitData(hashMap);
    }
    @Override
    public void upLoadFileSuccess(String filename) {
        LogUtil.i(filename);
      uploadData(filename);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.report_type:
                PickerUtil.getInstance().setOptionPick(this,new String[]{"日报","周报"})
                        .setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                            @Override
                            public void onOptionPicked(int index, String item) {
                                mType.setText(item);
                            }
                        });
                break;
            case R.id.report_start:
                PickerUtil.getInstance().setDateTimePick(this)
                        .setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                            @Override
                            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                                mStart.setText(year+"-"+month+"-"+day);
                            }
                        });
                break;
            case R.id.report_end:
                PickerUtil.getInstance().setDateTimePick(this)
                        .setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
                            @Override
                            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                                mEnd.setText(year+"-"+month+"-"+day);
                            }
                        });
                break;
        }

    }
}
