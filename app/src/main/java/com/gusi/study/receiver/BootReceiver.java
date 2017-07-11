package com.gusi.study.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.orhanobut.logger.Logger;

public class BootReceiver extends BroadcastReceiver {

  @Override public void onReceive(Context context, Intent intent) {
    // TODO: This method is called when the BroadcastReceiver is receiving
    // an Intent broadcast.
    //throw new UnsupportedOperationException("Not yet implemented");
    Logger.w(intent.getAction());
  }
}
