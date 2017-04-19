package shangchuan.com.oec.util;

import android.content.Context;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import shangchuan.com.oec.R;


/**
 * @author: 蜡笔小新
 * @date: 2016-06-14 16:02
 * @GitHub: https://github.com/meikoz
 */
public class Glides {

    public static Glides instance = new Glides();

    public Glides() {
    }

    public static Glides getInstance() {
        return instance;
    }

   /* // 加载网络图片
    public void load(Context context, String url, final ImageView imageView) {
        Glide.with(context)
                .load(url).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (imageView == null) {
                    return false;
                }
                if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                float scale = (float) vw / (float) resource.getIntrinsicWidth();
                int vh = Math.round(resource.getIntrinsicHeight() * scale);
                params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                imageView.setLayoutParams(params);
                return false;

            }
        })
                .placeholder(R.drawable.user_img_avatar)
                .error(R.color.colorPrimary)
                .crossFade()
                .into(imageView);
    }*/



    // 加载圆型网络图片
    public void loadCircle(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.user_img_avatar01)
                .error(R.drawable.user_img_avatar01)
                .into(imageView);
    }



    // 加载本地图片
    public void load(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.user_img_avatar01)
                .error(R.drawable.user_img_avatar01)
              //  .crossFade()
                .into(imageView);
    }

    // 加载圆型本地图片
    public void loadCircle(Context context, int resId, ImageView imageView) {
      /*  Glide.with(context)
                .load(resId)
                .placeholder(R.color.abc_tab_text_normal)
                .error(R.color.abc_tab_text_normal)
                .transform(new GlideCircleTransform(context))
                .crossFade()
                .into(imageView);*/
    }
    // 加载网络图片动画
    public void loadAnima(Context context, String url, Animation animation, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.user_img_avatar)
                .error(R.color.colorPrimary)
                .animate(animation)
                .crossFade()
                .into(imageView);
    }

    // 加载网络图片动画
    public void loadAnima(Context context, String url, int animationId, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.user_img_avatar)
                .error(R.color.colorPrimary)
                .animate(animationId)
                .crossFade()
                .into(imageView);
    }

    // 加载本地图片动画
    public void loadAnima(Context context, int resId, Animation animation, ImageView imageView) {
        Glide.with(context)
                .load(resId)
                .placeholder(R.drawable.user_img_avatar)
                .error(R.color.colorPrimary)
                .animate(animation)
                .crossFade()
                .into(imageView);
    }


    // 加载drawable图片
    public void loadAnima(Context context, int resId, int animationId, ImageView imageView) {
        Glide.with(context)
                .load(resId)
                .placeholder(R.drawable.user_img_avatar)
                .error(R.color.colorPrimary)
                .animate(animationId)
                .crossFade()
                .into(imageView);
    }
}
