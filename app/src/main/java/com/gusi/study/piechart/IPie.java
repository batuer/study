package com.gusi.study.piechart;

/**
 * 作者：${ylw} on 2017-06-23 10:23
 */
public interface IPie extends Comparable {

  String getLabel();

  int getValue();

  int getColor();

  float getAngle();
  void setAngle(float angle);
}
