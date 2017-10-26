package com.hencoder.hencoderpracticedraw4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * 作者：${ylw} on 2017-10-26 17:34
 */
public class Fl extends FrameLayout {
  private boolean isChange;

  public Fl(@NonNull Context context) {
    super(context);
  }

  public Fl(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Fl(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onDraw(Canvas canvas) {

    //super.onDraw(canvas);
    Log.w("FireFl", "------" + mProgress);
    if (isChange) {
      int width = getWidth();
      //canvas.save(); //在 裁剪出去的画布上绘制
      canvas.clipRect(width * mProgress / 100, 0, width, getHeight());
      canvas.drawColor(Color.parseColor("#22000000"));
      super.onDraw(canvas);
      //canvas.restore();
    } else {
      super.onDraw(canvas);
    }
  }

  private float mProgress;

  public void change(float progress) {
    isChange = true;
    this.mProgress = progress;
    invalidate();
  }
}
