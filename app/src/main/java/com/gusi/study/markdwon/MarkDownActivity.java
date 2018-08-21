package com.gusi.study.markdwon;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.CloseUtils;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MarkDownActivity extends BaseActivity {


    @Override
    protected int getLayout() {
        return R.layout.activity_mark_down;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(mToolbar, true, "MarkDown");

    }

    public void down(View view) {
        if (needPermission()) return;
        String url = "https://raw.githubusercontent.com/batuer/Blog/master/Blog/source/_posts/Activity%E5%90%AF%E5%8A%A8%E6%B5%81%E7%A8%8B.md";
        OkHttpClient client = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder().url(url).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Fire", "MarkDownActivity:38行:" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream is = null;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    File file = new File(getExternalCacheDir(), "Activity.md");
                    if (file.exists()) {
                        file.delete();
                        file.createNewFile();
                    }
                    fos = new FileOutputStream(file);
                    byte[] b = new byte[1024];
                    while ((is.read(b)) != -1) {
                        fos.write(b);
                    }
                    Log.w("Fire", "MarkDownActivity:66行:" + file.toString());
                } catch (IOException e) {
                    Log.e("Fire", "MarkDownActivity:68行:" + e.toString());
                } finally {
                    CloseUtils.closeIO(fos, is);
                }
            }
        });
    }

    private boolean needPermission() {
//        PermissionUtils.permission(PermissionConstants.STORAGE).callback(new PermissionUtils.SimpleCallback() {
//            @Override
//            public void onGranted() {
//                Log.w("Fire", "MarkDownActivity:87行:onGranted");
//            }
//
//            @Override
//            public void onDenied() {
//                Log.w("Fire", "MarkDownActivity:92行:onDenied");
//            }
//        }).request();

        int i = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int i1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (i != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if (i1 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        Log.w("Fire", "MarkDownActivity:99行:" + i + ":" + i1);
        return i != PackageManager.PERMISSION_GRANTED && i1 != PackageManager.PERMISSION_GRANTED;

    }

}
