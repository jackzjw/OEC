package shangchuan.com.oec.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * 日期的类
 * Created by xiaozhu on 2016/8/7.
 */
public class Day {
    /**
     * 单个日期格子的宽
     */
    public int width;
    /**
     * 单个日期格子的高
     */
    public int height;
    /**
     * 日期的文本
     */
    public String text;
    /**
     * 文本字体的颜色
     */
    public int textClor;
    /**
     * 日期背景的类型 0代表无任何背景，1代表正常打卡，2代表所选日期，3代表当前日期 4,代表即是当前日期，也被选中
     */
    public int backgroundStyle;
    /**
     * 字体的大小
     */
    public float textSize;
    /**
     * 背景的半径
     */
    public int backgroundR;
    /**
     * 出勤的类型 0为不画，1为正常考勤，2为异常，3为出差外出灯
     */
    public int workState;
    //迟到
    /**
     * 出勤状态的半径
     */
    public int workStateR = 5;
    /**
     * 字体在第几行
     */
    public int location_x;
    /**
     * 字体在第几列
     */
    public int location_y;

    /**
     * 创建日期对象
     * @param width 每个日期格子的宽度
     * @param height 每个日期格子的高度
     */
    public Day(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * 画天数
     *
     * @param canvas  要画的画布
     * @param paint   画笔
     * @param context 画布的上下文对象
     */
    public void drawDays(Canvas canvas, Context context, Paint paint) {
        //取窄的边框为圆的半径
        backgroundR = width > height ? height : width;
        //画背景
        drawBackground(canvas, paint);

        //画数字
        drawTaxt(canvas, paint);

        //画考勤
        drawWorkState(canvas, paint);


    }

    /**
     * 画考勤
     *
     * @param canvas
     * @param paint
     */
    private void drawWorkState(Canvas canvas, Paint paint) {
        //确定圆心位置
        float cx = location_x * width + width / 2;
        float xy = location_y * height + height * 44 / 60;
        paint.setStyle(Paint.Style.FILL);
        //根据工作状态设置画笔颜色
        if (workState == 0) {
            return;
        }
        //workState 0:迟到不早退，1：正常，2：早退不迟到，3：请假，4：迟到又早退，
        switch (workState) {
            case 0:
                paint.setColor(0xFFE65752);
                break;
            case 2:
                paint.setColor(0xFFFDDD28);
                break;
            case 3:
                paint.setColor(0xff46CEA1);
                break;
        }
        canvas.drawCircle(cx, xy, workStateR, paint);
        if(workState==4){
            float ccx=location_x * width + width / 2-10;
            paint.setColor(Color.parseColor("#E65752"));
            canvas.drawCircle(ccx,xy,workStateR,paint);
            float cccx=location_x * width + width / 2+10;
            paint.setColor(Color.parseColor("#FDDD28"));
            canvas.drawCircle(cccx,xy,workStateR,paint);
        }
    }

    /**
     * 花数字
     *
     * @param canvas
     * @param paint
     */
    private void drawTaxt(Canvas canvas, Paint paint) {
        //根据圆的半径设置字体的大小
        textSize = backgroundR / 3;
        paint.setTextSize(textSize);
        paint.setColor(textClor);
        paint.setStyle(Paint.Style.FILL);
        //计算文字的宽度
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        int w = rect.width();
        //计算画文字的位置
        float x = location_x * width + (width - w) / 2;
        float y = location_y * height + (height + textSize/2) / 2;
        canvas.drawText(text, x, y, paint);
    }

    /**
     * 画背景
     *
     * @param canvas
     * @param paint
     */
    private void drawBackground(Canvas canvas, Paint paint) {
        //画背景 根据背景状态设置画笔类型
        if (backgroundStyle == 0) {
            return;
        }
        //日期背景的类型 0代表无任何背景，1代表正常打卡，2代表所选日期，3代表当前日期 4,代表即是当前日期，也被选中,5代表周末

        switch (backgroundStyle) {
            case 1:
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.parseColor("#00000000"));
                break;
            case 2:
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(0xff40B8D8);
                paint.setStrokeWidth(3);
                break;
            case 3:
                paint.setColor(0xff40B8D8);
                paint.setStyle(Paint.Style.FILL);
                break;
            case 5:
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(0xFFECF1F4);
                break;
        }
        //计算圆心的位置
        float cx = location_x * width + width / 2;
        float cy = location_y * height + height / 2;
        canvas.drawCircle(cx, cy, backgroundR * 9 / 20, paint);

    }


}
