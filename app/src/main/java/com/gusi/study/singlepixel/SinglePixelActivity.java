package com.gusi.study.singlepixel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class SinglePixelActivity extends AppCompatActivity {
  private static final String TAG = "Fire";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_single_pixel);
    Log.d(TAG, "onCreate--->启动1像素保活");
    // 获得activity的Window对象，设置其属性
    Window mWindow = getWindow();
    mWindow.setGravity(Gravity.LEFT | Gravity.TOP);
    WindowManager.LayoutParams attrParams = mWindow.getAttributes();
    attrParams.x = 0;
    attrParams.y = 0;
    attrParams.height = 1;
    attrParams.width = 1;
    mWindow.setAttributes(attrParams);
    // 绑定SinglePixelActivity到ScreenManager
    ScreenManager.getScreenManagerInstance(this).setSingleActivity(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG,"onDestroy--->1像素保活被终止");
    //if(! SystemUtils.isAppAlive(this,Contants.PACKAGE_NAME)){
    //  Intent intentAlive = new Intent(this, SportsActivity.class);
    //  intentAlive.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //  startActivity(intentAlive);
    //  Log.i(TAG,"SinglePixelActivity---->APP被干掉了，我要重启它");
    //}
  }
}
