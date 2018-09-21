package com.gusi.study.anim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class AnimActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_anim;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(mToolbar, true, "Anim");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("Fire", "AnimActivity:23行:onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Fire", "AnimActivity:30行:onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("Fire", "AnimActivity:36行:onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("Fire", "AnimActivity:42行:onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("Fire", "AnimActivity:48行:onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("Fire", "AnimActivity:54行:onDestroy");
    }
}
