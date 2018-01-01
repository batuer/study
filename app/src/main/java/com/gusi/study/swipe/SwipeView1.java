package com.gusi.study.swipe;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.gusi.study.R;

/**
 * Created by batue on 2018/1/2.
 */

public class SwipeView1 extends RelativeLayout implements View.OnClickListener {

  private ImageView mIv;
  private View mView1;
  private int times = 0;
  private ObjectAnimator mAnimator;

  public SwipeView1(Context context, AttributeSet attrs) {
    super(context, attrs);
    getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override public void onGlobalLayout() {

        times++;
        if (times == 2) {
          initAnimation();
          mAnimator.start();
        }
      }
    });
  }

  private void initAnimation() {
    int height = mIv.getHeight();
    mAnimator = ObjectAnimator.ofFloat(mView1, "translationY", 20, height - 20, 20);
    mAnimator.setDuration(3000);
    mAnimator.setRepeatCount(ValueAnimator.INFINITE);
    mAnimator.setInterpolator(new LinearInterpolator());
    mAnimator.start();
  }

  public void resume() {
    if (mAnimator == null) {
      initAnimation();
      mAnimator.start();
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
    mIv = (ImageView) findViewById(R.id.iv_finger1);
    mView1 = findViewById(R.id.view_swipe_1);
    findViewById(R.id.btn_stop1).setOnClickListener(this);
    findViewById(R.id.btn_resume1).setOnClickListener(this);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_stop1:
        stop();
        break;
      case R.id.btn_resume1:
        resume();
        break;
    }
  }
}
