package com.gusi.study.bigrcv;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @Author ylw  2018-01-10 11:24
 */
public class BigRcv extends RecyclerView {

  public BigRcv(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  //@Override protected void onMeasure(int widthSpec, int heightSpec) {
  //  super.onMeasure(widthSpec, heightSpec);
  //  Log.w("Fire", "BigRcv:20行:" + getMeasuredWidth() + ":-:" + getWidth());
  //}
  //
  //@Override public boolean onTouchEvent(MotionEvent e) {
  //  //scrollBy(10, 0);
  //  //scrollToPosition();
  //  scrollTo(getScrollX() + 20, getScrollY());
  //  Log.w("Fire", "BigRcv:27行:" + getScrollX() + ":-:" + getMeasuredWidth() + ":-:" + getWidth());
  //  Log.w("Fire", "BigRcv:29行:" + getRight() + ":-:" + getLeft());
  //  return super.onTouchEvent(e);
  //}
  //
  //@Override public void computeScroll() {
  //  super.computeScroll();
  //  Log.w("Fire", "BigRcv:34行:");
  //}
  //
  //@Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
  //  super.onLayout(changed, l, t, 1080, b);
  //  LayoutManager manager = getLayoutManager();
  //
  //
  //
  //  Log.w("Fire", "BigRcv:32行:" + +getMeasuredWidth() + ":-:" + getWidth() + ":-:" + r);
  //  Log.w("Fire", "BigRcv:40行:" + changed + ":-:" + l + ":-:" + r);
  //  Log.w("Fire", "BigRcv:41行:" + getRight() + ":-:" + getLeft());
  //
  //
  //
  //
  //}
}
