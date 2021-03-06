package com.gusi.study;

import android.app.Application;
import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 作者：${ylw} on 2017-06-23 15:19
 */
public class App extends Application {
  @Override public void onCreate() {
    super.onCreate();

    initLog();
    Utils.init(this);
  }

  private void initLog() {
    FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
        .showThreadInfo(true)  // (Optional) Whether to add thread info or not. Default true
        .methodCount(3)         // (Optional) How many method line to add. Default 2
        //.methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
        //.logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
        .tag("Study")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
        .build();
    Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
  }
}
