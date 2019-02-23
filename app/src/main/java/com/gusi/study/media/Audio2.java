package com.gusi.study.media;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Environment;
import android.util.Log;

import com.blankj.utilcode.util.CloseUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
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
    //    private static final int BUFFER_SIZE = 2048;//buffer值不能太大，避免OOM
    private volatile boolean mIsRecording;
    private volatile boolean mIsPlaying;
    private final ExecutorService mService;

    private File mAudioRecordFile;
//    private byte[] mBuffer;

    public Audio2() {
        mService = Executors.newSingleThreadExecutor();
//        mBuffer = new byte[BUFFER_SIZE];
    }

    public void recordAndPlay(final int audioSource, final boolean noise) {
        if (mIsRecording) {
            ToastUtils.showShort("Audio正在录音: ");
            return;
        }
        mService.execute(new Runnable() {
            @Override
            public void run() {
//                FileOutputStream fos = null;
                try {
                    //记录开始录音时间
                    long beginTime = System.currentTimeMillis();
                    //创建录音文件
                    //配置AudioRecord
//                    int audioSource = MediaRecorder.AudioSource.MIC;
                    //所有android系统都支持
                    int sampleRate = 44100;
                    //单声道输入
                    int inChannelConfig = AudioFormat.CHANNEL_IN_MONO;
                    //PCM_16是所有android系统都支持的
                    int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
                    //计算AudioRecord内部buffer最小
                    int minBufferSize = AudioRecord.getMinBufferSize(sampleRate, inChannelConfig, audioFormat);
                    //buffer不能小于最低要求，也不能小于我们每次我们读取的大小。
                    AudioRecord audioRecord = new AudioRecord(audioSource, sampleRate, inChannelConfig, audioFormat, minBufferSize);

                    //配置播放器
                    //音乐类型，扬声器播放
                    int streamType = AudioManager.STREAM_MUSIC;
                    //录音时采用的采样频率，所以播放时同样的采样频率
                    //流模式
                    int mode = AudioTrack.MODE_STREAM;
                    //构造AudioTrack  不能小于AudioTrack的最低要求，也不能小于我们每次读的大小
                    int audioSessionId = -1;
                    if (AcousticEchoCanceler.isAvailable()) {
                        audioSessionId = audioRecord.getAudioSessionId();
                    }

                    if (AcousticEchoCanceler.isAvailable()) {
                        AcousticEchoCanceler acousticEchoCanceler = AcousticEchoCanceler.create(audioSessionId);
                        Log.w("Fire", "Audio2:97行:" + acousticEchoCanceler);
                        if (acousticEchoCanceler == null) {

                        } else {
                            acousticEchoCanceler.setEnabled(true);
                        }
                    }

                    Log.w("Fire", "Audio2:78行:" + audioSessionId);
                    AudioTrack audioTrack = null;
                    if (audioSessionId == -1) {
                        audioTrack = new AudioTrack(streamType, sampleRate, AudioFormat.CHANNEL_OUT_MONO, audioFormat, minBufferSize, mode);
                    } else {
                        audioTrack = new AudioTrack(streamType, sampleRate, AudioFormat.CHANNEL_OUT_MONO, audioFormat, minBufferSize, mode, audioSessionId);
                    }

                    //开始录音
                    mIsRecording = true;
                    audioRecord.startRecording();

                    //开始播放
                    mIsPlaying = true;
                    audioTrack.play();


//                    byte[] buffer = new byte[minBufferSize];
                    short[] buffer = new short[minBufferSize];
                    //循环读取数据，写入输出流中
                    while (mIsRecording) {
                        //只要还在录音就一直读取
                        int read = audioRecord.read(buffer, 0, minBufferSize);
                        if (read > 0) {
                            if (noise) {
                                noise(buffer, 0, minBufferSize);
                            }
//                            fos.write(buffer, 0, read);
                            audioTrack.write(buffer, 0, read);
                        }

                    }

                    //退出循环，停止录音，释放资源
                    if (audioRecord != null) {
                        audioRecord.release();
                    }
                    //播放器释放
                    mIsPlaying = false;
                    if (audioTrack != null) {
                        audioTrack.release();
                    }

                } catch (
                        NullPointerException e) {
                    ToastUtils.showShort(e.toString());
                    Log.e("Fire", "MediaActivity:163行:" + e.toString());
                } finally {
//                    CloseUtils.closeIO(fos);
                }
            }
        });
    }


    public void record(final int audioSource, final boolean noise) {
        if (mIsRecording) {
            ToastUtils.showShort("Audio正在录音: ");
            return;
        }
        mService.execute(new Runnable() {
            @Override
            public void run() {
                FileOutputStream fos = null;
                try {
                    //记录开始录音时间
                    long beginTime = System.currentTimeMillis();
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
                    AudioRecord audioRecord = new AudioRecord(audioSource, sampleRate, channelConfig, audioFormat, minBufferSize);
                    //开始录音
                    mIsRecording = true;
                    audioRecord.startRecording();
                    byte[] buffer = new byte[minBufferSize];
                    //循环读取数据，写入输出流中
                    while (mIsRecording) {
                        //只要还在录音就一直读取
                        int read = audioRecord.read(buffer, 0, minBufferSize);
                        if (read > 0) {
                            if (noise) {
//                                noise(buffer, 0, minBufferSize);
                            }
                            fos.write(buffer, 0, read);
                        }

                    }
                    //退出循环，停止录音，释放资源
                    mIsRecording = false;
                    if (audioRecord != null) {
                        audioRecord.release();
                        Log.w("Fire", ":Audio 录音结束" + (System.currentTimeMillis() - beginTime) + ":" + mAudioRecordFile.length());
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


    public void recordShort(final int audioSource, final boolean noise) {
        if (mIsRecording) {
            ToastUtils.showShort("Audio正在录音: ");
            return;
        }
        mService.execute(new Runnable() {
            @Override
            public void run() {
//                FileOutputStream fos = null;
                DataOutputStream dos = null;
                try {
                    //记录开始录音时间
                    long beginTime = System.currentTimeMillis();
                    //创建录音文件
                    mAudioRecordFile = new File(Environment.getExternalStorageDirectory()
                            .getAbsolutePath() +
                            "/recorderDemo/" + System.currentTimeMillis() + ".pcm");
                    File recordFileParentFile = mAudioRecordFile.getParentFile();
                    if (!recordFileParentFile.exists()) recordFileParentFile.mkdirs();
                    mAudioRecordFile.createNewFile();
                    Log.w("Fire", "MediaActivity:170行:" + mAudioRecordFile.getPath());
                    //创建文件输出流
//                    fos = new FileOutputStream(mAudioRecordFile);
                    dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(mAudioRecordFile)));
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
                    AudioRecord audioRecord = new AudioRecord(audioSource, sampleRate, channelConfig, audioFormat, minBufferSize);
                    //开始录音
                    mIsRecording = true;
                    audioRecord.startRecording();
                    int audioSessionId = audioRecord.getAudioSessionId();


                    short[] buffer = new short[minBufferSize];
                    //循环读取数据，写入输出流中
                    while (mIsRecording) {
                        //只要还在录音就一直读取
                        int read = audioRecord.read(buffer, 0, minBufferSize);
                        if (read > 0) {
                            if (noise) {
                                noise(buffer, 0, minBufferSize);
                            }
                            // 循环将buffer中的音频数据写入到OutputStream中
                            for (int i = 0; i < read; i++) {
                                dos.writeShort(buffer[i]);
                            }
                        }

                    }

                    //退出循环，停止录音，释放资源
                    mIsRecording = false;
                    if (audioRecord != null) {
                        audioRecord.release();
                        Log.w("Fire", ":Audio 录音结束" + (System.currentTimeMillis() - beginTime) + ":" + mAudioRecordFile.length());
                    }

                } catch (IOException e) {
                    ToastUtils.showShort(e.toString());
                    Log.e("Fire", "MediaActivity:163行:" + e.toString());
                } finally {
                    CloseUtils.closeIO(dos);
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
                    AudioTrack audioTrack = new AudioTrack(streamType, sampleRate, channelConfig, audioFormat, minBufferSize, mode);
                    //开始播放
                    mIsPlaying = true;
                    audioTrack.play();
                    byte[] buffer = new byte[minBufferSize];
                    //从文件流读数据
                    FileInputStream is = null;
                    try {
                        //循环读数据，写到播放器去播放
                        is = new FileInputStream(file);
                        //循环读数据，写到播放器去播放
                        int read;
                        //只要没读完，循环播放
                        while (mIsPlaying && (read = is.read(buffer)) > 0) {
                            int ret = audioTrack.write(buffer, 0, read);
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
                        audioTrack.release();
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


    private void noise(short[] lin, int off, int len) {
        int i, j;
        for (i = 0; i < len; i++) {
            j = lin[i + off];
            lin[i + off] = (short) (j >> 2);
        }
    }

    //    private void noise(byte[] lin, int off, int len) {
//        byte[] temp = Arrays.copyOf(lin, lin.length);
//        for (byte b : lin) {
//            Log.e("Fire", "Audio2:290行:" + b);
//        }
//
//        int i, j;
//        for (i = 0; i < len; i++) {
//            j = lin[i + off];
//            lin[i + off] = (byte) (j >> 2);
//        }
//        for (int i1 = 0; i1 < temp.length; i1++) {
//            Log.w("Fire", "Audio2:294行:" + temp[i1] + ":" + lin[i1]);
//        }
//    }
//    private AcousticEchoCanceler acousticEchoCanceler;


//    private void initAEC() {
//        if (AcousticEchoCanceler.isAvailable()) {
//            if (acousticEchoCanceler == null) {
//                acousticEchoCanceler = AcousticEchoCanceler.create(audioSessionId);
//                Log.d(TAG, "initAEC: ---->" + acousticEchoCanceler + "\t" + audioSessionId);
//                if (acousticEchoCanceler == null) {
//                    Log.e(TAG, "initAEC: ----->AcousticEchoCanceler create fail.");
//                } else {
//                    acousticEchoCanceler.setEnabled(true);
//                }
//            }
//        }
//    }
}
