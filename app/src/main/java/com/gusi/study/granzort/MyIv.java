package com.gusi.study.granzort;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * @Author ylw  2018-01-08 16:35
 */
public class MyIv extends ImageView {
  public MyIv(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }



  @TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override protected void onDraw(Canvas canvas) {

    int height = getHeight();
    int width = getWidth();
    Log.w("Fire", "MyIv:24行:" + height + ":--:" + width );
    if (height > 0 && width > 0 ) {

      Path path = new Path();
      RectF rectF = new RectF();
      rectF.left = 20;
      rectF.right = width - 20;
      rectF.top = 20;
      rectF.bottom = height - 20;
      path.addRoundRect(rectF, 20, 20, Path.Direction.CW);

      canvas.save();
      canvas.clipPath(path);
      canvas.drawColor(Color.YELLOW);
      canvas.restore();

      //Paint paint = new Paint();
      //paint.setStrokeWidth(3);
      //paint.setAntiAlias(true);
      //paint.setColor(Color.RED);
      //paint.setStyle(Paint.Style.STROKE);
      //canvas.drawPath(path, paint);
    } else {
      super.onDraw(canvas);
    }
  }
}
