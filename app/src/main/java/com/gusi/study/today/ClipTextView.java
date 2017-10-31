package com.gusi.study.today;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * 作者：${ylw} on 2017-10-30 18:19
 */
public class ClipTextView extends TextView {
  private float mClipPercent = 0;

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
    if (Math.abs(mClipPercent) > 0) {
      int width = getWidth();
      int height = getHeight();

      float left = mClipPercent > 0 ? width * mClipPercent : 0;
      float right = mClipPercent > 0 ? 0 : width * mClipPercent;
      Log.w("Fire", "ClipPercent:" + left + ":--:" + right);
      //在 裁剪出去的画布上绘制
      canvas.save();
      canvas.clipRect(left, 0, right, height);
      super.onDraw(canvas);
      canvas.restore();
    }
  }

  public void clipPercent(float clipPercent) {
    this.mClipPercent = clipPercent;
    setVisibility(clipPercent == 0 ? GONE : VISIBLE);
    invalidate();
  }
  public void pageSelectedChange(){
    invalidate();
  }

}
