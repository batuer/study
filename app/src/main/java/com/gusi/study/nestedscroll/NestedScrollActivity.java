package com.gusi.study.nestedscroll;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import com.gusi.headline.HeadLineTabLayout;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;
import com.gusi.study.today.PageFragment;

/**
 * @author
 * @Date
 */
public class NestedScrollActivity extends BaseActivity {
  @BindView(R.id.tabLayout1) HeadLineTabLayout mTabLayout;
  @BindView(R.id.view_pager) ViewPager mViewPager;
  String[] titles = { "保障", "家人", "首页", "我的", "药丸", "简介" };

  @Override protected int getLayout() {
    return R.layout.activity_nested_scroll;
  }

  @Override protected void initView() {
    super.initView();
    initToolBar(mToolbar, true, "NestedScroll");

    final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

      @Override public Fragment getItem(int position) {

        return PageFragment.newInstance(titles[position]);
      }

      @Override public int getCount() {
        return titles.length;
      }

      @Override public CharSequence getPageTitle(int position) {
        return titles[position];
      }
    };

    mViewPager.setAdapter(adapter);
    mTabLayout.setupWithViewPager(mViewPager);
  }
}
