package com.gusi.study.objectbox.bean;

/**
 * 作者：${ylw} on 2017-10-27 15:27
 */
public class Student {
  public String name;
  public String sex;
  public String age;
  public String id;
  public String classNo;
  public String math;
  public String english;
  public String chinese;

  public Student(String name, String sex, String age, String id, String classNo, String math, String english, String chinese) {
    this.name = name;
    this.sex = sex;
    this.age = age;
    this.id = id;
    this.classNo = classNo;
    this.math = math;
    this.english = english;
    this.chinese = chinese;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getClassNo() {
    return classNo;
  }

  public void setClassNo(String classNo) {
    this.classNo = classNo;
  }

  public String getMath() {
    return math;
  }

  public void setMath(String math) {
    this.math = math;
  }

  public String getEnglish() {
    return english;
  }

  public void setEnglish(String english) {
    this.english = english;
  }

  public String getChinese() {
    return chinese;
  }

  public void setChinese(String chinese) {
    this.chinese = chinese;
  }
}
