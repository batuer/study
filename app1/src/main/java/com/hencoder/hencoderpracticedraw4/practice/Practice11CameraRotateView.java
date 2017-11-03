package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.hencoder.hencoderpracticedraw4.R;

public class Practice11CameraRotateView extends View {
  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;
  Point point1 = new Point(200, 200);
  Point point2 = new Point(600, 200);
  Camera mCamera = new Camera();

  public Practice11CameraRotateView(Context context) {
    super(context);
  }

  public Practice11CameraRotateView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Practice11CameraRotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  {
    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int x = point1.x;
    int y = point1.y;
    //int left = x, right = x + bitmap.getWidth();
    //int bottom = y, top = y - bitmap.getHeight();
    //
    //Matrix matrix = new Matrix();
    //float[] pointsSrc = { left, top, right, top, left, bottom, right, bottom };
    //float[] pointsDst = {
    //    left - 10, top + 50, right + 120, top - 90, left + 20, bottom + 30, right + 20, bottom + 60
    //};
    //
    //matrix.reset();
    //matrix.setPolyToPoly(pointsSrc, 0, pointsDst, 0, 4);
    //
    //canvas.save();
    //canvas.concat(matrix);
    //canvas.drawBitmap(bitmap, x, y, paint);
    //canvas.restore();


    canvas.save();
    mCamera.save();
    mCamera.rotateX(30);
    mCamera.applyToCanvas(canvas);
    mCamera.restore();
    canvas.drawBitmap(bitmap, x, y, paint);
    canvas.restore();
    //canvas.drawBitmap(bitmap, x, y, paint);


    //canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
  }
}
