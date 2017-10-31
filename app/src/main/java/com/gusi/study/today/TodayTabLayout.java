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
  private static final double MIN_SCROLL = 0.05;
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
        TodayTabItemView itemTab =
            (TodayTabItemView) inflater.inflate(R.layout.item_today_tablayout, null);
        tab.setCustomView(itemTab);
        //
        itemTab.setAttrs(adapter.getPageTitle(i), mBgColor, mSelectedColor, mTabTextAppearance);
      }

      //切换Tab ViewPager 不滑动
      //clearOnTabSelectedListeners();
      //addOnTabSelectedListener(new OnTabSelectedListener() {
      //  @Override public void onTabSelected(Tab tab) {
      //    int position = tab.getPosition();
      //    mViewPager.setCurrentItem(position, false);
      //  }
      //
      //  @Override public void onTabUnselected(Tab tab) {
      //  }
      //
      //  @Override public void onTabReselected(Tab tab) {
      //
      //  }
      //});

    }
  }

  /**
   * @author LC
   */
  private class PagerListener implements ViewPager.OnPageChangeListener {

    private float mLastOffset = 0;
    private int mLaseSelected = 0;
    private int mLastScrollState = ViewPager.SCROLL_STATE_IDLE;


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      Log.e("FireScroll", mLastScrollState + ":-mLastScrollState-:" );

      if ((positionOffset == 0 || positionOffset == 1)) {
        //unSelected
        Tab unSelectedTab = getTabAt(mLaseSelected);
        TodayTabItemView unSelectedItemTab = (TodayTabItemView) unSelectedTab.getCustomView();
        unSelectedItemTab.setSelectedChange(false);
        //selected
        int selectedTabPosition = getSelectedTabPosition();
        Tab selectedTab = getTabAt(selectedTabPosition);
        TodayTabItemView selectedItemTab = (TodayTabItemView) selectedTab.getCustomView();
        selectedItemTab.setSelectedChange(true);

        mLaseSelected = selectedTabPosition;

        mLastScrollState = ViewPager.SCROLL_STATE_IDLE;
      } else if (mLastScrollState == ViewPager.SCROLL_STATE_DRAGGING) {
        float diffOffset = mLastOffset - positionOffset;
        if (positionOffset < MIN_SCROLL || (1 - positionOffset) < MIN_SCROLL
            || Math.abs(diffOffset) > MIN_SCROLL) {
          //mDiffOffset > 0  ViewPager 向左滑动(和手势相反)
          if (diffOffset > 0) {
            //position 是目标position
            //当前的
            TabLayout.Tab currentTab = getTabAt(position + 1);
            TodayTabItemView itemTabCurrent = (TodayTabItemView) currentTab.getCustomView();
            itemTabCurrent.clipPercent(positionOffset, ClipTextView.LEFT);
            //前面的
            TabLayout.Tab tabPre = getTabAt(position);
            TodayTabItemView itemTabPre = (TodayTabItemView) tabPre.getCustomView();

            itemTabPre.clipPercent(positionOffset, ClipTextView.RIGHT);
          } else {
            //ViewPager 向右滑动  position 是当前position
            TabLayout.Tab tab = getTabAt(position);
            TodayTabItemView itemTab = (TodayTabItemView) tab.getCustomView();
            itemTab.clipPercent(positionOffset, ClipTextView.RIGHT);
            //后边的
            TabLayout.Tab tabRight = getTabAt(position + 1);
            TodayTabItemView itemTabRight = (TodayTabItemView) tabRight.getCustomView();
            itemTabRight.clipPercent(positionOffset, ClipTextView.LEFT);
          }

          mLastOffset = positionOffset;
        }
      }
    }

    @Override public void onPageSelected(int position) {

    }

    @Override public void onPageScrollStateChanged(int state) {
      if (state == ViewPager.SCROLL_STATE_DRAGGING) {
        this.mLastScrollState = state;
      }
    }
  }
}
