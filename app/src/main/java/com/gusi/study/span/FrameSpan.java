package com.gusi.study.span;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.style.ReplacementSpan;

/**
 * @Author ylw  2017-12-07 16:52
 */
public class FrameSpan extends ReplacementSpan {
  private final Paint mPaint;
  private int mWidth;

  public FrameSpan() {
    mPaint = new Paint();
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setColor(Color.BLUE);
    mPaint.setAntiAlias(true);
  }

  @Override
  public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
    mWidth = (int) paint.measureText(text, start, end);
    return mWidth;
  }

  @Override
  public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
    canvas.drawRect(x, top, x + mWidth, bottom, mPaint);
    canvas.drawText(text, start, end, x, y, paint);
  }
}
