package com.gusi.study.granzort;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.gusi.study.R;

/**
 * 作者：${ylw} on 2017-09-19 16:50
 */
public class SpringView extends View {
  private ValueAnimator valueAnimator;
  private Paint mPaint;
  private Path mPath;

  public SpringView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    initPaint();
    initAnimator();
    valueAnimator.start();
  }

  float maxX = 0;
  float maxY = 0;
  int width;
  int height;

  private void initAnimator() {
    valueAnimator = ValueAnimator.ofFloat(0, 1).setDuration(3000);
    //valueAnimator.setInterpolator(new SpringInterpolator(new SpringInterpolator.XListener() {
    //  @Override public void getX(float x, float y) {
    //    float realX = x * width / 1;
    //    float realY = y * height / 1.3f;
    //
    //    mPath.lineTo(realX, realY);
    //    maxX = Math.max(maxX, x);
    //    maxY = Math.max(maxY, y);
    //    Log.w("StudyInter", realX + ":--:" + x + " | " + realY + ":--:" + y);
    //    invalidate();
    //  }
    //}));
    valueAnimator.setInterpolator(
        new SmoothStepInterpolator(new SmoothStepInterpolator.CoordinateListener() {
          @Override public void coordinate(float x, float y) {
            float realX = x * width / 1;
            float realY = y * height / 1.0f;

            mPath.lineTo(realX, realY);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
            Log.w("StudyInter", realX + ":--:" + x + " | " + realY + ":--:" + y);
            invalidate();
          }
        }));

    valueAnimator.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {
        width = getMeasuredWidth();
        height = getMeasuredHeight();
      }

      @Override public void onAnimationEnd(Animator animation) {
        Log.e("StudyInter", maxX + ":--:" + maxY);
      }

      @Override public void onAnimationCancel(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {

      }
    });
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //canvas.drawColor(Color.WHITE);
    canvas.drawPath(mPath, mPaint);
  }

  private void initPaint() {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setColor(getResources().getColor(R.color.other));
    mPaint.setStyle(Paint.Style.STROKE); //画笔风格  填充、描边、描边或填充
    mPaint.setStrokeWidth(5);
    mPaint.setStrokeCap(Paint.Cap.ROUND);
    mPaint.setStrokeJoin(Paint.Join.BEVEL);
    mPaint.setShadowLayer(15, 0, 0, Color.WHITE);//白色光影效果

    mPath = new Path();
    mPath.moveTo(0, 0);
  }
}
