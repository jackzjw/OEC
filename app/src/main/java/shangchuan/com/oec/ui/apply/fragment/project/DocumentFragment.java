package shangchuan.com.oec.ui.apply.fragment.project;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import shangchuan.com.oec.R;
import shangchuan.com.oec.base.SimpleFragment;
import shangchuan.com.oec.ui.apply.activity.ProjectDocumentActivity;
import shangchuan.com.oec.util.ToastUtil;
import shangchuan.com.oec.widget.LoadingView;

/**
 * Created by sg280 on 2017/5/4.
 */

public class DocumentFragment extends SimpleFragment implements View.OnClickListener{

    @BindView(R.id.ll_document)
    LinearLayout mllDoc;
    private String projectId;
    public static DocumentFragment getInstance(String id){
        DocumentFragment fragment=new DocumentFragment();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void showError(String msg) {
        LoadingView.dismissProgress();
        ToastUtil.shortShow(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_document;
    }

    @Override
    protected void initEventAndData() {
        projectId=getArguments().getString("id");
      mllDoc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_document:
                startActivity(ProjectDocumentActivity.getInstance(mActivity,projectId));

                break;
        }
    }
}
