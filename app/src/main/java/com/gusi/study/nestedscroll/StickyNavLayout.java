package com.gusi.study.nestedscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Date: 2017-11-06 14:55
 *
 * @author ylw
 */
public class StickyNavLayout extends LinearLayout {
  private static final String TAG = "FireSticky";

  public StickyNavLayout(Context context) {
    super(context);
  }

  public StickyNavLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public StickyNavLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
    Log.w(TAG, "-----onStartNestedScroll--");
    return false;
  }

  @Override public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {

  }

  @Override public void onStopNestedScroll(View target) {

  }

  @Override
  public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

  }

  @Override public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {

  }

  @Override
  public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
    return false;
  }

  @Override public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
    return false;
  }

  @Override public int getNestedScrollAxes() {
    return 0;
  }
}
