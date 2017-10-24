package com.gusi.study.flippertv;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.gusi.study.R;

/**
 * 作者：${ylw} on 2017-10-24 13:52
 *
 * @author LC
 */
public class FlipperTextView extends TextView {

  private Animation mInAnim;
  private Animation mOutAnim;
  private CharSequence[] mTexts;
  private int mTimeOut;
  private boolean mIsShow = false;
  private boolean mIsStop = false;
  private int mPosition;

  public FlipperTextView(Context context) {
    this(context, null);
  }

  public FlipperTextView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public FlipperTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public FlipperTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    mInAnim = AnimationUtils.loadAnimation(context, R.anim.flipper_in);
    mOutAnim = AnimationUtils.loadAnimation(context, R.anim.flipper_out);

    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FlipperTextView);
    int standingTime = array.getInteger(R.styleable.FlipperTextView_standing_time, 3000);
    mTexts = array.getTextArray(R.styleable.FlipperTextView_texts);
    array.recycle();

    mTimeOut = (int) Math.abs(standingTime + mInAnim.getDuration());
  }

  @Override public void draw(Canvas canvas) {
    super.draw(canvas);
    String text = getText().toString();

    TextPaint paint = getPaint();
    Rect rect = new Rect();
    paint.getTextBounds(text, 0, text.length(), rect);

    Log.w("Fire", getHeight() + "--draw--" + text + ":--:" + rect.height());
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    pause();
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    resume();
  }

  private void resume() {
    if (mTexts == null || mTexts.length == 0) return;
    mIsShow = true;
    startAnimation();
  }

  private void pause() {
  }

  @Override public void startAnimation(Animation animation) {
    if (mIsShow && !mIsStop) {
      super.startAnimation(animation);
    }
  }

  protected void startAnimation() {
    setText(mTexts[mPosition]);
    startAnimation(mInAnim);
    postDelayed(mCircleRunnable, mTimeOut);
  }

  private void stopAnimation() {
    removeCallbacks(mCircleRunnable);
    Animation animation = getAnimation();
    if (animation != null) {
      animation.cancel();
    }
  }

  private Runnable mCircleRunnable = new Runnable() {
    @Override public void run() {
      startAnimation(mOutAnim);
      Animation animation = getAnimation();
      if (animation != null) {
        animation.setAnimationListener(mAnimListener);
      }
    }
  };
  private Animation.AnimationListener mAnimListener = new Animation.AnimationListener() {
    @Override public void onAnimationStart(Animation animation) {

    }

    @Override public void onAnimationEnd(Animation animation) {
      if (mIsShow) {
        mPosition = mPosition == mTexts.length - 1 ? 0 : mPosition + 1;
        startAnimation();
      }
    }

    @Override public void onAnimationRepeat(Animation animation) {
    }
  };
}
