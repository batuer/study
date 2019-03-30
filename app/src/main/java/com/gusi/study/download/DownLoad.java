package com.gusi.study.download;


import android.os.Build;
import android.os.Environment;

import com.blankj.utilcode.util.CloseUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class DownLoad {


    private File getExternalCacheDir() {
        File cache = new File(Environment.getExternalStorageDirectory(), "ACache");
        if (!cache.exists()) {
            cache.mkdirs();
        }
        return cache;
    }

    private void clearExternalCacheDir(File cache) {
        if (!cache.exists()) return;
        File[] files = cache.listFiles();
        if (files != null) {
            for (File file1 : files) {
                file1.delete();
            }
        }
    }


    public static String getNetFileSizeDescription(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i))
                    .append("GB");
        } else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i))
                    .append("MB");
        } else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i))
                    .append("KB");
        } else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            } else {
                bytes.append((int) size)
                        .append("B");
            }
        }
        return bytes.toString();
    }


    public void downloadFilePlus(String urlPath) {
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = null;
            int code = 0;
            long length = -1;
            for (int i = 0; i < 10; i++) {
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept-Encoding", "identity");
                conn.setConnectTimeout(30000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Range", "bytes=" + 0 + "-" + Long.MAX_VALUE);
                conn.connect();
                code = conn.getResponseCode();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    length = conn.getContentLengthLong();
                } else {
                    length = conn.getContentLength();
                }
                if (code == 200) {
                    EventBus.getDefault()
                            .post(new DownloadEvent(DownloadEvent.HANDLER_MSG, "不支持多线程下载!"));
                    return;
                }
                if (i >= 10) {
                    EventBus.getDefault()
                            .post(new DownloadEvent(DownloadEvent.HANDLER_MSG,
                                    "访问最大限制:" + code + " :-: " + length));
                    return;
                }
                if (code == 206 && length > 0) break;
            }
            StringBuilder sbHead = new StringBuilder();
            for (Map.Entry<String, List<String>> entry : conn.getHeaderFields()
                    .entrySet()) {
                sbHead.append(entry.getKey() + " , " + entry.getValue() + "\n");
            }

            //append
            String fileName = conn.getHeaderField("Content-Disposition");
            if (fileName != null) {
                fileName =
                        fileName.substring(fileName.indexOf("filename=") + "filename=".length(),
                                fileName.length());
            } else {
                fileName = urlPath.substring(urlPath.lastIndexOf("/") + 1, (urlPath.lastIndexOf(
                        "?") == -1 ? urlPath.length() : urlPath.lastIndexOf("?")));   //文件名
            }

            File cacheDir = getExternalCacheDir();
            clearExternalCacheDir(cacheDir);
            sbHead.append(fileName + " :文件大小: " + getNetFileSizeDescription(length));

            EventBus.getDefault()
                    .post(new DownloadEvent(DownloadEvent.HANDLER_HEAD, sbHead.toString()));

            int threadCount = Runtime.getRuntime()
                    .availableProcessors() * 2 + 1;
            long blockSize = length / threadCount;
            AtomicLong allDownloadCount = new AtomicLong();


            for (int threadId = 1; threadId <= threadCount; threadId++) {
                //第一个线程下载的开始位置
                long startIndex = (threadId - 1) * blockSize;
                long endIndex = threadId * blockSize - 1;
                if (threadId == threadCount) {//最后一个线程下载的长度要稍微长一点
                    endIndex = length;
                }
                new Thread(new DownLoadRunnable(startIndex, endIndex, urlPath, allDownloadCount)).start();
            }
            while (allDownloadCount.get() < length) {
                EventBus.getDefault()
                        .post(new DownloadEvent(DownloadEvent.HANDLER_MSG,
                                ":已下载百分比:" + (allDownloadCount.doubleValue() / length) * 100));
                Thread.sleep(500);
            }
            EventBus.getDefault()
                    .post(new DownloadEvent(DownloadEvent.HANDLER_MSG, "下载结束!!"));
            List<File> fileList = Arrays.asList(cacheDir.listFiles());
            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return Integer.parseInt(o1.getName()) - Integer.parseInt(o2.getName());
                }
            });
            File orgFile = new File(cacheDir, fileName);
            orgFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(orgFile);
            int len = 0;
            byte[] buffer = new byte[1024 * 4];
            for (File file : fileList) {
                FileInputStream fis = new FileInputStream(file);
                while ((len = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                CloseUtils.closeIOQuietly(fis);
                file.delete();
            }
            CloseUtils.closeIOQuietly(fos);
            EventBus.getDefault()
                    .post(new DownloadEvent(DownloadEvent.HANDLER_MSG, "拼接结束!!!!"));
        } catch (Exception e) {
            EventBus.getDefault()
                    .post(new DownloadEvent(DownloadEvent.HANDLER_MSG, "网络异常!" + e.toString()));
        } finally {

        }
    }

}
