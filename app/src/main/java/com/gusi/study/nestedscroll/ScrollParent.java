package com.gusi.study.nestedscroll;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.gusi.study.R;

/**
 * @Author ylw  2017-11-14 16:00
 */
public class ScrollParent extends LinearLayout implements NestedScrollingParent {

  private ImageView mIv;

  public ScrollParent(Context context) {
    super(context);
  }

  public ScrollParent(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public ScrollParent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
    if (nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL) {
      return true;
    }
    return false;
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    mIv = (ImageView) findViewById(R.id.iv);
  }

  @Override public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
    int height = mIv.getHeight();
    Log.w("FireScrollParent", ((dy > 0 && getScrollY() < height) || (dy < 0 && getScrollY() > 0))+"onNestedPreScroll():49行:" + dy+":"+getScrollY());
    if ((dy > 0 && getScrollY() < height) || (dy < 0 && getScrollY() > 0)) {
      consumed[0] = dy;
      scrollBy(0, dy);
    }
  }

  @Override public boolean onTouchEvent(MotionEvent event) {

    //final int actionMasked = event.getActionMasked();
    //switch (actionMasked) {
    //  case MotionEvent.ACTION_DOWN: {
    //    startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
    //    break;
    //  }
    //  case MotionEvent.ACTION_MOVE:
    //    if (dispatchNestedPreScroll(...){
    //    deltaY -= mScrollConsumed[1];
    //
    //  }

    return super.onTouchEvent(event);
  }
}
