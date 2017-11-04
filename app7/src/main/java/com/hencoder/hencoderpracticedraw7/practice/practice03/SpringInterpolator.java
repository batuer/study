package com.hencoder.hencoderpracticedraw7.practice.practice03;

import android.view.animation.Interpolator;

/**
 * 作者：${ylw} on 2017-09-19 10:28
 */
public class SpringInterpolator implements Interpolator {
  @Override public float getInterpolation(float input) {

    //factor = 0.4
    //pow(2, -10 * x) * sin() + 1
    float factor = 0.4f;
    float v =
        (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor)
            + 1);

    mXListener.getX(input, v);
    return v;
  }

  public SpringInterpolator(XListener XListener) {
    mXListener = XListener;
  }

  private XListener mXListener;

  public interface XListener {
    void getX(float x, float y);
  }
}
