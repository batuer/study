package com.gusi.study.utils;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.Log;
import com.gusi.study.R;
import com.orhanobut.logger.Logger;

/**
 * 作者：${ylw} on 2017-09-13 14:17
 */
public class KeyUtils {
  private static final String TAG = "Study";

  private Context mContext;//上下文对象
  private KeyboardView mKeyboardView;//这个主角怎么能丢？
  private Keyboard mKeyboard;//好吧，其实他也是主角

  /**
   * 必须activity作为上下文对像 *
   */
  public KeyUtils(Context context) {
    mContext = context;
    //初始化键盘布局，下面在放进 KeyBoardView里面去。
    mKeyboard = new Keyboard(mContext, R.xml.number);
    //配置keyBoardView
    try {
      mKeyboardView = (KeyboardView) ((Activity) context).findViewById(R.id.key_board);
      mKeyboardView.setKeyboard(mKeyboard); //装甲激活~ 咳咳…
      mKeyboardView.setPreviewEnabled(false);   //这个是，效果图按住是出来的预览图。

      //设置监听，不设置的话会报错。监听放下面了。
      mKeyboardView.setOnKeyboardActionListener(new Listener());
    } catch (Exception e) {
      Logger.e(e.toString());
    }
  }

  private class Listener implements KeyboardView.OnKeyboardActionListener {
    @Override public void onPress(int primaryCode) {
      Log.w(TAG, ":--onPress:" + primaryCode);
    }

    @Override public void onRelease(int primaryCode) {
      Log.w(TAG, ":--onRelease:" + primaryCode);
    }

    @Override public void onKey(int primaryCode, int[] keyCodes) {
      Log.w(TAG, ":--onKey:" + primaryCode);
    }

    @Override public void onText(CharSequence text) {
      Log.w(TAG, ":--onText:" + text);
    }

    @Override public void swipeLeft() {
      Log.w(TAG, ":--onPress:");
    }

    @Override public void swipeRight() {
      Log.w(TAG, ":--swipeRight:");
    }

    @Override public void swipeDown() {
      Log.w(TAG, ":--swipeDown:");
    }

    @Override public void swipeUp() {
      Log.w(TAG, ":--swipeUp:");
    }
  }
}
