package com.gusi.study.bigrcv;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;
import com.blankj.utilcode.util.SizeUtils;

/**
 * @Author ylw  2018-01-10 14:07
 */
public class ScrollTv extends TextView {

  private final int mSlop;
  private int mMaxScroll;

  public ScrollTv(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop() / 2;
  }

  private float mPreMoveX = 0;

  @Override public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    switch (action) {
      case MotionEvent.ACTION_DOWN:
        break;
      case MotionEvent.ACTION_MOVE:
        float x = event.getX();
        if (mPreMoveX != 0 && Math.abs(x - mPreMoveX) >= mSlop) {
          int diffX = (int) (mPreMoveX - x);
          int realScroll = 0;
          int scrollX = getScrollX();
          if (diffX > 0) { //View ← 移动
            realScroll = Math.min(diffX, (mMaxScroll - scrollX));
          } else {
            realScroll = Math.max(diffX, -scrollX);
          }
          scrollBy(realScroll, 0);
        }
        mPreMoveX = x;
        break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
        mPreMoveX = 0;
        break;
    }
    return (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) ? true
        : super.onTouchEvent(event);
  }

  @Override public void layout(int l, int t, int r, int b) {
    super.layout(l, t, SizeUtils.dp2px(300), b);
    mMaxScroll = getLayout().getWidth() - getWidth() + getPaddingRight();
  }
}
