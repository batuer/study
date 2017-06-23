package com.gusi.study.piechart;

import android.support.annotation.NonNull;
import com.github.lzyzsd.randomcolor.RandomColor;

/**
 * 作者：${ylw} on 2017-06-23 10:27
 */
public class Entry implements IPie {
  private String label;
  private int value;
  private int color;
  private float angle;

  public Entry(String label, int value) {
    this.label = label;
    this.value = value;
    this.color = new RandomColor().randomColor();
  }

  @Override public String getLabel() {
    return label;
  }

  @Override public int getValue() {
    return value;
  }

  @Override public int getColor() {
    return color;
  }

  @Override public float getAngle() {
    return angle;
  }

  @Override public void setAngle(float angle) {
    //return angle;
    this.angle = angle;
  }

  @Override public int compareTo(@NonNull Object o) {
    IPie iPie = (IPie) o;
    if (value > iPie.getValue()) {
      return -1;
    }
    if (value < iPie.getValue()) {
      return 1;
    }
    return 0;
  }
}
