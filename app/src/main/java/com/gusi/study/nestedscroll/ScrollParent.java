package com.gusi.study.nestedscroll;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import com.gusi.study.R;

/**
 * @Author ylw  2017-11-14 16:00
 */
public class ScrollParent extends LinearLayout implements NestedScrollingParent {

  private ImageView mIv;
  private OverScroller mScroller;
  private RecyclerView mRcv;

  public ScrollParent(Context context) {
    super(context);
  }

  public ScrollParent(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mScroller = new OverScroller(context);
  }

  public ScrollParent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    mIv = (ImageView) findViewById(R.id.iv);
    mRcv = (RecyclerView) findViewById(R.id.rcv);
  }

  //定要按照自己的需求返回true，该方法决定了当前控件是否能接收到其内部View(非并非是直接子View)滑动时的参数；
  // 假设你只涉及到纵向滑动，这里可以根据nestedScrollAxes这个参数，进行纵向判断。
  @Override public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
    if (nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL) {
      return true;
    }
    return false;
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    mTopIvH = mIv.getHeight();
    Log.e("FireScrollParent", "onMeasure(): 50:" + mRcv.getMeasuredHeight()+":"+ mRcv.getHeight());
  }

  private int mTopIvH;

  @Override public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {

  }

  @Override public void onStopNestedScroll(View target) {

  }

  @Override
  public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
      int dyUnconsumed) {

  }

  //该方法的会传入内部View移动的dx,dy，如果你需要消耗一定的dx,dy，
  // 就通过最后一个参数consumed进行指定，例如我要消耗一半的dy，就可以写consumed[1]=dy/2
  @Override public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
    int scrollY = getScrollY();
    boolean hiddenTop = dy > 0 && scrollY < mTopIvH;
    boolean showTop = dy < 0 && scrollY > 0 && !ViewCompat.canScrollVertically(target, -1);
    //Log.w("FireScrollParent", mTopIvH + ":" + dy + ":" + scrollY + ":" + (dy + scrollY));
    if (hiddenTop) {
      int consumeDy = Math.min((mTopIvH - scrollY), dy);
      scrollBy(0, consumeDy);
      consumed[1] = consumeDy;

      requestLayout();
    }
    if (showTop) {
      int consumeDy = Math.max(-scrollY, dy);
      scrollBy(0, consumeDy);
      consumed[1] = consumeDy;
      requestLayout();
    }
  }

  //你可以捕获对内部View的fling事件，如果return true则表示拦截掉内部View的事件。
  @Override public boolean onNestedFling(View target, float velocityX, float velocityY,
      boolean consumed) {
    Log.w("FireScrollParent", "onNestedFling(): 94:" +getScrollY()+":"+mTopIvH);
    if (getScrollY() >= mTopIvH) return false;
    fling((int) velocityY);
    return true;
  }

  @Override public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
    return false;
  }

  @Override public int getNestedScrollAxes() {

    return 0;
  }

  public void fling(int velocityY) {
    mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mTopIvH);
    invalidate();
  }

}
