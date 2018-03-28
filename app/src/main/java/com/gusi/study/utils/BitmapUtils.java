package com.gusi.study.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.View;

/**
 * @Author ylw  2018-02-26 18:25
 */
public class BitmapUtils {
  /**
   * 获取一个 View 的缓存视图
   */
  public static Bitmap getCacheBitmapFromView(View view) {
    final boolean drawingCacheEnabled = true;
    view.setDrawingCacheEnabled(drawingCacheEnabled);
    view.buildDrawingCache(drawingCacheEnabled);
    final Bitmap drawingCache = view.getDrawingCache();
    Bitmap bitmap;
    if (drawingCache != null) {
      bitmap = Bitmap.createBitmap(drawingCache);
      view.setDrawingCacheEnabled(false);
    } else {
      bitmap = null;
    }
    return bitmap;
  }

  /**
   * 选择变换
   *
   * @param origin 原图
   * @param alpha 旋转角度，可正可负
   * @return 旋转后的图片
   */
  public static Bitmap rotateBitmap(Bitmap origin, float alpha) {
    if (origin == null) {
      return null;
    }
    int width = origin.getWidth();
    int height = origin.getHeight();
    Matrix matrix = new Matrix();
    matrix.setRotate(alpha);
    // 围绕原地进行旋转
    Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
    if (newBM.equals(origin)) {
      return newBM;
    }
    origin.recycle();
    return newBM;
  }
}
