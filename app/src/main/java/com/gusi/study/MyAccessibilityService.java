package com.gusi.study;

import android.accessibilityservice.AccessibilityService;
import android.support.v4.content.LocalBroadcastManager;
import android.view.accessibility.AccessibilityEvent;

/**
 * @Author ylw  2018/7/16 08:48
 */
public class MyAccessibilityService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
    }

    @Override
    public void onInterrupt() {

    }
}
