package com.gusi.study.anim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ThreadPoolExecutor;

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

    public void delete(View view) {
//        File externalStorageDirectory = Environment.getExternalStorageDirectory();
//        int core = Runtime.getRuntime()
//                .availableProcessors() * 2;
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(core, core, 30, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.execute(new DelRunnable(executor, externalStorageDirectory));
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    get();
                } catch (Exception e) {
                    Log.e("Fire", "AnimActivity:83行:" + e.toString());
                }
            }
        }).start();
    }

    private void get() throws Exception {
        String encoding = "UTF-8";
        //post的form参数(json兼职对)
        String params = "[{\"addTime\":\"2011-09-19 14:23:02\"[],\"iccid\":\"1111\",\"id\":0,\"imei\":\"2222\"," +
                "\"imsi\":\"3333\",\"phoneType\":\"4444\",\"remark\":\"aaaa\",\"tel\":\"5555\"}]";
        try {
            String path = "http://www.baidu.com";
            byte[] data = params.getBytes(encoding);
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Type", "application/x-javascript; charset=" + encoding);
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            conn.setConnectTimeout(5 * 1000);
            OutputStream outStream = conn.getOutputStream();
            outStream.write(data);
            outStream.flush();
            outStream.close();
            System.out.println(conn.getResponseCode()); // 响应代码 200表示成功
            if (conn.getResponseCode() == 200) {
                InputStream inStream = conn.getInputStream();
                String result = new String(toByteArray(inStream), "UTF-8");
                Log.w("Fire", "AnimActivity:114行:" + result);
            }else{
                Log.w("Fire", "AnimActivity:115行:" + conn.getResponseMessage());
            }
        } finally {

        }

    }
    private  byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

    private class DelRunnable implements Runnable {
        private ThreadPoolExecutor mExecutor;
        private File mFile;

        public DelRunnable(ThreadPoolExecutor executor, File file) {
            mExecutor = executor;
            mFile = file;
        }

        @Override
        public void run() {
            if (mFile.isFile()) {
                // TODO: 2019/3/23  
                mFile.delete();
                Log.e("Fire", "DelRunnable:89行:" + mFile.toString());
            } else {
                File[] files = mFile.listFiles();
                if (files == null || files.length == 0) return;
                for (File file : files) {
                    if (file == null) return;
                    if (file.length() == 0) {
                        file.delete();
                        Log.e("Fire", "DelRunnable:96行:" + file.toString());
                        continue;
                    }
                    if (!file.isFile()) {
                        mExecutor.execute(new DelRunnable(mExecutor, file));
                    }
                }
            }
        }
    }
}
