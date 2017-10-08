package com.gusi.study.floating;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * Created by batue on 2017/10/8.
 */

public class CustomViewManager {
  //上下文
  private Context mContext;
  //本类实例
  private static CustomViewManager instance;
  //自定义的FloatView
  private FloatView mFloatView;
  //窗口管理类
  private WindowManager mWindowManager;

  private CustomViewManager(Context context) {
    this.mContext = context;
    mFloatView = new FloatView(mContext);
    mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
  }

  /**
   * @param
   * @description 通过单例模式获取实例对象
   * @author ldm
   * @time 2016/8/17 11:59
   */
  public static CustomViewManager getInstance(Context mContext) {
    if (null == instance) {
      synchronized (CustomViewManager.class) {
        if (null == instance) {
          instance = new CustomViewManager(mContext);
        }
      }
    }
    return instance;
  }

  /**
   * @param
   * @description 在手机屏幕上显示自定义的FloatView
   * @author ldm
   * @time 2016/8/17 13:47
   */
  public void showFloatViewOnWindow() {
    WindowManager.LayoutParams parmas = new WindowManager.LayoutParams();
    parmas.width = mFloatView.getFloatWidth();
    parmas.height = mFloatView.getFloatHeight();
    //窗口图案放置位置
    parmas.gravity = Gravity.LEFT | Gravity.CENTER;
    // 如果忽略gravity属性，那么它表示窗口的绝对X位置。
    parmas.x = 0;
    //如果忽略gravity属性，那么它表示窗口的绝对Y位置。
    parmas.y = 0;
    ////电话窗口。它用于电话交互（特别是呼入）。它置于所有应用程序之上，状态栏之下。
    parmas.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
    //FLAG_NOT_FOCUSABLE让window不能获得焦点，这样用户快就不能向该window发送按键事件及按钮事件
    //FLAG_NOT_TOUCH_MODAL即使在该window在可获得焦点情况下，仍然把该window之外的任何event发送到该window之后的其他window.
    parmas.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
    // 期望的位图格式。默认为不透明。参考android.graphics.PixelFormat。
    parmas.format = PixelFormat.RGBA_8888;
    mWindowManager.addView(mFloatView, parmas);
  }
}
