package com.gusi.study.floating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by batue on 2017/10/8.
 */

public class FloatView extends View {
  //悬浮球宽度
  private int floatWidth = 150;
  //悬浮球高度
  private int floatHeight = 150;
  //悬浮球画笔
  private Paint mPaint;
  //绘制文字画笔
  private Paint mTextPaint;
  private String text = "50%";

  public FloatView(Context context) {
    super(context);
    initPaint();
  }

  public int getFloatWidth() {
    return floatWidth;
  }

  public int getFloatHeight() {
    return floatHeight;
  }

  public FloatView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initPaint();
  }

  public FloatView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initPaint();
  }

  /**
   * @description 初始化画笔
   * @author ldm
   * @time 2016/8/17 11:37
   */
  private void initPaint() {
    //设置悬浮球画笔
    mPaint = new Paint();
    mPaint.setColor(Color.GREEN);
    mPaint.setAntiAlias(true);
    mPaint.setDither(true);
    //设置文字画笔
    mTextPaint = new Paint();
    mTextPaint.setTextSize(25);
    mPaint.setAntiAlias(true);
    mTextPaint.setColor(Color.WHITE);
    mTextPaint.setFakeBoldText(true);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //设置大小
    setMeasuredDimension(floatWidth, floatHeight);
  }

  /**
   * @description 绘制图案
   * @author ldm
   * @time 2016/8/17 11:44
   */
  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //绘制悬浮球
    canvas.drawCircle(floatWidth / 2, floatHeight / 2, floatWidth / 2, mPaint);
    //绘制文字
    Paint.FontMetrics metrics = mTextPaint.getFontMetrics();
    //文字大小计算可以参考：http://mikewang.blog.51cto.com/3826268/871765/
    float textWidth = mTextPaint.measureText(text);
    float x = floatWidth / 2 - textWidth / 2;
    float dy = -(metrics.descent + metrics.ascent) / 2;
    float y = floatHeight / 2 + dy;
    canvas.drawText(text, x, y, mTextPaint);
  }
}
