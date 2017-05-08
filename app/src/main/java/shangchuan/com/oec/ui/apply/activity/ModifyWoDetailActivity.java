package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yalantis.ucrop.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.SimpleActivity;
import shangchuan.com.oec.model.bean.SelectOwnerBean;
import shangchuan.com.oec.model.bean.WoDetailBean;
import shangchuan.com.oec.ui.apply.adapter.FileListAdapter;
import shangchuan.com.oec.ui.apply.adapter.GridImgAdapter;

public class ModifyWoDetailActivity extends SimpleActivity {
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
    @BindView(R.id.file_recycleview)
    RecyclerView fileRec;
    private String[] parentArray;
    private String[] childArray;
    private int flag;
    private int[] ownerId;
    @BindView(R.id.owner_recycleview)
    RecyclerView mOwnerRec;
    @BindView(R.id.rel_link)
    ImageView mRelLink;
    private GridImgAdapter adapter;
    private List<LocalMedia> selectMedia = new ArrayList<>();
    private List<SelectOwnerBean> ownerList=new ArrayList<>();
    private List<String> filePathList=new ArrayList<>();
    private List<String> fileNameList=new ArrayList<>();
    private FileListAdapter fileAdapter;
    private WoDetailBean mData;

    public static Intent getInstance(Context context, WoDetailBean bean){
        Intent intent=new Intent(context,ModifyWoDetailActivity.class);
        intent.putExtra("bean",bean);
        return intent;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_create_work_order;
    }

    @Override
    protected void initEventAndData() {
        mData=(WoDetailBean)getIntent().getSerializableExtra("bean");
        if(mData==null) return;
        mParentName.setText(mData.getClassNameA());
        mChildName.setText(mData.getClassNameB());
        mTitle.setText(mData.getOrderTitle());
        mRadioGroup.check(mRadioGroup.getChildAt(mData.getOrderFlag()-1).getId());
        mContent.setText(mData.getOrderContent());


    }



    @Override
    public void showError(String msg) {

    }
}
