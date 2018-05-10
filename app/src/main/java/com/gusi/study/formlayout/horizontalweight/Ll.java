package com.gusi.study.formlayout.horizontalweight;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @Author ylw  2018-04-04 11:25
 */
public class Ll extends LinearLayout {
  public Ll(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    View child = getChildAt(0);
  }

  @Override public void draw(Canvas canvas) {
    super.draw(canvas);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }
}
