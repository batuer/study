package com.gusi.study;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import butterknife.OnClick;
import com.gusi.study.Lottie.LottieActivity;
import com.gusi.study.ScrollTv.ScrollTvActivity;
import com.gusi.study.base.BaseActivity;
import com.gusi.study.bean.LoginInfo;
import com.gusi.study.bigrcv.BigRcvActivity;
import com.gusi.study.canvas.CanvasActivity;
import com.gusi.study.constraint.Constraint1Activity;
import com.gusi.study.constraint.ConstraintActivity;
import com.gusi.study.dragshadow.DragShadowActivity;
import com.gusi.study.drawable.DrawableActivity;
import com.gusi.study.flippertv.FlipperTvActivity;
import com.gusi.study.floating.FloatingActivity;
import com.gusi.study.flow.FlowActivity;
import com.gusi.study.formlayout.FormActivity;
import com.gusi.study.formlayout.horizontalweight.HorizontalWeightActivity;
import com.gusi.study.granzort.GranzortActivity;
import com.gusi.study.keyboard.KeyBoardActivity;
import com.gusi.study.loading.LoadingActivity;
import com.gusi.study.nestedscroll.NestedScroll1Activity;
import com.gusi.study.nestedscroll.ScrollingActivity;
import com.gusi.study.piechart.PieChartActivity;
import com.gusi.study.rainbow.RainbowActivity;
import com.gusi.study.screenshot.ScreenShotActivity;
import com.gusi.study.span.SpanActivity;
import com.gusi.study.swipe.SwipeActivity;
import com.gusi.study.threadlocal.ThreadLocalActivity;
import com.gusi.study.today.TodayActivity;
import com.gusi.study.transparent.TransparentActivity;
import com.gusi.study.utils.HanziUtils;
import com.gusi.study.vlayout.VLayoutActivity;

/**
 * @author ylw   2017-11-14 16:03
 */
public class MainActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolBar(mToolbar, true, "Study");
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState,
      @Nullable PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
  }

  @Override protected int getLayout() {
    return R.layout.activity_main;
  }

  @OnClick(R.id.btn_pie_chart) public void pieChart(View view) {
    startActivity(new Intent(this, PieChartActivity.class));
    String substring = "12".substring(0, 1);
    Log.w("Fire", "MainActivity:51行:" + substring);
    getSubIndex("12啊啊方", 12, 24, 10 * 12);
    getSubIndex("1大方", 12, 24, 10 * 12);
    Pair<Integer, Integer> pair = getSubIndex("11212啊啊大方", 12, 24, 10 * 12);
    "".substring(0, 0);
  }

  private Pair<Integer, Integer> getSubIndex(String content, int charUnit, int hanziUnit,
      int limit) {
    char[] chars = content.toCharArray();
    int allLength = 0;
    for (int len = chars.length, i = 0; i < len; i++) {
      char c = chars[i];
      allLength += HanziUtils.isChinese(c) ? hanziUnit : charUnit;
      if (allLength >= limit) {
        return new Pair<>(allLength, i);
      }
    }
    return new Pair<>(allLength, content.length());
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

  @OnClick(R.id.btn_constraint1) public void constraint1(View view) {
    startActivity(new Intent(this, Constraint1Activity.class));
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
    startActivity(new Intent(this, NestedScroll1Activity.class));
  }

  @OnClick(R.id.btn_scroll) public void scroll(View view) {
    startActivity(new Intent(this, ScrollingActivity.class));
  }

  @OnClick(R.id.btn_rainbow) public void rainbow(View view) {
    startActivity(new Intent(this, RainbowActivity.class));
  }

  @OnClick(R.id.btn_vlayout) public void vlayout(View view) {
    startActivity(new Intent(this, VLayoutActivity.class));
  }

  @OnClick(R.id.btn_span) public void span(View view) {
    startActivity(new Intent(this, SpanActivity.class));
  }

  @OnClick(R.id.btn_threadlocal) public void threadLocal(View view) {
    startActivity(new Intent(this, ThreadLocalActivity.class));
  }

  @OnClick(R.id.btn_swipe) public void Swipe(View view) {
    startActivity(new Intent(this, SwipeActivity.class));
  }

  @OnClick(R.id.btn_transparent) public void Transparent(View view) {
    startActivity(new Intent(this, TransparentActivity.class));
  }

  @OnClick(R.id.btn_big_rcv) public void bigRcvClick() {
    startActivity(new Intent(this, BigRcvActivity.class));
  }

  @OnClick(R.id.btn_canvas) public void canvasClick() {
    startActivity(new Intent(this, CanvasActivity.class));
  }

  @OnClick(R.id.btn_lottie) public void lottie() {
    startActivity(new Intent(this, LottieActivity.class));
  }

  @OnClick(R.id.btn_drag_shadow) public void dragShadow() {
    startActivity(new Intent(this, DragShadowActivity.class));
  }

  @OnClick(R.id.btn_screen_shot) public void screenShot() {
    startActivity(new Intent(this, ScreenShotActivity.class));
  }

  @OnClick(R.id.btn_horizontal_weight) public void horizontalWeight() {
    startActivity(new Intent(this, HorizontalWeightActivity.class));
    LoginInfo.Login login =
        LoginInfo.Login.newBuilder().setAccount("Account").setPassword("PassWord").build();
    Log.w("FireMainActivity", ": 182:" + login.getAccount() + ":" + login.getPassword());
  }

  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      moveTaskToBack(false);
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }
}
