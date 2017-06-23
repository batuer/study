package com.gusi.study.piechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 作者：${ylw} on 2017-06-23 17:45
 */
public class Fab extends FloatingActionButton {

  private  Paint mPaint;

  public Fab(Context context, AttributeSet attrs) {
    super(context, attrs);
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setAntiAlias(true);
    mPaint.setTextAlign(Paint.Align.CENTER);
    mPaint.setColor(Color.RED);
    mPaint.setTextSize(32);
    mPaint.setStrokeWidth(4);
  }

  @Override protected void onDraw(Canvas canvas) {
    Rect rect = new Rect();
    String fab = "Fab";
    mPaint.getTextBounds(fab, 0, fab.length(), rect);

    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
    int x = (getWidth()) / 2 ;

    canvas.drawText(fab, x, (getHeight() + rect.height()) / 2 , mPaint);
    canvas.drawLine(0, 20, 100, 20, mPaint);
    super.onDraw(canvas);
  }
}
