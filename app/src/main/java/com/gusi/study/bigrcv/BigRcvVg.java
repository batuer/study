package com.gusi.study.bigrcv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

/**
 * @Author ylw  2018-01-10 18:17
 */
public class BigRcvVg extends FrameLayout {
  private final int mSlop;
  private int mMaxScroll;
  private int mMaxLeftMargin = 0;
  private boolean mConsumeTouchEvent = false;

  public BigRcvVg(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop() / 2;
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    View parent = (View) getParent();
    MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
    int realRight = parent.getWidth() + params.leftMargin + params.rightMargin;
    super.onLayout(changed, left, top, realRight, bottom);

    mMaxScroll = getMeasuredWidth() - realRight + getPaddingRight() + getPaddingLeft();
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int maxWidth = 0;
    int maxLeftMargin = 0;
    for (int i = 0; i < getChildCount(); i++) {
      View view = getChildAt(i);
      if (view.getVisibility() == GONE) continue;
      int widthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
      view.measure(widthSpec, heightMeasureSpec);
      MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
      int viewRealWidth = view.getMeasuredWidth() + params.leftMargin + params.rightMargin;
      maxWidth = Math.max(viewRealWidth, maxWidth);
      maxLeftMargin = Math.max(params.leftMargin, maxLeftMargin);
    }
    mMaxLeftMargin = maxLeftMargin;
    setMeasuredDimension(maxWidth, getMeasuredHeight());
  }

  private float mPreMoveX = 0;

  @Override public boolean dispatchTouchEvent(MotionEvent event) {
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
            realScroll = Math.min(diffX, (mMaxScroll - scrollX + mMaxLeftMargin));
          } else {
            realScroll = Math.max(diffX, -scrollX);
          }
          scrollBy(realScroll, 0);

          mConsumeTouchEvent = true;
        }
        mPreMoveX = x;
        break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
        mPreMoveX = 0;
        mConsumeTouchEvent = false;
        break;
    }
    boolean b = super.dispatchTouchEvent(event);
    return mConsumeTouchEvent ? false : b;
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    boolean b = super.onTouchEvent(event);
    return mConsumeTouchEvent ? true : b;
  }
}
