package com.gusi.study.span;

import android.util.Log;
import java.util.ArrayList;

/**
 * @Author ylw  2017-12-07 14:02
 */
public class TypeWriterSpanGroup {
  private static final boolean DEBUG = false;
  private static final String TAG = "TypeWriterSpanGroup";

  private final float mAlpha;
  private final ArrayList<MutableForegroundColorSpan> mSpans;

  private TypeWriterSpanGroup(float alpha) {
    mAlpha = alpha;
    mSpans = new ArrayList();
  }

  public void addSpan(MutableForegroundColorSpan span) {
    span.setAlpha((int) (mAlpha * 255));
    mSpans.add(span);
  }

  public void setAlpha(float alpha) {
    int size = mSpans.size();
    float total = 1.0f * size * alpha;

    if (DEBUG) Log.d(TAG, "alpha " + alpha + " * 1.0f * size => " + total);

    for (int index = 0; index < size; index++) {
      MutableForegroundColorSpan span = mSpans.get(index);

      if (total >= 1.0f) {
        span.setAlpha(255);
        total -= 1.0f;
      } else {
        span.setAlpha((int) (total * 255));
        total = 0.0f;
      }

      if (DEBUG) Log.d(TAG, "alpha span(" + index + ") => " + alpha);
    }
  }

  public float getAlpha() {
    return mAlpha;
  }

}
