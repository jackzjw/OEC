package shangchuan.com.oec.ui.apply.activity;

import android.support.v7.widget.GridLayoutManager;
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
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.present.AddWoPresent;
import shangchuan.com.oec.present.contact.AddWoContract;
import shangchuan.com.oec.ui.apply.adapter.GridImgAdapter;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.FullyGridLayoutManager;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.ActionSheetDialog;
import shangchuan.com.oec.widget.LoadingView;
import shangchuan.com.oec.widget.WheelDialogFragment;

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
    @BindView(R.id.tv_wo_owner)
    TextView mOwner;
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    private String[] parentArray;
    private String[] childArray;
    private int flag;
    private int[] ownerId;
    private String[] files;
    private LoadingView loadingview;
    @BindView(R.id.rel_link)
    RelativeLayout mRelLink;
    private GridImgAdapter adapter;
    private List<LocalMedia> selectMedia = new ArrayList<>();

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
       loadingview= new LoadingView(this);
        loadingview.show();
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
              final  WheelDialogFragment weel = WheelDialogFragment.newInstance(parentArray);
            weel.setWheelDialogListener(new WheelDialogFragment.OnWheelDialogListener() {
                @Override
                public void onClickRight(String value) {
                    LogUtil.i("value="+value);
                        mParentName.setText(value);
                       mPresent.getChildData(value);
                      weel.dismiss();
                }
            });
                weel.show(getSupportFragmentManager(),"");
            }
        });
        mChildName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  WheelDialogFragment weel = WheelDialogFragment.newInstance(childArray);
                weel.setWheelDialogListener(new WheelDialogFragment.OnWheelDialogListener() {

                    @Override
                    public void onClickRight(String value) {
                        LogUtil.i("value="+value);
                        mChildName.setText(value);
                        weel.dismiss();
                    }
                });
                weel.show(getSupportFragmentManager(),"");
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
                if(list.get(0).isCompressed()){
                    LogUtil.i(list.get(0).getCompressPath());
                }else {
                    LogUtil.i(list.get(0).getPath());
                }

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
         LoadingView.Dismiss();

        ToastUtil.show(msg);
    }

    @Override
    public void showChildData(String[] childTitles) {
          childArray=childTitles;
        mChildName.setText(childTitles[0]);
    }

    @Override
    public void showParentData(String[] parentTitles) {
      loadingview.dismiss();
        parentArray=parentTitles;
        mParentName.setText(parentTitles[0]);

    }

    @Override
    public void showSuccess() {
         LoadingView.Dismiss();
    }
    //提交工单
    @OnClick(R.id.btn_submit)
    void submit( ){
       if(mTitle.getText().toString().isEmpty()){
           ToastUtil.show("请输入标题");
           return;
       }
        if(mOwner.getText().toString().isEmpty()){
            ToastUtil.show("请选择审批人");
            return;
        }
        int classBid=mPresent.getChildId(mChildName.getText().toString());
        LoadingView.Show(this);
        mPresent.submitWo(classBid,flag,mTitle.getText().toString(),mContent.getText().toString(),ownerId,files);


    }
}
