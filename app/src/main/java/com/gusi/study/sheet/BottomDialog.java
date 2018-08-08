package com.gusi.study.sheet;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @Author ylw  2018/8/8 23:27
 */
public class BottomDialog extends Dialog {

    public BottomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        Log.w("Fire", "BottomDialog:20行:onTouchEvent开始");
        boolean b = super.onTouchEvent(event);
        Log.w("Fire", "BottomDialog:22行:onTouchEvent结束" + b);
        return b;
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        Log.w("Fire", "BottomDialog:24行:dispatchTouchEvent 卡ISHI");
        boolean b = super.dispatchTouchEvent(ev);
        Log.w("Fire", "BottomDialog:27行:dispatchTouchEvent 结束" + b);
        return b;
    }
}
