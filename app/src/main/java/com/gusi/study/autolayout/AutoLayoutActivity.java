package com.gusi.study.autolayout;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.TimeUtils;
import com.google.gson.Gson;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AutoLayoutActivity extends BaseActivity {

    private OkHttpClient mClient;
    private Gson mGson;

    @Override
    protected int getLayout() {
        return R.layout.activity_auto_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(mToolbar, true, "Auto");
        mClient = new OkHttpClient.Builder().build();
        mGson = new Gson();
    }

    public void query(View view) {
//        new OkHttpClient.Builder().

        Request req = new Request.Builder().get().addHeader("id","1")
                .url("http://192.168.1.110:8080/ssm/test/index_api").build();
        Call call = mClient.newCall(req);
        String s = req.url().toString();
        Log.w("Fire", "AutoLayoutActivity:50行:" + s);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response2Str(response);
                Log.w("Fire", s+"::40行:" + Thread.currentThread().getName());
                if (!TextUtils.isEmpty(s)) {
                    Test test = mGson.fromJson(s, Test.class);
                    Log.w("Fire", "AutoLayoutActivity:54行:" + test.toString());
                }
            }
        });

    }

    public void save(View view) {
        Test test = new Test();
        test.setContext(":"+ TimeUtils.getNowString());
        test.setViewCount(123251);

        //使用Gson 添加 依赖 compile 'com.google.code.gson:gson:2.8.1'
        Gson gson = new Gson();
        //使用Gson将对象转换为json字符串
        String json = gson.toJson(test);

        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request req = new Request.Builder().post(requestBody)
                .url("http://192.168.1.110:8080/ssm/test/index_api1").build();
        Call call = mClient.newCall(req);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.w("Fire", "AutoLayoutActivity:40行:" + Thread.currentThread().getName());

                String s = response2Str(response);
                BaseResp resp = mGson.fromJson(s, BaseResp.class);
                Log.w("Fire", "AutoLayoutActivity:94行:" + resp.toString());
            }
        });
    }

    private String response2Str(Response response) {
        String result = "";
        try {
            InputStream is = response.body().byteStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            result = byteArrayOutputStream.toString();
        } catch (IOException e) {
            Log.e("Fire", "AutoLayoutActivity:70行:" + e.toString());
        }

        return result;
    }

}
