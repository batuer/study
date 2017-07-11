package com.gusi.study.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 作者：${ylw} on 2017-07-08 10:43
 */
public class MyDrawable extends BitmapDrawable {

  @Override public void draw(@NonNull Canvas canvas) {

  }

  @Override public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

  }

  @Override public void setColorFilter(@Nullable ColorFilter colorFilter) {

  }

  @Override public int getOpacity() {
    return PixelFormat.TRANSLUCENT;
  }
}
