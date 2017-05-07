package shangchuan.com.oec.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import java.util.List;

import shangchuan.com.oec.R;
import shangchuan.com.oec.model.bean.RectDeptBean;
import shangchuan.com.oec.util.DensityUtil;
import shangchuan.com.oec.util.LogUtil;

/**
 * Created by sg280 on 2017/5/6.
 */

public class OrganizationView extends View {
    private final int text_color;
    private final int bg_color;
    private final float rect_height;
    private final float top;
    private final float mTextSize;
    private final Scroller scroller;
    private Paint bgPaint;
    private Paint textPaint;
    private float left;
    private float lineOffset;
    private List<RectDeptBean> mData;
    private Paint linePaint;
    private int startY;
    private boolean mScrollToTop;
    private boolean mScrollToBottom;


    public OrganizationView(Context context) {
        this(context,null,0);
    }

    public OrganizationView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public void setData(List<RectDeptBean> data){
        this.mData=data;
         invalidate();
    }

    public OrganizationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.OrganizationView);
        text_color= a.getColor(R.styleable.OrganizationView_zjw_text_color,0xff2E3A4C);
        bg_color=a.getColor(R.styleable.OrganizationView_zjw_bg_color,0xffF5F7FA);
        rect_height=a.getDimension(R.styleable.OrganizationView_zjw_rect_height,120);
        top=a.getDimension(R.styleable.OrganizationView_zjw_rect_magin,20);
        left=a.getDimension(R.styleable.OrganizationView_zjw_rect_margin_left,80);
        lineOffset=top;
        a.recycle();
        mTextSize= DensityUtil.px2sp(context,15);
        initPaint();
        scroller=new Scroller(context);


    }

    private void initPaint() {
        bgPaint=new Paint();
        bgPaint.setColor(bg_color);
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.FILL);
        textPaint=new TextPaint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(text_color);
        textPaint.setTextSize(30);
        textPaint.setAntiAlias(true);
        linePaint=new Paint();
        linePaint.setStrokeWidth(3);
        linePaint.setColor(bg_color);
        linePaint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRect(canvas);

    }


   @Override
    public boolean onTouchEvent(MotionEvent event) {
      //  int  startX=(int)event.getX();
           if(mData==null) return false;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY=(int)event.getY();

                break;
            case MotionEvent.ACTION_MOVE:
            //    int offsetX=(int)event.getX()-startX;
                int offsetY=(int)event.getY()-startY;
                LogUtil.i("offsetY="+offsetY);
                LogUtil.i("scrollY="+getScrollY());
                if(getScrollY()<=0&&offsetY>0){
                    LogUtil.i("top");
                    mScrollToTop=true;
                    mScrollToBottom=false;
                }else if(getScrollY()+getMeasuredHeight()>=mData.size()*(rect_height+top)&&offsetY<0){
                   LogUtil.i("bottom");
                    mScrollToTop=false;
                    mScrollToBottom=true;
                }else {
                    LogUtil.i("middle");
                    mScrollToBottom=false;
                    mScrollToTop=false;
                }
                if(!mScrollToTop&&!mScrollToBottom) {
                    scroller.startScroll(0, getScrollY(), 0, -offsetY);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();
        }
    }

    private void drawRect(Canvas canvas) {
        float width=getMeasuredWidth();
        float paddingLeft=(float)getPaddingLeft();
        if(mData!=null){
            for(int i=0;i<mData.size();i++){
                RectDeptBean item=mData.get(i);
                String deptName=item.getDeptName();
                Rect textRect=new Rect();
                textPaint.getTextBounds(deptName,0,deptName.length(),textRect);
                int textWidth=textRect.width();
                int textHeight=textRect.height();
                int num=item.getNum();
                if(item.getFloor()==1){
                    canvas.drawRect(paddingLeft,top,width-getPaddingRight(),top+rect_height,bgPaint);
                    canvas.drawText(deptName,paddingLeft+(width-textWidth)/2,top+(rect_height+textHeight)/2,textPaint);
                    canvas.drawLine(paddingLeft+lineOffset,rect_height+top,paddingLeft+lineOffset,mData.size()*(top+rect_height)-(rect_height/2),linePaint);
                }else if(item.getFloor()==2){
                      float marginTop=i*(top+rect_height)+top;
                     canvas.drawRect(paddingLeft+left,marginTop,width-getPaddingRight(),marginTop+rect_height,bgPaint);
                    canvas.drawText(deptName,(width-textWidth+paddingLeft+left)/2,marginTop+(rect_height+textHeight)/2,textPaint);
                    canvas.drawLine(paddingLeft+lineOffset,marginTop+rect_height/2,paddingLeft+lineOffset+left,marginTop+rect_height/2,linePaint);
                    canvas.drawLine(paddingLeft+lineOffset+left,(i+1)*(top+rect_height),paddingLeft+lineOffset+left,(i+1+num)*(top+rect_height)-rect_height/2,linePaint);
                }else if(item.getFloor()==3){
                    float childTop=i*(top+rect_height)+top;
                    canvas.drawRect(paddingLeft+2*left,childTop,width-getPaddingRight(),childTop+rect_height,bgPaint);
                    canvas.drawText(deptName,(width-textWidth+paddingLeft+2*left)/2,(i+1)*(top+rect_height)-(rect_height-textHeight)/2,textPaint);
                    canvas.drawLine(paddingLeft+lineOffset+left,(i+1)*(top+rect_height)-rect_height/2,paddingLeft+lineOffset+2*left,(i+1)*(top+rect_height)-rect_height/2,linePaint);

                }





            }
        }
    }


}
