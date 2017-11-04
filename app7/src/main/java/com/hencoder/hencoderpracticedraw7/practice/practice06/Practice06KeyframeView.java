package com.hencoder.hencoderpracticedraw7.practice.practice06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.util.Random;

import static com.hencoder.hencoderpracticedraw7.Utils.dpToPixel;

public class Practice06KeyframeView extends View {
  final float radius = dpToPixel(80);

  float progress = 0;
  RectF arcRectF = new RectF();

  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

  public Practice06KeyframeView(Context context) {
    super(context);
  }

  public Practice06KeyframeView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Practice06KeyframeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  {
    paint.setTextSize(dpToPixel(40));
    paint.setTextAlign(Paint.Align.CENTER);
  }

  public float getProgress() {
    return progress;
  }

  public void setProgress(float progress) {
    this.progress = progress;
    invalidate();
  }

  @Override public void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    float centerX = getWidth() / 2;
    float centerY = getHeight() / 2;

    //paint.setColor(Color.parseColor("#E91E63"));

    paint.setColor(Color.parseColor(getRandomColor()));
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeCap(Paint.Cap.ROUND);
    paint.setStrokeWidth(dpToPixel(15));
    arcRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
    canvas.drawArc(arcRectF, 135, progress * 3.599f, false, paint);

    paint.setColor(Color.WHITE);
    paint.setStyle(Paint.Style.FILL);
    canvas.drawText((int) progress + "%", centerX, centerY - (paint.ascent() + paint.descent()) / 2,
        paint);
  }

  private String getRandomColor() {
    //红色
    String red;
    //绿色
    String green;
    //蓝色
    String blue;
    //生成随机对象
    Random random = new Random();
    //生成红色颜色代码
    red = Integer.toHexString(random.nextInt(256)).toUpperCase();
    //生成绿色颜色代码
    green = Integer.toHexString(random.nextInt(256)).toUpperCase();
    //生成蓝色颜色代码
    blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

    //判断红色代码的位数
    red = red.length() == 1 ? "0" + red : red;
    //判断绿色代码的位数
    green = green.length() == 1 ? "0" + green : green;
    //判断蓝色代码的位数
    blue = blue.length() == 1 ? "0" + blue : blue;
    //生成十六进制颜色值
    return "#" + red + green + blue;
  }
}
