package com.gusi.study.nestedscroll;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import com.gusi.study.R;

/**
 * @author ylw   2017-11-15 13:45
 */
public class ScrollParent extends LinearLayout implements NestedScrollingParent {

  private ImageView mIv;
  private OverScroller mScroller;
  //private RecyclerView mRcv;
  private final NestedScrollingParentHelper mParentHelper;
  private int mTouchSlop;
  private int mMinimumVelocity;
  private int mMaximumVelocity;
  private View mView;
  private int mTopIvH;

  public ScrollParent(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mScroller = new OverScroller(context);
    mParentHelper = new NestedScrollingParentHelper(this);
    final ViewConfiguration configuration = ViewConfiguration.get(getContext());
    mTouchSlop = configuration.getScaledTouchSlop();
    mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
    mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();

    //setNestedScrollingEnabled(true);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    mIv = (ImageView) findViewById(R.id.iv);
    mView = findViewById(R.id.rcv);
    //mRcv = (RecyclerView) findViewById(R.id.rcv);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //不限制顶部的高度
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    mTopIvH = mIv.getHeight();
    ViewGroup.LayoutParams params = mView.getLayoutParams();
    params.height = getMeasuredHeight();
    setMeasuredDimension(getMeasuredWidth(), mTopIvH + mView.getMeasuredHeight());
  }

  //按照自己的需求返回true，该方法决定了当前控件是否能接收到其内部View(非并非是直接子View)滑动时的参数；
  // 假设你只涉及到纵向滑动，这里可以根据nestedScrollAxes这个参数，进行纵向判断。
  @Override public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
    Log.e("FireScrollParent",
        ":65行:" + child.getClass().getSimpleName() + ":" + target.getClass().getSimpleName() + ":"
            + nestedScrollAxes);
    return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
  }

  //onStartNestedScroll 返回true以后  接受处理
  @Override public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
    Log.e("FireScrollParent",
        "71行:" + child.getClass().getSimpleName() + ":" + target.getClass().getSimpleName() + ":"
            + nestedScrollAxes);
    mParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
    //startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
  }

  @Override public void onStopNestedScroll(View target) {
    Log.e("FireScrollParent", "onStopNestedScroll():69行:" + target.getClass().getSimpleName());
    //stopNestedScroll();
    mParentHelper.onStopNestedScroll(target);
  }

  //
  @Override
  public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    Log.w("Fire", "ScrollParent:87行:" + target.getClass().getSimpleName() + ':' + dyConsumed + ":"
        + dyUnconsumed);
    //final int myConsumed = moveBy(dyUnconsumed);
    //final int myUnconsumed = dyUnconsumed - myConsumed;
    //dispatchNestedScroll(0, myConsumed, 0, myUnconsumed, null);
  }

  //该方法的会传入内部View移动的dx,dy，如果你需要消耗一定的dx,dy，
  // 就通过最后一个参数consumed进行指定，例如我要消耗一半的dy，就可以写consumed[1]=dy/2
  @Override public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int len = consumed.length, i = 0; i < len; i++) {
      sb.append(consumed[i]);
    }
    sb.append("]");
    Log.e("FireScrollParent", "onNestedPreScroll():86行:" + target.getClass().getSimpleName() + ":" + dy + ":" + sb.toString());

    int scrollY = getScrollY();
    boolean hiddenTop = dy > 0 && scrollY < mTopIvH;
    boolean showTop = dy < 0 && scrollY > 0 && !ViewCompat.canScrollVertically(target, -1);
    if (hiddenTop) {
      int consumeDy = Math.min((mTopIvH - scrollY), dy);
      scrollBy(0, consumeDy);
      consumed[1] = consumeDy;
    }
    if (showTop) {
      int consumeDy = Math.max(-scrollY, dy);
      scrollBy(0, consumeDy);
      consumed[1] = consumeDy;
    }
  }

  //你可以捕获对内部View的fling事件，如果return true则表示拦截掉内部View的事件。
  @Override
  public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
    Log.e("FireScrollParent", "onNestedFling(): 94:" + ":" + velocityX + ":" + consumed);
    if (getScrollY() >= mTopIvH) return false;
    fling((int) velocityY);
    return true;
  }

  @Override public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
    Log.e("FireScrollParent",
        "onNestedPreFling():115行:" + target.getClass().getSimpleName() + ":" + velocityY);
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
