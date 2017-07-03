package com.gusi.study.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by zjq on 2015/8/17.
 * 重新计算listView高度
 */
public class ListViewHeightUtil {
  /**
   * 动态设置ListView的高度
   */
  public static void setListViewHeightBasedOnChildren(ListView listView) {
    if (listView == null) return;
    ListAdapter listAdapter = listView.getAdapter();
    if (listAdapter == null) {
      // pre-condition
      return;
    }
    int totalHeight = 0;
    for (int i = 0; i < listAdapter.getCount(); i++) {
      View listItem = listAdapter.getView(i, null, listView);
      listItem.measure(0, 0);
      totalHeight += listItem.getMeasuredHeight();
      totalHeight += listItem.getPaddingTop();
      totalHeight += listItem.getPaddingBottom();
    }
    ViewGroup.LayoutParams params = listView.getLayoutParams();
    int measuredHeight = params.height;
    if (measuredHeight == 0) {
      params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
    }
    listView.setLayoutParams(params);
  }
}
