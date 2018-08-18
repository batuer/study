package com.gusi.study.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.gusi.study.utils.ColorUtils;

/**
 * @Author ylw  2018-02-08 10:58
 */
public class CanvasView extends View {

  private final TextPaint mPaint;

  public CanvasView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setColor(Color.parseColor("#FF605C5A"));
    mPaint.setStrokeWidth(2);
  }

  //@Override public void draw(Canvas canvas) {
  //  super.draw(canvas);
  //  //canvas.translate(getWidth() / 2, getHeight() / 2);
  //  //canvas.drawLine(-getWidth() / 2, 0, getWidth() / 2, 0, mPaint);
  //  //canvas.drawLine(0, -getHeight() / 2, 0, getHeight() / 2, mPaint);
  //  //
  //  ////canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mPaint);
  //  ////canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mPaint);
  //  //mPaint.setTextSize(SizeUtils.sp2px(20));
  //  //
  //  //float txtWidth = mPaint.measureText("Yuliweng");
  //  //
  //  ////Paint.FontMetricsInt fontMetricsInt = new Paint.FontMetricsInt();
  //  ////Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
  //  ////float ascent = fontMetrics.ascent;
  //  ////float descent = fontMetrics.descent;
  //  ////Log.w("Fire", "CanvasView:40行:" + ascent+":-:"+descent);
  //  //Log.w("Fire",
  //  //    "CanvasView:42行:" + mPaint.ascent() + ":-:" + mPaint.descent() + ":-:" + txtWidth);
  //  //float baseLineY = Math.abs(mPaint.ascent() + mPaint.descent()) / 2;
  //  ////canvas.drawText("Yuliweng", -txtWidth / 2, baseLineY, mPaint);
  //  ////canvas.drawText("Yuliweng", 0, -getHeight() / 2, mPaint);
  //  //
  //  //StaticLayout layout =
  //  //    new StaticLayout("Yuliweng", mPaint, (int) txtWidth / 2, Layout.Alignment.ALIGN_CENTER,
  //  //        0.8f, 0f, false);
  //  //canvas.save();
  //  //canvas.translate(-layout.getWidth() / 2, -layout.getHeight() / 2);
  //  //layout.draw(canvas);
  //  //canvas.restore();
  //}
  int i = 0;

  @Override public void draw(Canvas canvas) {
    int width = getWidth();
    int height = getHeight();
    //canvas
    Path path = new Path();
    float x = (float) width / 2;
    float y = (float) height / 2;
    path.addCircle(x, y, Math.min(x, y) - (i * 10), Path.Direction.CCW);
    //canvas.save();
    canvas.clipPath(path);
    super.draw(canvas);
    //canvas.restore();

    i++;

    postDelayed(new Runnable() {
      @Override public void run() {
        int randomColor = ColorUtils.getRandomColor();
        ColorDrawable background = (ColorDrawable) getBackground();
        background.setColor(randomColor);
        invalidate();
      }
    }, 500);
  }
}
