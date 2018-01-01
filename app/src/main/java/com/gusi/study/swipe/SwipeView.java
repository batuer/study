package com.gusi.study.swipe;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.gusi.study.R;

/**
 * Created by batue on 2018/1/1.
 */

public class SwipeView extends ConstraintLayout implements View.OnClickListener {

  private ImageView mIvFinger;
  private int time = 0;
  private View mView;
  private int mHeight;

  private ObjectAnimator mAnimator;

  public SwipeView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override public void onGlobalLayout() {
        time++;
        if (time == 2) {
          //int height = getHeight();
          //int width = getWidth();
          ////
          mHeight = mIvFinger.getHeight();
          ////int width = mIvFinger.getWidth();
          ////400:--:400
          //Log.w("FireSwipeView", "onGlobalLayout(): 30:" + height + ":--:" + width);
          //float x = mIvFinger.getX();
          //340.0:-:730.0
          //Log.w("FireSwipeView", "onGlobalLayout(): 33:" + x + ":-:" + y);
          startAnimation();
        }
      }
    });
  }

  private void startAnimation() {
    mAnimator = ObjectAnimator.ofFloat(mView, "translationY", 20, mHeight - 20, 20);
    mAnimator.setDuration(3000);
    mAnimator.setRepeatCount(ValueAnimator.INFINITE);
    mAnimator.setInterpolator(new LinearInterpolator());
    mAnimator.start();
  }

  public void resume() {
    if (mAnimator == null) {
      startAnimation();
    } else {
      mAnimator.start();
    }
  }

  public void stop() {
    if (mAnimator != null && mAnimator.isRunning()) {
      mAnimator.cancel();
    }
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    mIvFinger = (ImageView) findViewById(R.id.iv_finger);
    mView = findViewById(R.id.view_swipe);
    findViewById(R.id.btn_stop).setOnClickListener(this);
    findViewById(R.id.btn_resume).setOnClickListener(this);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_stop:
        stop();
        break;
      case R.id.btn_resume:
        resume();
        break;
    }
  }
}
