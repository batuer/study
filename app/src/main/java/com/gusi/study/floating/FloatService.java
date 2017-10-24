package com.gusi.study.floating;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FloatService extends Service {
  public FloatService() {
  }

  @Override public IBinder onBind(Intent intent) {
    // TODO: Return the communication channel to the service.
    return null;
  }

  @Override public void onCreate() {
    super.onCreate();
    CustomViewManager.getInstance(this).showFloatViewOnWindow();
  }
}
