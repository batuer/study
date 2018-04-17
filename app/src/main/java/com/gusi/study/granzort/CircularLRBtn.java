package com.gusi.study.granzort;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * @Author ylw  2018-04-12 17:39
 */
public class CircularLRBtn extends Button {
  public CircularLRBtn(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int height = getHeight();
    int width = getWidth();

    if (height > 0 && width > 0) {
      float radius = (float) height / 2;
      Path path = new Path();
      path.moveTo(radius,0);
      path.lineTo(width - radius,0);
      //path.addArc(width - height ,0,width,0,);
      RectF rectF = new RectF(width - height, 0, width, height);
      path.arcTo(rectF,270,180,false);
      path.lineTo(radius,height);
      RectF rect = new RectF(0, 0, height, height);
      path.arcTo(rect,90,180,false);


      Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
      paint.setStrokeWidth(5);
      paint.setColor(Color.RED);
      canvas.drawPath(path,paint);
    }
  }
}
