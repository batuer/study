package com.gusi.study.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w("Fire", "MyIntentService:17行:onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w("Fire", "MyIntentService:24行:onDestroy");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.w("Fire", "MyIntentService:34行:onHandleIntent");
    }

}
