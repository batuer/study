package com.gusi.study.download;


import android.os.Environment;

import com.blankj.utilcode.util.CloseUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

public class DownLoadRunnable implements Runnable {
    private long mStartIndex;
    private long mEndIndex;
    private String mUrl;
    private AtomicLong mAllDownloadCount;
    private int mDownloadCount;

    public DownLoadRunnable(long startIndex, long endIndex, String url,
                            AtomicLong allDownloadCount) {
        this.mStartIndex = startIndex;
        this.mEndIndex = endIndex;
        this.mUrl = url;
        this.mAllDownloadCount = allDownloadCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                download();
                break;
            } catch (IOException e) {
                mAllDownloadCount.addAndGet(-mDownloadCount);
                mDownloadCount = 0;
                ToastUtils.showShort("失败第" + i + "次" + "  :   " + e.toString());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                }
            }
        }
    }

    private void download() throws IOException {
        InputStream is = null;
        FileOutputStream fos = null;
        File temp = new File(getExternalCacheDir(), "" + mStartIndex);
        try {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(30000);
            conn.setRequestMethod("GET");
            //重要:请求服务器下载部分文件 指定文件的位置
            conn.setRequestProperty("Range", "bytes=" + mStartIndex + "-" + mEndIndex);
            conn.connect();
            is = conn.getInputStream();//已经设置了请求的位置，返回的是当前位置对应的文件的输入流
            temp.createNewFile();
            fos = new FileOutputStream(temp);
            int len = 0;
            byte[] buffer = new byte[1024 * 4];
            while ((len = is.read(buffer)) != -1) {
                mAllDownloadCount.addAndGet(len);
                mDownloadCount += len;
                fos.write(buffer, 0, len);
            }
        } finally {
            CloseUtils.closeIOQuietly(is, fos);
        }
    }

    private File getExternalCacheDir() {
        File cache = new File(Environment.getExternalStorageDirectory(), "ACache");
        if (!cache.exists()) {
            cache.mkdirs();
        }
        return cache;
    }


    //    private void save(File temp) throws IOException {
//        synchronized (mRaf) {
//            System.out.println("Fire:   " + System.currentTimeMillis() + ":--:" + mStartIndex);
//            FileInputStream fis = null;
//            try {
//                fis = new FileInputStream(temp);
//                //随机写文件的时候从哪个位置开始写
//                mRaf.seek(mStartIndex);//定位文件
//                int len = 0;
//                byte[] buffer = new byte[1024 * 4];
//                while ((len = fis.read(buffer)) != -1) {
//                    mRaf.write(buffer, 0, len);
//                }
//                int i = mSaveCount.addAndGet(1);
//                System.out.println("Fire" + i + ":    " + System.currentTimeMillis() + ":--:" +
// mStartIndex);
//            } finally {
//                CloseUtils.closeIOQuietly(fis);
//            }
//        }
//    }

}
