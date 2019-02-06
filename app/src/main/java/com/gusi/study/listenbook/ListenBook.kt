package com.gusi.study.listenbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.CloseUtils
import com.blankj.utilcode.util.PermissionUtils
import com.gusi.study.R
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ListenBook : AppCompatActivity() {
    var mExecutors: ExecutorService = Executors.newSingleThreadExecutor()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listen_book)
        checkPermission()
    }

    private fun checkPermission(): Boolean {
        val granted = PermissionUtils.isGranted(PermissionConstants.STORAGE)
        if (!granted) {
            PermissionUtils.permission(PermissionConstants.STORAGE)
                    .callback(object : PermissionUtils.SimpleCallback {
                        override fun onGranted() {

                        }

                        override fun onDenied() {
                            PermissionUtils.openAppSettings()
                        }
                    }).request()

        }

        return granted
    }

    fun getKey(v: View) {

    }

    fun getDirectory(v: View) {
//        mExecutors!!.execute { Jsoup. }
    }

    fun performDownload(v: View) {
        mExecutors!!.execute {
            val url = "http://t33.tingchina.com/yousheng/%E6%AD%A6%E4%BE%A0%E5%B0%8F%E8%AF%B4/%E5%A4%A9%E9%BE%99%E5%85%AB%E9%83%A8/%E7%AC%AC29%E7%AB%A0_%E8%99%AB%E8%B1%B8%E5%87%9D%E5%AF%92%E6%8E%8C%E4%BD%9C%E5%86%B0.mp3?key=568f62c26c255e3ac0c4fa46010ebc95_602757076"
            var saveDir = File(externalCacheDir, "tianlongbabu").path
            performDownload(url)
        }

    }

    private fun performDownload(url: String) {
        var client = OkHttpClient()
        var request = Request.Builder().url(url)
                .build()
        val response = client.newCall(request).execute()
        val toString = response.toString()
        val body = response.body()


        Log.w("Fire", ":80行:" + toString)
        Log.w("Fire", ":70行:" + body.toString());
        Log.w("Fire", ":71行:" + body.contentLength())
        val byteStream = body.byteStream()
        Log.w("Fire", ":72行:" + byteStream);
        CloseUtils.closeIOQuietly(byteStream)


    }

}

