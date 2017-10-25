package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice01ClipRectView extends View {
  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;

  public Practice01ClipRectView(Context context) {
    super(context);
  }

  public Practice01ClipRectView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Practice01ClipRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  {
    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    int width = bitmap.getWidth();
    int left = (getWidth() - width) / 2;
    int height = bitmap.getHeight();
    int top = (getHeight() - height) / 2;

    //没效果
    //canvas.drawBitmap(bitmap, left, top, paint);

    canvas.save();
    //先裁剪 ,在绘图
    canvas.clipRect(left, top + height / 2, left + width, top + height);
    canvas.drawBitmap(bitmap, left, top, paint);
    canvas.restore();
  }
}
