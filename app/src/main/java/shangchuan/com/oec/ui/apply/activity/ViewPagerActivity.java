package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.AttchmentBean;
import shangchuan.com.oec.util.Glides;

public class ViewPagerActivity extends AppCompatActivity {

    private ArrayList<AttchmentBean> mUrls;
    private Toolbar mToolbar;
    private TextView mTitle;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mUrls=getIntent().getParcelableArrayListExtra("urls");
        pos=getIntent().getIntExtra("pos",0);
        setToolBar();
        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_view_pager);
        viewPager.setCurrentItem(pos);
        viewPager.setAdapter(new ImgAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               mTitle.setText(position+1+"/"+mUrls.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setToolBar() {
        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mTitle=(TextView)findViewById(R.id.toolbar_title);
        mTitle.setText((pos+1)+"/"+mUrls.size());
        mToolbar.setNavigationIcon(R.drawable.home_news_arrow_back);
        setSupportActionBar(mToolbar);
        //设置左上角返回图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置返回图标可点击
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    public static Intent getInstance(Context context, ArrayList<AttchmentBean> list, int position){
        Intent intent=new Intent(context,ViewPagerActivity.class);
        intent.putParcelableArrayListExtra("urls",list);
        intent.putExtra("pos",position);
        return intent;

    }
    class ImgAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView photoView=new PhotoView(container.getContext());
            photoView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glides.getInstance().load(container.getContext(),mUrls.get(position).getUrl(),photoView);
            //Glide.with(container.getContext()).load(mUrls.get(position).getUrl()).asBitmap().into(photoView);
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
          //  super.destroyItem(container, position, object);
        }
    }
}
