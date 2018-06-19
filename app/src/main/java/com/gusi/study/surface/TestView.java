package com.gusi.study.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @Author ylw  2018/6/8 20:32
 */
public class TestView extends View {
    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        if (width > 0 && height > 0) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStrokeWidth(0.5f);

            int alpha = 0;
            int red = 0;
            int green = 0;
            int blue = 0;

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Log.w("Fire", "TestView:38行:" + alpha + ":" + red + ":" + green + ":" + blue);
                    paint.setColor(Color.argb(alpha, red, green, blue));
                    canvas.drawPoint(j, i, paint);
                    if (alpha < 255) {
                        alpha++;
                    } else if (red < 255) {
                        red++;
                    } else if (green < 255) {
                        green++;
                    } else if (blue < 255) {
                        blue++;
                    } else {
                        alpha = 0;
                        red = 0;
                        green = 0;
                        blue = 0;
                    }

                }
            }


        }


    }
}
