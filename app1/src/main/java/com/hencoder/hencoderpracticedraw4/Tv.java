package com.hencoder.hencoderpracticedraw4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by batue on 2017/10/25.
 */

public class Tv extends TextView {
  TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
  TextPaint paint1 = new TextPaint(Paint.ANTI_ALIAS_FLAG);

  private boolean isChange = false;
  private float mProgress;

  private float lastPositionOffset;

  public Tv(Context context) {
    super(context);
  }

  public Tv(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  {
    paint.setColor(Color.BLACK);
    paint.setTextSize(60);
    paint1.setColor(Color.RED);
    paint1.setTextSize(60);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //if (isChange) {
    //  int width = getWidth();
    //  canvas.save(); //在 裁剪出去的画布上绘制
    //  canvas.clipRect(width * mProgress / 100, 0, width, getHeight());
    //  super.onDraw(canvas);
    //  canvas.restore();
    //} else {
    //  super.onDraw(canvas);
    //}

    //canvas.drawColor(Color.parseColor("#55000000"));
    //
    //String text = getText().toString();
    //
    //Rect rect = new Rect();
    //paint.getTextBounds(text, 0, text.length(), rect);
    //
    //int width = rect.width();
    //int x = (getWidth() - width) / 2;
    //int height = rect.height();
    //int y = (getHeight() - height) / 2;
    //canvas.drawColor(Color.WHITE);
    //canvas.drawText(text, x, y, paint);
    //
    //canvas.save();
    //canvas.clipRect(0, 0, getWidth() * mProgress / 100, getHeight());
    ////canvas.drawColor(Color.parseColor("#99000000"));
    //canvas.drawText(text, x, y, paint1);
    //canvas.restore();
  }

  public void change(float progress) {

    isChange = true;
    this.mProgress = progress;
    lastPositionOffset = progress;
    invalidate();

    //float diff = progress - lastPositionOffset;
    //if (progress == 0 || progress == 1 || (Math.abs(diff)) > 0.05) {
    //  //if (diff > 0) {
    //  //  Log.w("FireDirection", diff + "---right--");
    //  //} else {
    //  //  Log.w("FireDirection", diff + "---left--");
    //  //}
    //  //isChange = true;
    //  //this.mProgress = progress;
    //  //lastPositionOffset = progress;
    //  //invalidate();
    //}
  }
}
