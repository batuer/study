package com.gusi.study.granzort;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.gusi.study.R;

/**
 * @Author ylw  2017-12-28 18:00
 */
public class LineFrameLayout extends FrameLayout {
  private Paint mPaint = null;

  public LineFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setStrokeWidth(4);
    mPaint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeCap(Paint.Cap.SQUARE);
    mPaint.setStrokeJoin(Paint.Join.BEVEL);
    mPaint.setShadowLayer(1, 0, 0, Color.WHITE);//白色光影效果
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    int height = getHeight();
    int width = getWidth();
    int length = 60;
    int radius = 30;
    if (height > 0 && width > 0) {
      Path path2 = new Path();

      path2.moveTo(0, length);
      path2.lineTo(0, length - radius);
      path2.moveTo(radius, 0);
      path2.lineTo(length, 0);
      Path path1 = new Path();
      RectF oval = new RectF(0, 0, radius * 2, radius * 2);
      path1.addArc(oval, 180, 90);

      path2.op(path1, Path.Op.XOR);
      canvas.drawPath(path2,mPaint);


      Path path = new Path();
      path.moveTo(0, height - length);
      path.lineTo(0, height);
      path.lineTo(length, height);

      path.moveTo(width - length, height);
      path.lineTo(width, height);
      path.lineTo(width, height - length);

      path.moveTo(width, length);
      path.lineTo(width, 0);
      path.lineTo(width - length, 0);


      canvas.drawPath(path, mPaint);

      //Path path1 = new Path();
      //mPaint.setStrokeWidth(2);

      //canvas.drawArc(oval, 180, 90, false, mPaint);

      GradientDrawable gd = new GradientDrawable();//创建drawable
    }
  }
}
