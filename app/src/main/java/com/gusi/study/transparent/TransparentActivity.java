package com.gusi.study.transparent;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import com.gusi.study.R;

/**
 * @Author ylw  2018-01-08 11:52
 */
public class TransparentActivity extends AppCompatActivity {
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transparent);
    //1.全屏
    //View decorView = getWindow().getDecorView();
    //
    //int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
    //decorView.setSystemUiVisibility(option);
    //
    //ActionBar actionBar = getSupportActionBar();
    //actionBar.hide();

    //2. 状态栏透明
    //if (Build.VERSION.SDK_INT >= 21) {
    //
    //  View decorView = getWindow().getDecorView();
    //
    //  int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
    //  //int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN ;
    //  decorView.setSystemUiVisibility(option);
    //  getWindow().setStatusBarColor(Color.TRANSPARENT);
    //}
    //
    //ActionBar actionBar = getSupportActionBar();
    //actionBar.hide();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = getWindow();
      int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
      window.getDecorView().setSystemUiVisibility(option);
      window.setStatusBarColor(Color.TRANSPARENT);
      window.setNavigationBarColor(Color.TRANSPARENT);
    }
    getStatusBarHeight();
    getActionBarHeight();
  }

  //@Override public void onWindowFocusChanged(boolean hasFocus) {
  //  super.onWindowFocusChanged(hasFocus);
  //  Log.w("Fire", "TransparentActivity:46行:" + hasFocus);
  //
  //  if (hasFocus && Build.VERSION.SDK_INT >= 19) {
  //    View decorView = getWindow().getDecorView();
  //    decorView.setSystemUiVisibility(
  //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
  //            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
  //            | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
  //  }
  //}

  @Override public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    getStatusBarHeight();
    getActionBarHeight();
  }

  private void getStatusBarHeight() {
    /**
     * 获取状态栏高度——方法1
     * */
    int statusBarHeight1 = -1;
    //获取status_bar_height资源的ID
    int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      //根据资源ID获取响应的尺寸值
      statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
    }
    Log.w("Fire", "TransparentActivity:76行:" + statusBarHeight1);

    /**
     * 获取状态栏高度——方法2
     * */
    int statusBarHeight2 = -1;
    try {
      Class<?> clazz = Class.forName("com.android.internal.R$dimen");
      Object object = clazz.newInstance();
      int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
      statusBarHeight2 = getResources().getDimensionPixelSize(height);
      Log.w("Fire", "TransparentActivity:87行:" + statusBarHeight2);
    } catch (Exception e) {
      Log.e("Fire", "TransparentActivity:89行:" + e.toString());
    }

    /**
     * 获取状态栏高度——方法3
     * 应用区的顶端位置即状态栏的高度
     * *注意*该方法不能在初始化的时候用
     * */
    Rect rectangle = new Rect();
    getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
    //高度为rectangle.top-0仍为rectangle.top
    Log.w("Fire", "TransparentActivity:102行:" + rectangle.top);

    /**
     * 获取状态栏高度——方法4
     * 状态栏高度 = 屏幕高度 - 应用区高度
     * *注意*该方法不能在初始化的时候用
     * */
    //屏幕
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    //应用区域
    Rect outRect1 = new Rect();
    getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
    int statusBar = dm.heightPixels - outRect1.height();  //状态栏高度=屏幕高度-应用区域高度
    Log.w("Fire", "TransparentActivity:118行:" + statusBar);
  }

  private void getActionBarHeight() {
    //屏幕
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    Log.e("Fire", "屏幕高:" + dm.heightPixels);

    //应用区域
    Rect outRect1 = new Rect();
    getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
    Log.e("Fire", "应用区顶部" + outRect1.top);
    Log.e("Fire", "应用区高" + outRect1.height());

    //View绘制区域
    Rect outRect2 = new Rect();
    getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect2);
    Log.e("Fire",
        "View绘制区域顶部-错误方法：" + outRect2.top);   //不能像上边一样由outRect2.top获取，这种方式获得的top是0，可能是bug吧
    int viewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();   //要用这种方法
    Log.e("Fire", "View绘制区域顶部-正确方法：" + viewTop);
    Log.e("Fire", "View绘制区域高度：" + outRect2.height());

    int resourceId = getResources().getIdentifier("actionBarSize", "dimen", "android");
    if (resourceId > 0) {
      int dimensionPixelSize = getResources().getDimensionPixelSize(resourceId);
      Log.w("Fire", "TransparentActivity:149行:" + dimensionPixelSize);
    }


    getActionBar().getHeight();
  }
}
