package com.gusi.study.media;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Environment;
import android.util.Log;

import com.blankj.utilcode.util.CloseUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author ylw  2019/2/19 22:24
 */
public class Audio2 {
    private static final int BUFFER_SIZE = 2048;//buffer值不能太大，避免OOM
    private volatile boolean mIsRecording;
    private volatile boolean mIsPlaying;
    private final ExecutorService mService;
    private long mBeginTime;
    private File mAudioRecordFile;
    private byte[] mBuffer;

    public Audio2() {
        mService = Executors.newSingleThreadExecutor();
        mBuffer = new byte[BUFFER_SIZE];
    }


    public void record(final int audioSource) {
        if (mIsRecording) {
            ToastUtils.showShort("Audio正在录音: " + (System.currentTimeMillis() - mBeginTime));
            return;
        }
        mService.execute(new Runnable() {
            @Override
            public void run() {
                FileOutputStream fos = null;
                try {
                    //记录开始录音时间
                    mBeginTime = System.currentTimeMillis();
                    //创建录音文件
                    mAudioRecordFile = new File(Environment.getExternalStorageDirectory()
                            .getAbsolutePath() +
                            "/recorderDemo/" + System.currentTimeMillis() + ".pcm");
                    File recordFileParentFile = mAudioRecordFile.getParentFile();
                    if (!recordFileParentFile.exists()) recordFileParentFile.mkdirs();
                    mAudioRecordFile.createNewFile();
                    Log.w("Fire", "MediaActivity:170行:" + mAudioRecordFile.getPath());
                    //创建文件输出流
                    fos = new FileOutputStream(mAudioRecordFile);
                    //配置AudioRecord
//                    int audioSource = MediaRecorder.AudioSource.MIC;
                    //所有android系统都支持
                    int sampleRate = 44100;
                    //单声道输入
                    int channelConfig = AudioFormat.CHANNEL_IN_MONO;
                    //PCM_16是所有android系统都支持的
                    int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
                    //计算AudioRecord内部buffer最小
                    int minBufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat);
                    //buffer不能小于最低要求，也不能小于我们每次我们读取的大小。
                    AudioRecord audioRecord = new AudioRecord(audioSource, sampleRate, channelConfig, audioFormat, Math.max(minBufferSize, BUFFER_SIZE));
                    //开始录音
                    mIsRecording = true;
                    audioRecord.startRecording();
                    //循环读取数据，写入输出流中
                    while (mIsRecording) {
                        //只要还在录音就一直读取
                        int read = audioRecord.read(mBuffer, 0, BUFFER_SIZE);
                        if (read > 0) {
                            fos.write(mBuffer, 0, read);
                        }

                    }
                    //退出循环，停止录音，释放资源
                    if (audioRecord != null) {
                        audioRecord.stop();
                        audioRecord.release();
                        Log.w("Fire", "MediaActivity:213行:Audio 录音结束" + (System.currentTimeMillis() - mBeginTime) + ":"
                                + FileUtils.getDirSize(mAudioRecordFile));
                    }

                } catch (IOException e) {
                    ToastUtils.showShort(e.toString());
                    Log.e("Fire", "MediaActivity:163行:" + e.toString());
                } finally {
                    CloseUtils.closeIO(fos);
                }
            }
        });
    }

    public void stop() {
        if (!mIsRecording) {
            ToastUtils.showShort("Audio 没有在录音!");
            return;
        }
        mIsRecording = false;
    }

    public void play(final File file) {
        if (mIsRecording) {
            ToastUtils.showShort("Audio 正在录音!");
            return;
        }
        if (mIsPlaying) {
            ToastUtils.showShort("Audio 正在播放!");
            return;
        }
        mService.execute(new Runnable() {
            @Override
            public void run() {
                Log.w("Fire", "MediaActivity:167行:" + file);
                if (file != null) {
                    //配置播放器
                    //音乐类型，扬声器播放
                    int streamType = AudioManager.STREAM_MUSIC;
                    //录音时采用的采样频率，所以播放时同样的采样频率
                    int sampleRate = 44100;
                    //单声道，和录音时设置的一样
                    int channelConfig = AudioFormat.CHANNEL_OUT_MONO;
                    //录音时使用16bit，所以播放时同样采用该方式
                    int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
                    //流模式
                    int mode = AudioTrack.MODE_STREAM;
                    //计算最小buffer大小
                    int minBufferSize = AudioTrack.getMinBufferSize(sampleRate, channelConfig, audioFormat);
                    //构造AudioTrack  不能小于AudioTrack的最低要求，也不能小于我们每次读的大小
                    AudioTrack audioTrack = new AudioTrack(streamType, sampleRate, channelConfig, audioFormat, Math.max(minBufferSize, BUFFER_SIZE), mode);
                    //开始播放
                    mIsPlaying = true;
                    audioTrack.play();
                    //从文件流读数据
                    FileInputStream is = null;
                    try {
                        //循环读数据，写到播放器去播放
                        is = new FileInputStream(file);
                        //循环读数据，写到播放器去播放
                        int read;
                        //只要没读完，循环播放
                        while (mIsPlaying && (read = is.read(mBuffer)) > 0) {
                            int ret = audioTrack.write(mBuffer, 0, read);
                            //检查write的返回值，处理错误
                            switch (ret) {
                                case AudioTrack.ERROR_INVALID_OPERATION:
                                case AudioTrack.ERROR_BAD_VALUE:
                                case AudioManager.ERROR_DEAD_OBJECT:
                                    ToastUtils.showShort("播放失败!");
                                    return;
                                default:
                                    break;
                            }
//                            audioTrack.play();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("Fire", "MediaActivity:208行:" + e.toString());
                        //读取失败
                        ToastUtils.showShort("播放失败!" + e.toString());
                    } finally {
                        mIsPlaying = false;
                        //播放器释放
                        try {
                            audioTrack.stop();
                            audioTrack.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //关闭文件输入流
                        CloseUtils.closeIO(is);
                    }
                    //循环读数据，写到播放器去播放
                    //错误处理，防止闪退
                }
            }
        });
    }

    public void stopPlay() {
        if (!mIsPlaying) {
            ToastUtils.showShort("没有在播放!");
        } else {
            mIsPlaying = false;
        }
    }

    public File getAudioRecordFile() {
        return mAudioRecordFile;
    }
}
