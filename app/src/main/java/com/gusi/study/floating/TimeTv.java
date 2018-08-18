package com.gusi.study.floating;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @Author ylw  2018-01-05 16:00
 */
public class TimeTv extends TextView {
  public TimeTv(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mHandler.sendEmptyMessageDelayed(0, 1000);
  }

  Handler mHandler = new Handler() {
    @Override public void handleMessage(Message msg) {
      super.handleMessage(msg);
      int i = Integer.valueOf(getText().toString()) + 1;
      setText("" + i);
      mHandler.sendEmptyMessageDelayed(0, 1000);
    }
  };

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    mHandler.removeCallbacksAndMessages(null);
  }
}
