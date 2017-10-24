package com.gusi.study.flippertv;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import com.gusi.study.R;

/**
 * 作者：${ylw} on 2017-10-24 13:52
 *
 * @author LC
 */
public class FlipperTextView extends TextView {

  private AnimationSet mInAnim;
  private AnimationSet mOutAnim;
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

    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FlipperTextView);
    int standingTime = array.getInteger(R.styleable.FlipperTextView_standing_time, 3000);
    mTexts = array.getTextArray(R.styleable.FlipperTextView_texts);
    int animTime = array.getInteger(R.styleable.FlipperTextView_anim_time, 1000);
    array.recycle();

    int relativeToSelf = Animation.RELATIVE_TO_SELF;
    Animation inTranAnim =
        new TranslateAnimation(relativeToSelf, 0, relativeToSelf, 0, relativeToSelf, 1.0f,
            relativeToSelf, 0);
    Animation outTranAnim =
        new TranslateAnimation(relativeToSelf, 0, relativeToSelf, 0, relativeToSelf, 0,
            relativeToSelf, -1.0f);

    //AlphaAnimation inAlphaAnim = new AlphaAnimation(0.0f, 1.0f);
    //AlphaAnimation outAlphaAnim = new AlphaAnimation(1.0f, 0.0f);

    mInAnim = new AnimationSet(true);
    mOutAnim = new AnimationSet(true);

    //mInAnim.addAnimation(inAlphaAnim);
    mInAnim.addAnimation(inTranAnim);
    //mOutAnim.addAnimation(outAlphaAnim);
    mOutAnim.addAnimation(outTranAnim);

    mInAnim.setDuration(animTime);
    mOutAnim.setDuration(animTime);
    mInAnim.setInterpolator(new LinearInterpolator());
    mOutAnim.setInterpolator(new LinearInterpolator());

    //AnimationSet animationSet = new AnimationSet(true);

    mTimeOut = Math.abs(standingTime + animTime);
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
    stopAnimation();
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
