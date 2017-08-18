package com.gusi.study.loading;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.gusi.study.R;
import com.orhanobut.logger.Logger;

/**
 * 作者：${ylw} on 2017-08-18 14:28
 */
public class LoadingView extends FrameLayout {
  public LoadingView(@NonNull Context context) {
    this(context, null);
  }

  public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    initView();
  }

  private void initView() {
    inflate(getContext(), R.layout.layout_loading_view, this);
    setHapticFeedbackEnabled(true);
  }

  public void show() {
    Activity context = (Activity) getContext();
    ViewGroup decorView = (ViewGroup) context.getWindow().getDecorView();
    decorView.addView(this);
    //context.back
  }

  /**
   * important
   */
  @Override public boolean dispatchTouchEvent(MotionEvent ev) {
    Logger.w("-----dispatchTouchEvent------");
    return true;
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    Logger.w("--------onInterceptTouchEvent---------");
    return true;
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    Logger.w("----onTouch---------");
    return true;
  }


  @Override public boolean dispatchKeyEvent(KeyEvent event) {
    if (getVisibility() == VISIBLE && event.getKeyCode() == KeyEvent.KEYCODE_BACK) return true;
    return super.dispatchKeyEvent(event);
  }
}
