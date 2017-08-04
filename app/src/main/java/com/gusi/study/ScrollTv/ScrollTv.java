package com.gusi.study.ScrollTv;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import android.widget.TextView;
import com.gusi.study.R;
import com.orhanobut.logger.Logger;

/**
 * 作者：${ylw} on 2017-07-31 17:18
 */
public class ScrollTv extends TextView {
  private Scroller mScroller;
  private int mScaledTouchSlop;
  private static final String DISPATCH = "Dispatch";
  private static final String TOUCH = "DispatchTouch";
  private final boolean mReceiveMoveEvent;

  public ScrollTv(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mScroller = new Scroller(context);
    mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ScrollTv);
    mReceiveMoveEvent = array.getBoolean(R.styleable.ScrollTv_receive_move_event, false);
    array.recycle();
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    //MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE,MeasureSpec.EXACTLY);
    //measure(widthMeasureSpec,);

    int i = (1 << 30) - 1;
    Logger.w("--:" + i);
    int size = MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(i, MeasureSpec.EXACTLY));
    int size1 = MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(i, MeasureSpec.AT_MOST));
    int size2 = MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(i, MeasureSpec.UNSPECIFIED));
    Logger.w(size + ":--:" + size1 + ":--:" + size2);
  }

  private float mLastMoveY = 0;

  @Override public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        if (!mScroller.isFinished()) {
          mScroller.forceFinished(true);
        }
        mLastMoveY = event.getRawY();
        break;
      case MotionEvent.ACTION_MOVE:
        float currentY = event.getRawY();
        Log.i(TOUCH, mLastMoveY + ":--:" + currentY + ":--:" + mScaledTouchSlop);
        if (Math.abs(mLastMoveY - currentY) > mScaledTouchSlop) {
          int scrollY = (int) (mLastMoveY - currentY);
          boolean topLimit = scrollY < 0 && (getScrollY() + scrollY) < getTop();//下拉界限
          boolean bottomLimit =
              scrollY > 0 && (scrollY + getScrollY() + getHeight()) > getBottom();//上拉界限
          Log.w(TOUCH, currentY + ":--mLastMoveY:" + mLastMoveY + ":--getScrollY:" + getScrollY()
              + ":--getTop:" + getTop() + ":--getBottom:" + getBottom() + ":--getHeight:"
              + getHeight() + ":--topLimit:" + topLimit + ":--bottomLimit:" + bottomLimit);
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
    boolean b = super.onTouchEvent(event);
    return mReceiveMoveEvent ? true : b;
  }
}
