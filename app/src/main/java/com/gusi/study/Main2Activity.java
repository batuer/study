package com.gusi.study;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void main(View view) {
        if (SPUtils.getInstance().getBoolean("Setting")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            ToastUtils.showShort("Setting");
        }
    }

    public void ARouter(View view) {
        Log.w("Fire", "Main2Activity:31行:" );
    }

    public void setting(View view) {
        SPUtils.getInstance().put("Setting", true);
    }
}
