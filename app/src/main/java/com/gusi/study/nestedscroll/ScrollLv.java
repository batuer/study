package com.gusi.study.nestedscroll;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by batue on 2017/11/14.
 */

public class ScrollLv extends ListView implements NestedScrollingChild {
  public ScrollLv(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public void setNestedScrollingEnabled(boolean enabled) {

  }

  @Override public boolean isNestedScrollingEnabled() {
    return false;
  }

  @Override public boolean startNestedScroll(int axes) {
    return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
  }

  @Override public void stopNestedScroll() {

  }

  @Override public boolean hasNestedScrollingParent() {
    return false;
  }

  @Override public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
      int dyUnconsumed, int[] offsetInWindow) {
    return false;
  }

  @Override
  public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
    return false;
  }

  @Override public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
    return false;
  }

  @Override public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
    return false;
  }
}
