package com.gusi.study.granzort;

import android.view.animation.Interpolator;

/**
 * 作者：${ylw} on 2017-09-19 10:28
 */
public class SpringInterpolator implements Interpolator {
  @Override public float getInterpolation(float input) {
    //factor = 0.4
    //pow(2, -10 * x) * sin() + 1
    float factor = 0.4f;
    int PI = 1;
    return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * PI) / factor)
        + 1);
  }
}
