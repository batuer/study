package com.gusi.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import butterknife.OnClick;
import com.gusi.study.ScrollTv.ScrollTvActivity;
import com.gusi.study.base.BaseActivity;
import com.gusi.study.constraint.ConstraintActivity;
import com.gusi.study.drawable.DrawableActivity;
import com.gusi.study.flippertv.FlipperTvActivity;
import com.gusi.study.floating.FloatingActivity;
import com.gusi.study.flow.FlowActivity;
import com.gusi.study.formlayout.FormActivity;
import com.gusi.study.granzort.GranzortActivity;
import com.gusi.study.keyboard.KeyBoardActivity;
import com.gusi.study.loading.LoadingActivity;
import com.gusi.study.nestedscroll.NestedScrollActivity;
import com.gusi.study.piechart.PieChartActivity;
import com.gusi.study.rainbow.RainbowActivity;
import com.gusi.study.today.TodayActivity;
import com.gusi.study.vlayout.VLayoutActivity;

public class MainActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolBar(mToolbar, true, "Study");
  }

  @Override protected int getLayout() {
    return R.layout.activity_main;
  }

  @OnClick(R.id.btn_pie_chart) public void pieChart(View view) {
    startActivity(new Intent(this, PieChartActivity.class));
  }

  @OnClick(R.id.btn_drawable) public void drawable(View view) {
    startActivity(new Intent(this, DrawableActivity.class));
  }

  @OnClick(R.id.btn_flow) public void flowLayout(View view) {
    startActivity(new Intent(this, FlowActivity.class));
  }

  @OnClick(R.id.btn_constraint) public void constraint(View view) {
    startActivity(new Intent(this, ConstraintActivity.class));
  }

  @OnClick(R.id.btn_form) public void formLayout(View view) {
    startActivity(new Intent(this, FormActivity.class));
  }

  @OnClick(R.id.btn_scroll_tv) public void scrollTv(View view) {
    startActivity(new Intent(this, ScrollTvActivity.class));
  }

  @OnClick(R.id.btn_show_loading) public void loading(View view) {
    startActivity(new Intent(this, LoadingActivity.class));
  }

  @OnClick(R.id.btn_granzort) public void granzort(View view) {
    startActivity(new Intent(this, GranzortActivity.class));
  }

  @OnClick(R.id.btn_keyboard) public void keyboard(View view) {
    startActivity(new Intent(this, KeyBoardActivity.class));
  }

  @OnClick(R.id.btn_float_ball) public void floatBall(View view) {
    startActivity(new Intent(this, FloatingActivity.class));
  }

  @OnClick(R.id.btn_flipper) public void flipperTv(View view) {
    startActivity(new Intent(this, FlipperTvActivity.class));
  }

  @OnClick(R.id.btn_today_tablayout) public void todayTabLayout(View view) {
    startActivity(new Intent(this, TodayActivity.class));
  }

  @OnClick(R.id.btn_nested_scroll) public void nestedScroll(View view) {
    startActivity(new Intent(this, NestedScrollActivity.class));
  }

  @OnClick(R.id.btn_rainbow) public void rainbow(View view) {
    startActivity(new Intent(this, RainbowActivity.class));
  }

  @OnClick(R.id.btn_vlayout) public void vlayout(View view) {
    startActivity(new Intent(this, VLayoutActivity.class));
  }

  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      moveTaskToBack(false);
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }
}
