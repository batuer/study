package com.gusi.study.today;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class TodayActivity extends BaseActivity {
  @BindView(R.id.tabLayout) TodayTabLayout mTabLayout;
  @BindView(R.id.pager) ViewPager mViewPager;

  @Override protected int getLayout() {
    return R.layout.activity_today;
  }

  @Override protected void initView() {
    initToolBar(mToolbar, true, "Today");
    final List<String> data = initData();
    final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

      @Override public Fragment getItem(int position) {

        return PageFragment.newInstance(data.get(position));
      }

      @Override public int getCount() {
        return data.size();
      }

      @Override public CharSequence getPageTitle(int position) {
        return data.get(position);
      }
    };
    mViewPager.setAdapter(adapter);
    mTabLayout.setupWithViewPager(mViewPager);
  }

  private List<String> initData() {
    ArrayList<String> list = new ArrayList<>(15);
    for (int i = 0; i < 15; i++) {
      list.add("Item: " + i);
    }
    return list;
  }
}
