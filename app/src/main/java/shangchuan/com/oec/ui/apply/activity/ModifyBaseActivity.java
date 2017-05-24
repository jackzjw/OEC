package shangchuan.com.oec.ui.apply.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.luck.picture.lib.model.LocalMediaLoader;
import com.luck.picture.lib.model.PictureConfig;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.qqtheme.framework.picker.FilePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.AttchmentBean;
import shangchuan.com.oec.model.bean.OaDetailsBean;
import shangchuan.com.oec.model.bean.SelectOwnerBean;
import shangchuan.com.oec.present.AddApplyPresent;
import shangchuan.com.oec.present.contact.AddApplyContract;
import shangchuan.com.oec.ui.apply.adapter.DelApproverAdapter;
import shangchuan.com.oec.ui.apply.adapter.FileListAdapter;
import shangchuan.com.oec.ui.apply.adapter.GridImgAdapter;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.FullyGridLayoutManager;
import shangchuan.com.oec.util.PickerUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

public abstract class ModifyBaseActivity extends BaseActivity<AddApplyPresent> implements AddApplyContract.View {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img_link)
    ImageView mLink;
    @BindView(R.id.rel_add_owner)
    RelativeLayout mAddOwner;
    @BindView(R.id.img_recycleview)
    RecyclerView ImgRec;
    @BindView(R.id.approver_recycleview)
    RecyclerView ApproveRec;
    @BindView(R.id.file_recycleview)
    RecyclerView fileRec;
    private GridImgAdapter adapter;
    protected List<LocalMedia> selectMedia = new ArrayList<>();
    protected List<SelectOwnerBean> ownerList=new ArrayList<>();
    protected int[] ownId;
    protected List<String> filePathList=new ArrayList<>();
    protected List<String> fileNameList=new ArrayList<>();
    private FileListAdapter fileAdapter;
    protected OaDetailsBean data;
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        //图片列表
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
        for(AttchmentBean item:data.getAttachmentList()){
            if(item.getAttType().equals("jpg")){
                LocalMedia media=new LocalMedia();
                media.setCompressPath(item.getUrl());
                media.setPath(item.getUrl());
                media.setType(1);
                selectMedia.add(media);

            }else if(item.getAttType().equals("mp4")){
                LocalMedia media=new LocalMedia();
                media.setPath(item.getUrl());
                media.setCompressPath(item.getUrl());
                media.setType(2);
                selectMedia.add(media);
            }
            else if(item.getAttType().equals("doc")||item.getAttType().equals("xls")||item.getAttType().equals("pdf")){
                fileNameList.add(item.getOldAttFileName());
              //  filePathList.add(item.getOldAttFileName());

            }
        }
        adapter.setData(selectMedia);

        //添加图片
        mLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickerUtil.getInstance().setOptionPick(ModifyBaseActivity.this,new String[]{"图片","视频","文件"})
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
                startActivityForResult(new Intent(ModifyBaseActivity.this,ApproverActivity.class), Constants.REQUEST_CODE);
            }
        });
        //文件列表
        fileRec.setLayoutManager(new LinearLayoutManager(this));
        fileRec.addItemDecoration(new DividerDecoration(this));
        fileAdapter = new FileListAdapter(this, fileNameList, new FileListAdapter.DelFileClickListener() {
            @Override
            public void delClick(int position) {
                fileNameList.remove(position);
              //  filePathList.remove(position);
                fileAdapter.notifyDataSetChanged();
            }
        });
        fileRec.setAdapter(fileAdapter);
    }
    private void chooseFile(){

        PickerUtil.getInstance().setFilePick(this)
                .setOnFilePickListener(new FilePicker.OnFilePickListener() {
                    @Override
                    public void onFilePicked(String currentPath) {
                        String docType=currentPath.substring(currentPath.lastIndexOf(".")+1,currentPath.length());
                        if(docType.equals("doc")||docType.equals("xls")||docType.equals("pdf")){
                         //   filePathList.add(currentPath);
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
    private void openMedia(int MediaType) {
        if (selectMedia.size() == 9) {
            ToastUtil.show("最多选择9张图片或视频");
            return;
        }
        PictureConfig.init(CommonUtil.configParams(MediaType, selectMedia));
        PictureConfig.getPictureConfig().openPhoto(this, new PictureConfig.OnSelectResultCallback() {
            @Override
            public void onSelectSuccess(List<LocalMedia> list) {
                selectMedia = list;
                if (selectMedia != null) {
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
                PictureConfig.getPictureConfig().externalPicturePreview(ModifyBaseActivity.this, position, selectMedia);
            }
        });
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
        startActivity(new Intent(this,ApplyListActivity.class));
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
}
