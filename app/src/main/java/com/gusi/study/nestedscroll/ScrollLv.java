package com.gusi.study.nestedscroll;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

/**
 * @author ylw   2017-11-15 11:00
 */
public class ScrollLv extends ListView implements NestedScrollingChild {
  private final NestedScrollingChildHelper mChildHelper;

  public ScrollLv(Context context, AttributeSet attrs) {
    super(context, attrs);
    mChildHelper = new NestedScrollingChildHelper(this);

    setNestedScrollingEnabled(true);
  }

  @Override public void setNestedScrollingEnabled(boolean enabled) {
    Log.w("FireScrollLv", "setNestedScrollingEnabled():19行:" + enabled);
    mChildHelper.setNestedScrollingEnabled(enabled);
  }

  @Override public boolean isNestedScrollingEnabled() {
    boolean nestedScrollingEnabled = mChildHelper.isNestedScrollingEnabled();
    Log.w("FireScrollLv", "isNestedScrollingEnabled():24行:" + nestedScrollingEnabled);
    return nestedScrollingEnabled;
  }

  @Override public boolean startNestedScroll(int axes) {
    boolean b = mChildHelper.startNestedScroll(axes);
    Log.w("FireScrollLv", b + ":startNestedScroll():29行:" + axes);
    return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
  }

  @Override public void stopNestedScroll() {
    Log.w("FireScrollLv", "stopNestedScroll():34行:");
    mChildHelper.stopNestedScroll();
  }

  @Override public boolean hasNestedScrollingParent() {
    boolean b = mChildHelper.hasNestedScrollingParent();
    Log.w("FireScrollLv", "hasNestedScrollingParent():38行:" + b);
    return b;
  }

  @Override
  public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {

    boolean b =
        mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed,
            offsetInWindow);
    Log.w("FireScrollLv",
        "dispatchNestedScroll():44行:" + dyUnconsumed + ":" + dyConsumed + ":" + b);
    return b;
  }

  @Override
  public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
    if (dy == 0) return false;
    boolean b = false;
    if (dy < 0) { //手势下拉
      b = ViewCompat.canScrollVertically(this, -1);
    } else {
      b = ViewCompat.canScrollVertically(this, 1);
    }
    boolean b1 = mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    Log.w("FireScrollLv", "dispatchNestedPreScroll():57行:" + dy + ":" + b + ":" + b1);
    return b1;
  }

  @Override public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
    boolean b = mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    Log.w("FireScrollLv", "dispatchNestedFling():65行:" + velocityY + ":" + consumed + ":" + b);
    return b;
  }

  @Override public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
    boolean b = mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    Log.w("FireScrollLv", "dispatchNestedPreFling():70行:" + velocityY + ":" + b);
    return b;
  }
}
