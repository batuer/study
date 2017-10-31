package com.hencoder.hencoderpracticedraw4;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  TabLayout tabLayout;
  ViewPager pager;
  List<PageModel> pageModels = new ArrayList<>();

  {
    pageModels.add(new PageModel(R.layout.sample_clip_rect, R.string.title_clip_rect,
        R.layout.practice_clip_rect));
    pageModels.add(new PageModel(R.layout.sample_clip_path, R.string.title_clip_path,
        R.layout.practice_clip_path));
    pageModels.add(new PageModel(R.layout.sample_translate, R.string.title_translate,
        R.layout.practice_translate));
    pageModels.add(
        new PageModel(R.layout.sample_scale, R.string.title_scale, R.layout.practice_scale));
    pageModels.add(
        new PageModel(R.layout.sample_rotate, R.string.title_rotate, R.layout.practice_rotate));
    pageModels.add(
        new PageModel(R.layout.sample_skew, R.string.title_skew, R.layout.practice_skew));
    pageModels.add(new PageModel(R.layout.sample_matrix_translate, R.string.title_matrix_translate,
        R.layout.practice_matrix_translate));
    pageModels.add(new PageModel(R.layout.sample_matrix_scale, R.string.title_matrix_scale,
        R.layout.practice_matrix_scale));
    pageModels.add(new PageModel(R.layout.sample_matrix_rotate, R.string.title_matrix_rotate,
        R.layout.practice_matrix_rotate));
    pageModels.add(new PageModel(R.layout.sample_matrix_skew, R.string.title_matrix_skew,
        R.layout.practice_matrix_skew));
    pageModels.add(new PageModel(R.layout.sample_camera_rotate, R.string.title_camera_rotate,
        R.layout.practice_camera_rotate));
    pageModels.add(
        new PageModel(R.layout.sample_camera_rotate_fixed, R.string.title_camera_rotate_fixed,
            R.layout.practice_measure_text));
    pageModels.add(new PageModel(R.layout.sample_camera_rotate_hitting_face,
        R.string.title_camera_rotate_hitting_face, R.layout.practice_camera_rotate_hitting_face));
    pageModels.add(new PageModel(R.layout.sample_flipboard, R.string.title_flipboard,
        R.layout.practice_flipboard));
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    pager = (ViewPager) findViewById(R.id.pager);
    final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

      @Override public Fragment getItem(int position) {
        PageModel pageModel = pageModels.get(position);
        return PageFragment.newInstance(pageModel.sampleLayoutRes, pageModel.practiceLayoutRes);
      }

      @Override public int getCount() {
        return pageModels.size();
      }

      @Override public CharSequence getPageTitle(int position) {
        return getString(pageModels.get(position).titleRes);
      }
    };
    pager.setAdapter(adapter);
    tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    tabLayout.setupWithViewPager(pager);

    setupTabCustomView(adapter);

    pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Log.w("Fire", position + ":--:" + positionOffset + ":--:" + positionOffsetPixels);
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        //((Tv) tab.getCustomView()).change(positionOffset);
        FrameLayout itemTab = (FrameLayout) tab.getCustomView();
        TextView tvBg = (TextView) itemTab.findViewById(R.id.tv_bg);
        Log.w("FireScrolled",
            position + ":--:" + itemTab.isSelected() + ":--:" + tvBg.isSelected());
      }

      @Override public void onPageSelected(int position) {

      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });
    //pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    //  @Override
    //  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    //    Log.w("Fire", position + ":--:" + positionOffset + ":--:" + positionOffsetPixels);
    //  }
    //
    //  @Override public void onPageSelected(int position) {
    //
    //  }
    //
    //  @Override public void onPageScrollStateChanged(int state) {
    //
    //  }
    //});
  }

  private void setupTabCustomView(FragmentPagerAdapter adapter) {
    int count = adapter.getCount();
    LayoutInflater inflater = getLayoutInflater();
    for (int i = 0; i < count; i++) {
      TabLayout.Tab tab = tabLayout.getTabAt(i);
      FrameLayout itemTab = (FrameLayout) inflater.inflate(R.layout.item_tab, null);
      TextView tvBg = (TextView) itemTab.findViewById(R.id.tv_bg);
      tab.setCustomView(itemTab);
      tvBg.setText(adapter.getPageTitle(i) + "Item-------Item-------Item-------");
      //((TextView) tab.getCustomView()).setText(adapter.getPageTitle(i)+"Item-------Item-------Item-------");
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    return super.onCreateOptionsMenu(menu);
  }

  private class PageModel {
    @LayoutRes int sampleLayoutRes;
    @StringRes int titleRes;
    @LayoutRes int practiceLayoutRes;

    PageModel(@LayoutRes int sampleLayoutRes, @StringRes int titleRes,
        @LayoutRes int practiceLayoutRes) {
      this.sampleLayoutRes = sampleLayoutRes;
      this.titleRes = titleRes;
      this.practiceLayoutRes = practiceLayoutRes;
    }
  }
}