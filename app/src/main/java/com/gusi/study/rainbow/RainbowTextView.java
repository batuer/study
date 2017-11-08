package com.gusi.study.rainbow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Date: 2017-11-07 13:48
 *
 * @author ylw
 */
public class RainbowTextView extends TextView {

  private LinearGradient mGradient;
  private int[] mGradientColors = {
      Color.rgb(255, 227, 132), Color.rgb(64, 224, 205), Color.rgb(0, 255, 255),
      Color.rgb(153, 51, 250), Color.rgb(218, 112, 214)
  };

  public RainbowTextView(Context context) {
    super(context);
  }

  public RainbowTextView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public RainbowTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
  }

  @Override protected void onDraw(Canvas canvas) {
    Layout layout = getLayout();
    int height = layout.getHeight();
    mGradient = new LinearGradient(0, 0, layout.getWidth(), height, mGradientColors, null,
        Shader.TileMode.REPEAT);

    TextPaint paint = getPaint();
    paint.setShader(mGradient);
    super.onDraw(canvas);

    exchangeOrder();
  }


  private void exchangeOrder() {
    if (isAttachedToWindow()) {
      postDelayed(new Runnable() {
        @Override public void run() {
          invalidate();
        }
      }, 1000);

      int[] exchangeColors = new int[mGradientColors.length];
      for (int len = mGradientColors.length, i = 0; i < len; i++) {
        exchangeColors[i] = mGradientColors[(i + 1) == len ? 0 : (i + 1)];
      }
      mGradientColors = exchangeColors;
    }
  }
}
