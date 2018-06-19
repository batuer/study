package com.gusi.study.intentservice;

import android.content.Intent;
import android.view.View;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

/**
 * IntentService 单线程HandleThread 执行任务，任务单线程执行。
 */
public class IntentServiceActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_intent_service;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(mToolbar, true, "IntentService");
    }

    public void startService(View view) {
        startService(new Intent(this, MyIntentService.class));
    }
}
