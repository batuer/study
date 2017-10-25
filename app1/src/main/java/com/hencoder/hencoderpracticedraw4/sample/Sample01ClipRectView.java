package com.hencoder.hencoderpracticedraw4.sample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.hencoder.hencoderpracticedraw4.R;

public class Sample01ClipRectView extends View {
  TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
  TextPaint paint1 = new TextPaint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;

  public Sample01ClipRectView(Context context) {
    super(context);
  }

  public Sample01ClipRectView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Sample01ClipRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  {
    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    paint.setColor(Color.BLACK);
    paint.setTextSize(60);
    paint1.setColor(Color.RED);
    paint1.setTextSize(60);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    int left = (getWidth() - bitmap.getWidth()) / 2;
    int top = (getHeight() - bitmap.getHeight()) / 2;

    canvas.save();
    canvas.clipRect(left + 50, top + 50, left + 300, top + 200);
    canvas.drawBitmap(bitmap, left, top, paint);
    canvas.restore();

    //String text = "热点";
    //
    //Rect rect = new Rect();
    //paint.getTextBounds(text, 0, text.length(), rect);
    //
    //int width = rect.width();
    //int x = (getWidth() - width) / 2;
    //int height = rect.height();
    //int y = (getHeight() - height) / 2;
    //canvas.drawColor(Color.WHITE);
    //canvas.drawText(text, x, y, paint);
    //
    //canvas.save();
    //canvas.clipRect(x + width / 2 , 0, x + width + 2, getHeight() );
    //canvas.drawText(text, x, y, paint1);
    //canvas.restore();
  }
}
