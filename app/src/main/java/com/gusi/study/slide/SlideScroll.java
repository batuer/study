package com.gusi.study.slide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import com.gusi.study.R;
import com.orhanobut.logger.Logger;

/**
 * 作者：${ylw} on 2017-07-03 16:26
 */
public class SlideScroll extends ScrollView {

  private ListView mLv;
  private float mPreY;

  public SlideScroll(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    mLv = (ListView) findViewById(R.id.lv);
  }

  private boolean isTouchOnView(float motionX, float motionY, View v) {
    int[] vLoc = new int[2];
    v.getLocationOnScreen(vLoc);
    int x = vLoc[0];
    int y = (int) (vLoc[1] + v.getTranslationY());
    Logger.w(getScrollY() + "--:" + v.getTranslationY());
    return motionX >= x && motionX <= (x + v.getWidth()) && motionY >= y && motionY <= (y
        + v.getHeight());
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {

    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mPreY = ev.getY();
        break;
      case MotionEvent.ACTION_MOVE:
        float currentY = ev.getY();
        float diffY = currentY - mPreY;
        mPreY = currentY;
        if (diffY < 0) {//上拉
          if (!isBottom(mLv)) {
            return false;
          }
        } else {//下拉
          if (!isTop(mLv)) {
            return false;
          }
        }
        break;
      case MotionEvent.ACTION_CANCEL:
      case MotionEvent.ACTION_UP:
        break;
    }

    //if (isTop())
    boolean b = super.onInterceptTouchEvent(ev);
    return b;
    //return false;
  }

  public boolean isBottom(final ListView listView) {
    boolean result = false;
    if (listView.getLastVisiblePosition() == (listView.getCount() - 1)) {
      final View bottomChildView = listView.getChildAt(
          listView.getLastVisiblePosition() - listView.getFirstVisiblePosition());
      result = (listView.getHeight() >= bottomChildView.getBottom());
    }
    ;
    return result;
  }

  public boolean isTop(final ListView listView) {
    boolean result = false;
    if (listView.getFirstVisiblePosition() == 0) {
      final View topChildView = listView.getChildAt(0);
      result = topChildView.getTop() == 0;
    }
    return result;
  }
}
