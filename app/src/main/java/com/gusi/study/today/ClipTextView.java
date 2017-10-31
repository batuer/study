package com.gusi.study.today;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 作者：${ylw} on 2017-10-30 18:19
 */
public class ClipTextView extends TextView {

  public ClipTextView(Context context) {
    super(context);
  }

  public ClipTextView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public ClipTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onDraw(Canvas canvas) {
    int width = getWidth();
    int height = getHeight();
    //在 裁剪出去的画布上绘制
    canvas.save();
    canvas.clipRect(0, 0, width / 2, height);
    super.onDraw(canvas);
    canvas.restore();

  }

  public void clip(int percent) {
  }
}
