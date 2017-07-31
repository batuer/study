package com.gusi.study.ScrollTv;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import android.widget.TextView;
import com.orhanobut.logger.Logger;

/**
 * 作者：${ylw} on 2017-07-31 17:18
 */
public class ScrollTv extends TextView {
  private Scroller mScroller;
  private int mScaledTouchSlop;

  public ScrollTv(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mScroller = new Scroller(context);
    mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
  }

  @Override public boolean dispatchTouchEvent(MotionEvent event) {
    Logger.w("dispatchTouchEvent: " + event);
    return super.dispatchTouchEvent(event);
  }

  private float mLastMoveY = 0;

  @Override public boolean onTouchEvent(MotionEvent event) {
    Logger.w("onTouchEvent:" + event);
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        if (!mScroller.isFinished()) {
          mScroller.forceFinished(true);
        }
        mLastMoveY = event.getRawY();
        break;
      case MotionEvent.ACTION_MOVE:
        float currentY = event.getRawY();
        Logger.i(mLastMoveY + ":--:" + currentY + ":--:" + mScaledTouchSlop);
        if (Math.abs(mLastMoveY - currentY) > mScaledTouchSlop) {
          int scrollY = (int) (mLastMoveY - currentY);
          boolean topLimit = scrollY < 0 && (getScrollY() + scrollY) < getTop();//下拉界限
          boolean bottomLimit =
              scrollY > 0 && (scrollY + getScrollY() + getHeight()) > getBottom();//上拉界限
          Logger.w(
              currentY + ":--:" + mLastMoveY + ":--:" + getScrollY() + ":--:" + getTop() + ":--:"
                  + getBottom() + ":--:" + getHeight());
          if (topLimit) {
            scrollTo(0, getTop());
            return true;
          } else if (bottomLimit) {
            scrollTo(0, getBottom() - getHeight());
            return true;
          } else {
            scrollBy(0, scrollY);
            mLastMoveY = currentY;
          }
        }

        break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
        break;
    }
    return super.onTouchEvent(event);
  }
}
