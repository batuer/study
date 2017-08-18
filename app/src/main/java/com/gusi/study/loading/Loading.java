package com.gusi.study.loading;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * 作者：${ylw} on 2017-08-18 14:30
 */
public final class Loading {
  private Loading() {

  }

  //public static Alerter create(@NonNull final Activity activity) {
  //  if (activity == null) {
  //    throw new IllegalArgumentException("Activity cannot be null!");
  //  }
  //
  //  final Alerter alerter = new Alerter();
  //
  //  //Clear Current Alert, if one is Active
  //  Alerter.clearCurrent(activity);
  //
  //  alerter.setActivity(activity);
  //  alerter.setLoadingView(new Alert(activity));
  //
  //  return alerter;
  //}
  public static LoadingView create(@NonNull final Activity activity) {
    if (activity == null) {
      throw new IllegalArgumentException("Activity cannot be null!");
    }

    final LoadingView loadingView = new LoadingView(activity);

    //Clear Current Alert, if one is Active
    //Alerter.clearCurrent(activity);

    //loadingView.setActivity(activity);
    return loadingView;
  }


}
