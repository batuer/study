package com.gusi.study.provider;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;
import com.gusi.study.rainbow.RainbowActivity;

import java.util.List;

/**
 * @author Ylw
 * @since 2020/7/17 22:25
 */
public class ProviderActivity extends BaseActivity {

    private static final String TAG = "Fire";
    private EditText mEt;
    private ActivityManager mActivityManager;

    @Override
    protected int getLayout() {
        return R.layout.activity_provider;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(mToolbar, true, "Provider");
        mEt = findViewById(R.id.et);
        mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    }

    public void turn(View view) {
        startActivity(new Intent(this, RainbowActivity.class));
    }

    public void query(View view) {
        Uri uri = Uri.parse("content://com.gusi.provider/student");
        Cursor cursor = getContentResolver().query(uri, null, mEt.getText().toString().trim(), null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String birth = cursor.getString(cursor.getColumnIndex("BIRTH"));
            boolean sex = cursor.getInt(cursor.getColumnIndex("SEX")) == 1 ? true : false;
            int age = cursor.getInt(cursor.getColumnIndex("AGE"));
            String result = String.format("id=%s, name=%s, birth=%s, sex=%s, age=%s",
                    id, name, birth, sex, age);
            Log.i(TAG, "query: " + result);
        }
    }

    public void maxQuery(View view) {
        for (int i = 0; i < 16; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Uri uri = Uri.parse("content://com.gusi.provider/student");
                    Cursor cursor = getContentResolver().query(uri, null, mEt.getText().toString().trim(), null, null
                            , null);
                    Log.i(TAG, "run: " + cursor.getCount());
                }
            }).start();
        }
    }

    public void maxAMS(View view) {
        for (int i = 0; i < 32; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        List<ActivityManager.RunningTaskInfo> runningTasks = mActivityManager.getRunningTasks(20);
                        Log.i(TAG, "run: " + runningTasks);
                    }
                }
            }).start();
        }
    }

}
