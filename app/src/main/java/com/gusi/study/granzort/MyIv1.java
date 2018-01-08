package com.gusi.study.granzort;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @Author ylw  2018-01-08 17:32
 */
public class MyIv1 extends LinearLayout {
  public MyIv1(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }
  //@TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override protected void onDraw(Canvas canvas) {
  //
  //  int height = getHeight();
  //  int width = getWidth();
  //  Log.w("Fire", "MyIv:24行:" + height + ":--:" + width);
  //  if (height > 0 && width > 0) {
  //    int radius = 20;
  //    int radius2 = 40;
  //
  //    Path path = new Path();
  //    path.moveTo(radius, 0);
  //    path.lineTo(width - radius, 0);
  //    path.addArc(width - radius2, 0, width, radius2, 270, 90);
  //
  //    path.lineTo(width, height - radius);
  //    path.addArc(width - radius2, height - radius2, width, height, 0, 90);
  //
  //    path.lineTo(radius, height);
  //    path.addArc(0, height - radius2, radius2, height, 90, 90);
  //
  //    path.lineTo(0, radius);
  //    path.addArc(0, 0, radius2, radius2, 180, 90);
  //    path.lineTo(radius, 0);
  //
  //
  //    canvas.save();
  //    canvas.clipPath(path);
  //    super.onDraw(canvas);
  //    canvas.restore();
  //
  //    Paint paint = new Paint();
  //    paint.setStrokeWidth(3);
  //    paint.setAntiAlias(true);
  //    paint.setColor(Color.RED);
  //    paint.setStyle(Paint.Style.STROKE);
  //    canvas.drawPath(path, paint);
  //  } else {
  //    super.onDraw(canvas);
  //  }
  //}

  @Override protected void onDraw(Canvas canvas) {

    int height = getHeight();
    int width = getWidth();
    Log.w("Fire", "MyIv1:75行:" + height + ":--:" + width);

    if (height > 0 && width > 0) {
      Path path = new Path();
      RectF rectF = new RectF();
      rectF.left = 0;
      rectF.right = width;
      rectF.top = 0;
      rectF.bottom = height;
      path.addRoundRect(rectF, 20, 20, Path.Direction.CW);

      canvas.save();
      canvas.clipPath(path);
      super.onDraw(canvas);
      canvas.restore();
    } else {
      super.onDraw(canvas);
    }
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    Log.w("Fire", "MyIv1:87行:dispatchDraw"  );
  }

  @Override protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
    Log.w("Fire", "MyIv1:86行:drawChild" );
    clipPath(canvas);
    return super.drawChild(canvas, child, drawingTime);
  }

  private void clipPath(Canvas canvas) {
    int height = getHeight();
    int width = getWidth();
    Log.w("Fire", "MyIv1:75行:" + height + ":--:" + width);

    if (height > 0 && width > 0) {
      Path path = new Path();
      RectF rectF = new RectF();
      rectF.left = 0;
      rectF.right = width;
      rectF.top = 0;
      rectF.bottom = height;
      path.addRoundRect(rectF, 20, 20, Path.Direction.CW);

      canvas.clipPath(path);
    }
  }
}
