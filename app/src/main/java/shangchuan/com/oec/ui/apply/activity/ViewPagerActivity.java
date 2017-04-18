package shangchuan.com.oec.ui.apply.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import shangchuan.com.oec.R;
import shangchuan.com.oec.util.Glides;

public class ViewPagerActivity extends AppCompatActivity {

    private ArrayList<String> mUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mUrls=(ArrayList<String>)getIntent().getSerializableExtra("urls");
        int pos=getIntent().getIntExtra("pos",0);
        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_view_pager);
        viewPager.setCurrentItem(pos);
        viewPager.setAdapter(new ImgAdapter());

    }
    public static Intent getInstance(Context context, ArrayList<String> list, int position){
        Intent intent=new Intent(context,ViewPagerActivity.class);
        intent.putExtra("urls",list);
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
            Glides.getInstance().load(container.getContext(),mUrls.get(position),photoView);
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
          //  super.destroyItem(container, position, object);
        }
    }
}
