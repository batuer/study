package com.gusi.study.utils;

import android.text.TextUtils;

/**
 * 作者：${ylw} on 2017-05-09 17:45
 * 查找字符串中汉字数量
 */
public class HanziUtils {
  //public static int getHanziNum(String content) {
  //  String reg = "[^\u4e00-\u9fa5]";
  //  return content.replaceAll(reg, "").length();
  //}

  //public static int getByteLength(String content) {
  //  if (TextUtils.isEmpty(content)) return 0;
  //  int length = content.length();
  //  int hanziNum = getHanziNum(content);
  //  return length + hanziNum;
  //}
  public static int getByteLength(String content, boolean bigFont) {
    if (TextUtils.isEmpty(content)) return 0;

    int length = content.length();
    int hanziNum = getHanziNum(content);
    return (length + hanziNum) * (bigFont ? 2 : 1);
  }

  public static int getHanziNum(String content) {
    if (TextUtils.isEmpty(content)) return 0;
    int num = 0;
    for (char c : content.toCharArray()) {
      if (isChinese(c)) {
        num++;
      }
    }
    return num;
  }

  public static boolean isChinese(char c) {
    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
    if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
        || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
        || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
        || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
        || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
        || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
      return true;
    }
    return false;
  }
}
