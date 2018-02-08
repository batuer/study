package com.gusi.study.granzort;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author ylw  2018-01-29 16:14
 */
public class HorizontalViewPager extends ViewPager {
  public HorizontalViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
    if (v.getVisibility() != VISIBLE) return false;
    //if (v != this && v instanceof RecyclerView )
    if (v instanceof RecyclerView) {
      RecyclerView rcv = (RecyclerView) v;
      RecyclerView.LayoutManager layoutManager = rcv.getLayoutManager();
      if (layoutManager instanceof LinearLayoutManager) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        if (linearLayoutManager.getOrientation() == RecyclerView.HORIZONTAL) {
          return true;
        }
      }
    }

    return super.canScroll(v, checkV, dx, x, y);
  }
}
