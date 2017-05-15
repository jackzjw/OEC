package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.AttchmentBean;
import shangchuan.com.oec.model.bean.CommentResultBean;
import shangchuan.com.oec.model.bean.TaskCommentBean;
import shangchuan.com.oec.model.bean.TaskDetailsBean;
import shangchuan.com.oec.present.TaskDetailPresent;
import shangchuan.com.oec.present.contact.TaskDetailContract;
import shangchuan.com.oec.ui.apply.adapter.DocumentAdapter;
import shangchuan.com.oec.ui.apply.adapter.ScanImgAdapter;
import shangchuan.com.oec.ui.apply.adapter.TaskCommentAdapter;
import shangchuan.com.oec.util.CommonUtil;
import shangchuan.com.oec.util.FullyGridLayoutManager;
import shangchuan.com.oec.util.LogUtil;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.DividerDecoration;
import shangchuan.com.oec.widget.LoadingView;

public class TaskDetailsActivity extends BaseActivity<TaskDetailPresent> implements TaskDetailContract.View {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.finish_task)
    ImageView mImgSelectFinish;
    @BindView(R.id.nick_name)
    TextView mNickName;
    @BindView(R.id.finish_date)
    TextView mFinishDate;
    @BindView(R.id.task_content)
    TextView mContent;
    @BindView(R.id.task_recycleview)
    RecyclerView mImgRec;
    @BindView(R.id.file_recycleview)
    RecyclerView mFileRec;
    @BindView(R.id.remark_recycleview)
    RecyclerView mRemarkRec;
    @BindView(R.id.input_remark)
    EditText mInputRemark;
    @BindView(R.id.btn_send)
    TextView mSend;
   private String mId;

  public static Intent getInstance(Context context,String id){
      Intent intent=new Intent(context,TaskDetailsActivity.class);
      intent.putExtra("id",id);
      return intent;
  }
    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_project_details;
    }

    @Override
    protected void initEventData() {
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        initToolBar(mToolbar);
        mId=getIntent().getStringExtra("id");
        LogUtil.i("mId="+mId);
        LoadingView.showProgress(this);
        mPresent.getTaskDetails(mId);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showError(String msg) {
           LoadingView.dismissProgress();
           ToastUtil.shortShow(msg);
    }

    @Override
    public void showContent(TaskDetailsBean bean) {
        LoadingView.dismissProgress();
        mToolbarTitle.setText(bean.getTaskTitle());
        if(bean.getTaskStatus().equals("3")){
            //已完成
            mImgSelectFinish.setImageResource(R.drawable.application_button_finish_selected);
            mFinishDate.setTextColor(ContextCompat.getColor(this,R.color.project_gray_text_color));

        }else {
            mImgSelectFinish.setImageResource(R.drawable.application_button_finish_normal);
            mFinishDate.setTextColor(ContextCompat.getColor(this,R.color.apply_wait_approve_text_color));
        }
        mNickName.setText(bean.getUserName());
        mFinishDate.setText(CommonUtil.formatDate(bean.getDeadline()).replace("/","月").concat("日"));
        mContent.setText(bean.getTaskContent());

    }

    @Override
    public void showImgContent(List<AttchmentBean> imgList) {
        if(imgList.isEmpty())return;
        mImgRec.setLayoutManager(new FullyGridLayoutManager(this,4));;
        ScanImgAdapter imgAdapter = new ScanImgAdapter(this, imgList);
         mImgRec.setAdapter(imgAdapter);
       /* imgAdapter.setItemClickListener(new ScanImgAdapter.OnItemClickListener() {
            @Override
            public void imgClick(int position, int mediaType) {

            }
        });*/
    }

    @Override
    public void showFileContent(final List<AttchmentBean> fileList) {
        if(fileList.isEmpty())return;
        mFileRec.setLayoutManager(new LinearLayoutManager(this));
        mFileRec.addItemDecoration(new DividerDecoration(this));
        DocumentAdapter fileAdapter=new DocumentAdapter(this,fileList);
        mFileRec.setAdapter(fileAdapter);
        fileAdapter.setOnItemClickListener(new DocumentAdapter.OnItemClickListener() {
            @Override
            public void ItemClick(int position) {
                LoadingView.showProgress(TaskDetailsActivity.this);
                mPresent.downFile(fileList.get(position).getUrl());
            }
        });

    }

    @Override
    public void showRemark(List<TaskCommentBean> commentBeanList) {
          mRemarkRec.setLayoutManager(new LinearLayoutManager(this));
          mRemarkRec.addItemDecoration(new DividerDecoration(this));
        TaskCommentAdapter adapter=new TaskCommentAdapter(this,commentBeanList);
          mRemarkRec.setAdapter(adapter);
    }

    @Override
    public void remarkSucc(List<CommentResultBean> result) {

    }

    @Override
    public void openFile(String path) {
        LoadingView.dismissProgress();
       startActivity(CommonUtil.openFile(path));
    }
}
