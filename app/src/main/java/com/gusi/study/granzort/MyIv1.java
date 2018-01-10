package com.gusi.study.granzort;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.gusi.study.R;

/**
 * @Author ylw  2018-01-08 17:32
 */
public class MyIv1 extends LinearLayout {

  private ImageView mIv;

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

  //@TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override protected void onDraw(Canvas canvas) {
  //
  //  int height = getHeight();
  //  int width = getWidth();
  //  Log.w("Fire", "MyIv1:75行:" + height + ":--:" + width);
  //
  //  if (height > 0 && width > 0) {
  //
  //    int radius = 40;
  //    int radius2 = 80;
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
  //    //
  //    //RectF rectF = new RectF();
  //    //rectF.left = 0;
  //    //rectF.right = width;
  //    //rectF.top = 0;
  //    //rectF.bottom = height;
  //    //path.addRoundRect(rectF, 20, 20, Path.Direction.CW);
  //
  //    canvas.save();
  //    canvas.clipPath(path);
  //    super.onDraw(canvas);
  //    canvas.restore();
  //  } else {
  //    super.onDraw(canvas);
  //  }
  //}

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    mIv = (ImageView) findViewById(R.id.iv);
  }

  @Override protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
    Log.w("Fire", "MyIv1:86行:drawChild");

    clipPath(canvas);
    boolean b = super.drawChild(canvas, child, drawingTime);
    return b;
  }

  private void clipPath(Canvas canvas) {
    int height = getHeight();
    int width = getWidth();
    Log.w("Fire", "MyIv1:75行:" + height + ":--:" + width);

    int height1 = mIv.getHeight();
    if (height > 0 && width > 0) {
      Path path = new Path();
      RectF rectF = new RectF();
      rectF.left = 0;
      rectF.right = width;
      rectF.top = 0;
      rectF.bottom = height;
      path.addRoundRect(rectF, 20, 20, Path.Direction.CW);

      //Path path1 = new Path();
      ////RectF rectF1 = new RectF();
      ////rectF1.left = 0;
      ////rectF1.right = 40;
      ////rectF1
      //
      //path1.addCircle(0, height1, 20, Path.Direction.CW);
      //
      //Path path2 = new Path();
      //path2.addCircle(width, height1, 20, Path.Direction.CW);
      //canvas.clipPath(path1, Region.Op.DIFFERENCE);

      canvas.clipPath(path);


      Paint paint = new Paint();
      paint.setStrokeWidth(4);
      paint.setColor(Color.RED);
      paint.setStyle(Paint.Style.STROKE);
      paint.setAntiAlias(true);


      canvas.drawPath(path, paint);
    }
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP) private void clipPath1(Canvas canvas) {
    int radius = 20;
    int radius2 = 20;
    int height = getHeight();
    int width = getWidth();

    Path path = new Path();

    path.moveTo(radius, 0);
    path.lineTo(width - radius, 0);
    path.arcTo(width - radius2, 0, width, radius2, 270, 90, true);

    path.lineTo(width, height - radius);
    path.arcTo(width - radius2, height - radius2, width, height, 0, 90, true);

    path.lineTo(radius, height);
    path.arcTo(0, height - radius2, radius2, height, 90, 90, true);

    path.lineTo(0, radius);
    path.arcTo(0, 0, radius2, radius2, 180, 90, true);

    //path.lineTo(radius, 0);
    //path.close();

    canvas.clipPath(path);

    Paint paint = new Paint();
    paint.setStrokeWidth(4);
    paint.setColor(Color.RED);
    paint.setStyle(Paint.Style.STROKE);
    paint.setAntiAlias(true);


    canvas.drawPath(path, paint);
  }
}
