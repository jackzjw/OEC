package shangchuan.com.oec.ui.apply.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luck.picture.lib.model.LocalMediaLoader;
import com.luck.picture.lib.model.PictureConfig;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.OptionPicker;
import shangchuan.com.oec.R;
import shangchuan.com.oec.app.Constants;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.SelectOwnerBean;
import shangchuan.com.oec.present.AddWoPresent;
import shangchuan.com.oec.present.contact.AddWoContract;
import shangchuan.com.oec.ui.apply.adapter.DelApproverAdapter;
import shangchuan.com.oec.ui.apply.adapter.GridImgAdapter;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.FullyGridLayoutManager;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.PickerUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.ActionSheetDialog;
import shangchuan.com.oec.widget.LoadingView;

public class CreateWorkOrderActivity extends BaseActivity<AddWoPresent> implements AddWoContract.View {

@BindView(R.id.toolbar_title)
    TextView mToolBarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rb_red)
    RadioButton mrbRed;
    @BindView(R.id.radiogroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.zhu_lei_bie)
    TextView mParentName;
    @BindView(R.id.zi_lei_bie)
    TextView mChildName;
    @BindView(R.id.et_wo_title)
    EditText mTitle;
    @BindView(R.id.et_content)
    EditText mContent;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.rel_add_owner)
    RelativeLayout mAddOwner;
    private String[] parentArray;
    private String[] childArray;
    private int flag;
    private int[] ownerId;
    @BindView(R.id.owner_recycleview)
    RecyclerView mOwnerRec;
    private String[] filesName;
    @BindView(R.id.rel_link)
    RelativeLayout mRelLink;
    private GridImgAdapter adapter;
    private List<LocalMedia> selectMedia = new ArrayList<>();
    private List<SelectOwnerBean> ownerList=new ArrayList<>();

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_create_work_order;
    }

    @Override
    protected void initEventData() {
       mToolBarTitle.setText("创建工单");
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        RecyclerView.LayoutManager layoutManager=new FullyGridLayoutManager(this,4, GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter=new GridImgAdapter(this, new GridImgAdapter.onDelPickClickListener() {
            @Override
            public void delClickPick(int position) {
                selectMedia.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        mRecyclerView.setAdapter(adapter);
      LoadingView.showProgress(this);
        mPresent.getWoClassType();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               switch (checkedId){
                   case R.id.rb_red:
                       flag=1;
                       break;
                   case R.id.rb_blue:
                       flag=2;
                       break;
                   case R.id.rb_green:
                       flag=3;
                       break;
                   case R.id.yellow:
                       flag=4;
                       break;
                   case R.id.purple:
                       flag=5;
                       break;
               }



            }
        });
        mParentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickerUtil.getInstance().setOptionPick(CreateWorkOrderActivity.this,parentArray)
                        .setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                            @Override
                            public void onOptionPicked(int index, String item) {
                                mParentName.setText(item);
                                mPresent.getChildData(item);
                            }
                        });
            }
        });
        mChildName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             PickerUtil.getInstance().setOptionPick(CreateWorkOrderActivity.this,childArray)
                     .setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                         @Override
                         public void onOptionPicked(int index, String item) {
                             mChildName.setText(item);
                         }
                     });
            }
        });
        //添加图片和视频
        mRelLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(CreateWorkOrderActivity.this).builder().addSheetItem("选择图片", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
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
        adapter.setOnItemClickListener(new GridImgAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                // 这里可预览图片
                PictureConfig.getPictureConfig().externalPicturePreview(CreateWorkOrderActivity.this, position, selectMedia);
            }
        });
        //添加审批人
        mAddOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(CreateWorkOrderActivity.this,ApproverActivity.class), Constants.REQUEST_CODE);
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
    public void showChildData(String[] childTitles) {
          childArray=childTitles;
        mChildName.setText(childTitles[0]);
    }

    @Override
    public void showParentData(String[] parentTitles) {
      LoadingView.dismissProgress();
        parentArray=parentTitles;
        mParentName.setText(parentTitles[0]);

    }

    @Override
    public void showSuccess() {
         LoadingView.dismissProgress();
        ToastUtil.show("工单创建成功");
        finish();
    }

    @Override
    public void upLoadSuccess(String[] fileName) {
            this.filesName=fileName;
        LogUtil.i(Arrays.toString(fileName));
        int bid=mPresent.getChildId(mChildName.getText().toString());
        mPresent.submitWo(bid,flag,mTitle.getText().toString(),mContent.getText().toString(),ownerId,filesName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constants.REQUEST_CODE&&resultCode==RESULT_OK){
            ownerList=(ArrayList<SelectOwnerBean>) data.getSerializableExtra("ownerlist");
            //上传图片成功后上传工单接口
            ownerId=new int[ownerList.size()];
            for(int i=0;i<ownerList.size();i++){
                ownerId[i]=ownerList.get(i).getId();
            }
            mOwnerRec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            final DelApproverAdapter delAdapter = new DelApproverAdapter(this, ownerList);
            mOwnerRec.setAdapter(delAdapter);
            delAdapter.setOwnerClickListener(new DelApproverAdapter.OnDelOwnerClickListener() {
                @Override
                public void delClick(int position) {
                    ownerList.remove(position);
                    delAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    //提交工单
    @OnClick(R.id.btn_submit)
    void submit( ){
       if(mTitle.getText().toString().isEmpty()){
           ToastUtil.show("请输入标题");
           return;
       }
        if(ownerList.isEmpty()){
            ToastUtil.show("请选择审批人");
            return;
        }
        LoadingView.showProgress(this);
        if(selectMedia.isEmpty()){
            int bid=mPresent.getChildId(mChildName.getText().toString());
            mPresent.submitWo(bid,flag,mTitle.getText().toString(),mContent.getText().toString(),ownerId,filesName);
        }else {
            mPresent.upLoadFile(selectMedia);
        }

      //  int classBid=mPresent.getChildId(mChildName.getText().toString());
       // LoadingView.Show(this);
      //  mPresent.submitWo(classBid,flag,mTitle.getText().toString(),mContent.getText().toString(),ownerId,files);


    }
}
