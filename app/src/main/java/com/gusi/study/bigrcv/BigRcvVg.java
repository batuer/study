package com.gusi.study.bigrcv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @Author ylw  2018-01-10 18:17
 */
public class BigRcvVg extends FrameLayout {
  public BigRcvVg(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    //Log.w("Fire", "BigRcvVg:19行:" + right + ":-:" + getMeasuredWidth() + ":-:" + getWidth());
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    //Log.w("Fire", "BigRcvVg:24行:" + getMeasuredWidth() + ":-:" + getWidth());
  }
}
