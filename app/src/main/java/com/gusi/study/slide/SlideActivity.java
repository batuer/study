package com.gusi.study.slide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class SlideActivity extends BaseActivity {
  @BindView(R.id.tool_bar) Toolbar mToolbar;
  @BindView(R.id.view_pager) ViewPager mViewPager;

  @Override protected int getLayout() {
    return R.layout.activity_slide;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolBar(mToolbar, true, "Slide");
    mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
  }

  class MyPagerAdapter extends FragmentPagerAdapter {
    private List<String> paramList;

    public MyPagerAdapter(FragmentManager fm) {
      super(fm);
      paramList = new ArrayList<>(4);
      for (int i = 0; i < 4; i++) {
        paramList.add("Param: " + i);
      }
    }

    @Override public Fragment getItem(int position) {
      return BlankFragment.newInstance(paramList.get(position));
    }

    @Override public int getCount() {
      return 1;
    }
  }
}
