package com.hencoder.hencoderpracticedraw4.sample;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.hencoder.hencoderpracticedraw4.R;

public class Sample13CameraRotateHittingFaceView extends View {
  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;
  Point point = new Point(200, 50);
  Path mPath = new Path();

  Camera camera = new Camera();
  Matrix matrix = new Matrix();
  int degree;
  ObjectAnimator animator = ObjectAnimator.ofInt(this, "degree", 0, 360);

  public Sample13CameraRotateHittingFaceView(Context context) {
    super(context);
  }

  public Sample13CameraRotateHittingFaceView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Sample13CameraRotateHittingFaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  {
    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    Bitmap scaledBitmap =
        Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 2, bitmap.getHeight() * 2, true);
    bitmap.recycle();
    bitmap = scaledBitmap;

    animator.setDuration(5000);
    animator.setInterpolator(new LinearInterpolator());
    animator.setRepeatCount(ValueAnimator.INFINITE);

    DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
    float newZ = -displayMetrics.density * 6;
    camera.setLocation(0, 0, newZ);

    mPath.addCircle(point.x + bitmap.getWidth() / 2, point.y + bitmap.getHeight() / 2,
        bitmap.getWidth() / 2, Path.Direction.CW);
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

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    int bitmapWidth = bitmap.getWidth();
    int bitmapHeight = bitmap.getHeight();
    int centerX = point.x + bitmapWidth / 2;
    int centerY = point.y + bitmapHeight / 2;

    camera.save();
    matrix.reset();
    camera.rotateZ(degree);
    camera.getMatrix(matrix);
    camera.restore();
    matrix.preTranslate(-centerX, -centerY);
    matrix.postTranslate(centerX, centerY);
    canvas.save();
    canvas.clipPath(mPath);
    canvas.concat(matrix);
    canvas.drawBitmap(bitmap, point.x, point.y, paint);
    canvas.restore();
  }
}
