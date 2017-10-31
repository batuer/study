package com.gusi.study.today;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 作者：${ylw} on 2017-10-30 18:19
 *
 * @author LC
 */
public class ClipTextView extends TextView {
  public static final int LEFT = 0;
  public static final int RIGHT = 1;

  private float mClipPercent = 0;
  private int mDirection = -1;
  private boolean isClip = false;

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
    if (!isClip) {
      super.onDraw(canvas);
    } else {
      int width = getWidth();
      int height = getHeight();
      //Log.e("Fire", getText() + ":--:" + mDirection + ":--:" + mClipPercent);
      float left = mDirection == RIGHT ? width * mClipPercent : 0;
      float right = mDirection == RIGHT ? width : width * mClipPercent;
      //在 裁剪出去的画布上绘制
      canvas.save();
      canvas.clipRect(left, 0, right, height);
      canvas.drawColor(Color.parseColor("#33000000"));
      super.onDraw(canvas);
      canvas.restore();
    }
  }

  public void clipPercent(float clipPercent, int direction) {
    if (!isClip) {
      isClip = true;
      setVisibility(VISIBLE);
    }
    this.mClipPercent = clipPercent;
    this.mDirection = direction;
    invalidate();
  }

  /**
   * set selected
   */
  public void setSelectedChange(boolean selected) {
    isClip = false;
    setVisibility(selected ? VISIBLE : GONE);
    invalidate();
  }
}
