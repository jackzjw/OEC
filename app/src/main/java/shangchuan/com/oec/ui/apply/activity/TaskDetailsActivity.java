package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.BaseActivity;
import shangchuan.com.oec.model.bean.CommentResultBean;
import shangchuan.com.oec.model.bean.TaskCommentBean;
import shangchuan.com.oec.model.bean.TaskDetailsBean;
import shangchuan.com.oec.present.TaskDetailPresent;
import shangchuan.com.oec.present.contact.OnItemClickListener;
import shangchuan.com.oec.present.contact.TaskDetailContract;
import shangchuan.com.oec.ui.apply.adapter.TaskCommentAdapter;
import shangchuan.com.oec.util.CommonUtil;
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
    @BindView(R.id.tishi)
    TextView mTishi;
   private String mId;
   private String PId="0";
    private String toUserId;
    private TaskCommentAdapter adapter;
    private List<TaskCommentBean> commentList;
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
            mTishi.setVisibility(View.GONE);
        }else {
            mImgSelectFinish.setImageResource(R.drawable.application_button_finish_normal);
            mFinishDate.setTextColor(ContextCompat.getColor(this,R.color.apply_wait_approve_text_color));
            mImgSelectFinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                      LoadingView.showProgress(TaskDetailsActivity.this);
                        mPresent.finishTask(mId);
                }
            });
        }
        mNickName.setText(bean.getUserName());
        mFinishDate.setText(CommonUtil.formatDate(bean.getDeadline()).replace("/","月").concat("日"));
        mContent.setText(bean.getTaskContent());

    }




    @Override
    public void showRemark(final List<TaskCommentBean> commentBeanList) {
        this.commentList=commentBeanList;
          mRemarkRec.setLayoutManager(new LinearLayoutManager(this));
          mRemarkRec.addItemDecoration(new DividerDecoration(this));
            adapter=new TaskCommentAdapter(this,commentBeanList);
          mRemarkRec.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
               mInputRemark.setText("");
                PId=commentBeanList.get(position).getPId();
                toUserId=commentBeanList.get(position).getUserID();
            LogUtil.i("pid="+PId+",toUserid="+toUserId);
            }
        });
    }

    @Override
    public void remarkSucc(CommentResultBean result) {
        LoadingView.dismissProgress();
        mInputRemark.setText("");
            TaskCommentBean bean=new TaskCommentBean();
        bean.setUserName(result.getUserName());
        bean.setRemark(result.getRemark());
        bean.setIsComment("1");
        bean.setPId(result.getPId());
        bean.setUserID(result.getToUserId());
        bean.setCreateTime(result.getCreateTime());
        if(PId.equals("0")){
            bean.setProcessInfo(result.getProcessInfo());
        }else {
            bean.setProcessInfo("回复了" + result.getToUserName() + "的评论");
        }
        commentList.add(0,bean);
        adapter.notifyItemInserted(0);


    }

    @Override
    public void finishTaskSucc() {
        LoadingView.dismissProgress();
       mImgSelectFinish.setImageResource(R.drawable.application_button_finish_selected);
        mTishi.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_send)
    void sendMsg(){
      String msg=mInputRemark.getText().toString();
      if(TextUtils.isEmpty(msg)){
          ToastUtil.shortShow("请输入内容");
          return;
      }
      LoadingView.showProgress(this);
      mPresent.sendRemark(mId,PId,toUserId,msg);
  }

}
