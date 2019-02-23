package com.gusi.study.media;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author ylw  2019/2/18 20:41
 */
public class Media {
    private final ExecutorService mService;
    private volatile MediaRecorder mMediaRecorder;
    private long mBeginTime;
    private File mFile;

    public Media() {
        mService = Executors.newSingleThreadExecutor();
    }

    /**
     * 录音
     */
    public void record() {
        mService.execute(new Runnable() {
            @Override
            public void run() {
                if (mMediaRecorder != null) {
                    ToastUtils.showShort("Media 正在录音");
                    return;
                }
                //实例化MediaRecorder对象
                mMediaRecorder = new MediaRecorder();
                //从麦克风采集声音数据
                mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                //设置输出格式为MP4
                mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                //设置采样频率44100 频率越高,音质越好,文件越大
                mMediaRecorder.setAudioSamplingRate(44100);
                //设置声音数据编码格式,音频通用格式是AAC
                mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                //设置编码频率
                mMediaRecorder.setAudioEncodingBitRate(96000);
                //设置输出文件
                //创建录音文件
                mFile = new File(Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/recorderDemo/" + System.currentTimeMillis() + ".m4a");
                File parentFile = mFile.getParentFile();
                if (!parentFile.exists()) parentFile.mkdirs();
                //开始录音
                try {
                    mFile.createNewFile();
                    String path = mFile.getPath();
                    Log.w("Fire", "MediaActivity:50行:" + path);
                    mMediaRecorder.setOutputFile(path);
                    mMediaRecorder.prepare();
                    mMediaRecorder.start();
                    mBeginTime = System.currentTimeMillis();
                    ToastUtils.showShort("Media正在录音: ");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Fire", "MediaActivity:109行:" + e.toString());
                    mMediaRecorder = null;
                }
            }
        });
    }

    /**
     * 停止
     */
    public void stop() {
        mService.execute(new Runnable() {
            @Override
            public void run() {
                if (mMediaRecorder == null) {
                    ToastUtils.showShort("Media 没有在录音!");
                    return;
                }
                mMediaRecorder.stop();
                mMediaRecorder.release();
                mMediaRecorder = null;
                ToastUtils.showShort("录音时间:" + (System.currentTimeMillis() - mBeginTime));
            }
        });
    }

    /**
     * 播放
     */
    public void play() {
        if (mFile == null) {
            ToastUtils.showShort("Media没有播放资源!");
            return;
        }
        final MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(mFile.getPath());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            // 通过异步的方式装载媒体资源
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // 装载完毕回调
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {

        } finally {

        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
            }
        });
    }

}
