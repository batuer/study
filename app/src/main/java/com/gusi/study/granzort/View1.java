package com.gusi.study.granzort;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import com.gusi.study.R;

/**
 * @Author ylw  2017-12-28 18:31
 */
public class View1 extends View {
  private Paint mPaint;

  public View1(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setStrokeWidth(4);
    mPaint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeCap(Paint.Cap.SQUARE);
    mPaint.setStrokeJoin(Paint.Join.ROUND);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    Path path = new Path();
    Path path2 = new Path();
    path.addCircle(200, 200, 100, Path.Direction.CCW);
    path2.addRect(200, 200, 300, 300, Path.Direction.CCW);
    path.op(path2, Path.Op.UNION);
    canvas.drawPath(path, mPaint);

  }
}
