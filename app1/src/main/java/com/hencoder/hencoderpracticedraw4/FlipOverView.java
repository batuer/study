package com.hencoder.hencoderpracticedraw4;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

/**
 * 作者：${ylw} on 2017-11-03 14:43
 */
public class FlipOverView extends LinearLayout {
  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Camera camera = new Camera();
  int degree;
  ObjectAnimator animator = ObjectAnimator.ofInt(this, "degree", 0, 180);

  public FlipOverView(Context context) {
    super(context);
  }

  public FlipOverView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FlipOverView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  {

    animator.setDuration(2500);
    animator.setInterpolator(new LinearInterpolator());
    animator.setRepeatCount(ValueAnimator.INFINITE);
    animator.setRepeatMode(ValueAnimator.REVERSE);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    animator.start();
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    animator.end();
  }

  @SuppressWarnings("unused") public void setDegree(int degree) {
    this.degree = degree;
    invalidate();
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    //super.dispatchDraw(canvas);
    int bitmapWidth = getWidth();
    int bitmapHeight = getHeight();
    int centerX = getWidth() / 2;
    int centerY = getHeight() / 2;
    int x = centerX - bitmapWidth / 2;
    int y = centerY - bitmapHeight / 2;

    // 第一遍绘制：上半部分
    canvas.save();
    canvas.clipRect(0, 0, getWidth(), centerY);
    super.dispatchDraw(canvas);
    canvas.restore();

    // 第二遍绘制：下半部分
    canvas.save();

    if (degree < 90) {
      canvas.clipRect(0, centerY, getWidth(), getHeight());
    } else {
      canvas.clipRect(0, 0, getWidth(), centerY);
    }
    camera.save();
    camera.rotateX(degree);
    canvas.translate(centerX, centerY);
    camera.applyToCanvas(canvas);
    canvas.translate(-centerX, -centerY);
    camera.restore();

    super.dispatchDraw(canvas);
    canvas.restore();
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }
}
