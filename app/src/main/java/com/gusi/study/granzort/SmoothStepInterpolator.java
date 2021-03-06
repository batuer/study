package com.gusi.study.granzort;

import android.util.Log;
import android.view.animation.Interpolator;

/**
 * 作者：${ylw} on 2017-09-19 10:26
 */
public class SmoothStepInterpolator implements Interpolator {

  @Override public float getInterpolation(float input) {
    //x * x * (3 - 2 * x)
    float v = input * input * (3 - 2 * input);
    mListener.coordinate(input, v);
    return v;
  }

  public SmoothStepInterpolator(CoordinateListener listener) {
    mListener = listener;
  }

  private CoordinateListener mListener;

  public interface CoordinateListener {
    void coordinate(float x, float y);
  }
}
