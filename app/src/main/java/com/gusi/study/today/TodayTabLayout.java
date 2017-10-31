package com.gusi.study.today;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import com.gusi.study.R;

/**
 * 作者：${ylw} on 2017-10-30 17:47
 */
public class TodayTabLayout extends TabLayout {
  private ViewPager mViewPager;
  private int mSelectedColor;
  private int mBgColor;
  private int mTabTextAppearance;

  public TodayTabLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs, 0);
  }

  public TodayTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr);
  }

  private void init(Context context, AttributeSet attrs, int defStyleAttr) {

    TypedArray a =
        context.obtainStyledAttributes(attrs, android.support.design.R.styleable.TabLayout,
            defStyleAttr, android.support.design.R.style.Widget_Design_TabLayout);
    try {
      mSelectedColor = a.getColor(android.support.design.R.styleable.TabLayout_tabSelectedTextColor,
          ContextCompat.getColor(context, R.color.colorPrimary));
      mBgColor = a.getColor(android.support.design.R.styleable.TabLayout_tabTextColor, Color.BLACK);

      mTabTextAppearance =
          a.getResourceId(android.support.design.R.styleable.TabLayout_tabTextAppearance,
              android.support.design.R.style.TextAppearance_Design_Tab);
    } finally {
      a.recycle();
    }
  }

  @Override public void setupWithViewPager(@Nullable ViewPager viewPager) {
    super.setupWithViewPager(viewPager);
    setupPager(viewPager);
  }

  @Override public void setupWithViewPager(@Nullable ViewPager viewPager, boolean autoRefresh) {
    super.setupWithViewPager(viewPager, autoRefresh);
    setupPager(viewPager);
  }

  private void setupPager(@Nullable ViewPager viewPager) {
    if (mViewPager == null) {
      mViewPager = viewPager;
      mViewPager.addOnPageChangeListener(new PagerListener());

      PagerAdapter adapter = viewPager.getAdapter();
      int count = adapter.getCount();
      LayoutInflater inflater = LayoutInflater.from(getContext());
      for (int i = 0; i < count; i++) {
        TabLayout.Tab tab = getTabAt(i);
        TodayTabLayoutItemView itemTab =
            (TodayTabLayoutItemView) inflater.inflate(R.layout.item_today_tablayout, null);
        tab.setCustomView(itemTab);
        //
        itemTab.setAttrs(adapter.getPageTitle(i), mBgColor, mSelectedColor, mTabTextAppearance);
      }
    }
  }

  /**
   *
   */
  private class PagerListener implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      Log.w("Fire", position + ":-onPageScrolled-:" + positionOffset);
      TabLayout.Tab tab = getTabAt(position);
      TodayTabLayoutItemView itemTab = (TodayTabLayoutItemView) tab.getCustomView();
      itemTab.clipPercent(positionOffset);
    }

    @Override public void onPageSelected(int position) {
      Log.w("Fire", position + ":-onPageSelected-:");
    }

    @Override public void onPageScrollStateChanged(int state) {

    }
  }
}
