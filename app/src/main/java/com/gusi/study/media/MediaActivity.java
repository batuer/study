package com.gusi.study.media;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.CloseUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MediaActivity extends BaseActivity {
    //buffer值不能太大，避免OOM
    private static final int BUFFER_SIZE = 2048;

    private ExecutorService mMediaService;
    private ExecutorService mAudioService;
    private volatile MediaRecorder mMediaRecorder;
    private volatile AudioRecord mAudioRecord;
    private volatile boolean mIsAudioRecording;
    private long mMediaStartTime;
    private long mAudioStartTime;
    private byte[] mBuffer;
    private FileOutputStream mAudioRecordFos;
    private File mAudioRecordFile;

    @Override
    protected int getLayout() {
        return R.layout.activity_media;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(mToolbar, true, "Record");
        mMediaService = Executors.newSingleThreadExecutor();
        mAudioService = Executors.newSingleThreadExecutor();
        mBuffer = new byte[BUFFER_SIZE];
    }

    public void mediaStart(View view) {
        mMediaService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public void mediaPause(View view) {
//        if (mMediaRecorder == null) {
//            ToastUtils.showShort("mMediaRecorder is Null");
//            return;
//        }
//        if (!mIsMediaRecording) {
//            ToastUtils.showShort("Media没有录音");
//            return;
//        }
//        mMediaRecorder.pause();
    }

    public void mediaDestroy(View view) {
        mMediaService.execute(new Runnable() {
            @Override
            public void run() {
                if (mMediaRecorder == null) {
                    ToastUtils.showShort("Media 没有在录音!");
                    return;
                }
                mediaDestroy();
                ToastUtils.showShort("录音时间:" + (System.currentTimeMillis() - mMediaStartTime));
            }
        });
    }


    /**
     * MediaRecord
     */
    private void mediaRecord() {
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
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + "/recorderDemo/" + System.currentTimeMillis() + ".m4a");
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) parentFile.mkdirs();
        //开始录音
        try {
            file.createNewFile();
            String path = file.getPath();
            Log.w("Fire", "MediaActivity:50行:" + path);
            mMediaRecorder.setOutputFile(path);
            mMediaRecorder.prepare();
            mMediaRecorder.start();
            mMediaStartTime = System.currentTimeMillis();
            ToastUtils.showShort("Media正在录音: ");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Fire", "MediaActivity:109行:" + e.toString());
            mMediaRecorder = null;
        }
    }

    /**
     * MediaStop
     */
    private void mediaDestroy() {
        mMediaRecorder.stop();
        mMediaRecorder.release();
        mMediaRecorder = null;
    }


    public void audioStart(View view) {
        mAudioService.execute(new Runnable() {
            @Override
            public void run() {
                if (mAudioRecord != null) {
                    ToastUtils.showShort("Media正在录音: " + (System.currentTimeMillis() - mAudioStartTime));
                    return;
                }
                audioRecord();
            }
        });
    }

    public void audioPause(View view) {
    }

    public void audioStop(View view) {
        if (!mIsAudioRecording) {
            ToastUtils.showShort("Audio 没有在录音!");
            return;
        }
        mIsAudioRecording = false;
    }

    public void audioPlay(View view) {
        mAudioService.execute(new Runnable() {
            @Override
            public void run() {
                Log.w("Fire", "MediaActivity:167行:" + mAudioRecordFile);
                if (mAudioRecordFile != null) {
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
                    //从文件流读数据
                    FileInputStream is = null;
                    try {
                        //循环读数据，写到播放器去播放
                        is = new FileInputStream(mAudioRecordFile);
                        //循环读数据，写到播放器去播放
                        int read;
                        //只要没读完，循环播放
                        while ((read = is.read(mBuffer)) > 0) {
                            int ret = audioTrack.write(mBuffer, 0, read);
                            Log.w("Fire", "MediaActivity:194行:" + ret);
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
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("Fire", "MediaActivity:208行:" + e.toString());
                        //读取失败
                        ToastUtils.showShort("播放失败!");
                    } finally {
//                        mIsPlaying = false;
                        //关闭文件输入流
                        if (is != null) {
                            CloseUtils.closeIO(is);
                        }
                        //播放器释放
                        try {
                            audioTrack.stop();
                            audioTrack.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    //循环读数据，写到播放器去播放
                    //错误处理，防止闪退
                }
            }
        });
    }

    private void audioRecord() {
        try {
            //记录开始录音时间
            mAudioStartTime = System.currentTimeMillis();
            //创建录音文件
            mAudioRecordFile = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath() +
                    "/recorderDemo/" + System.currentTimeMillis() + ".pcm");
            File recordFileParentFile = mAudioRecordFile.getParentFile();
            if (!recordFileParentFile.exists()) recordFileParentFile.mkdirs();
            mAudioRecordFile.createNewFile();
            Log.w("Fire", "MediaActivity:170行:" + mAudioRecordFile.getPath());
            //创建文件输出流
            mAudioRecordFos = new FileOutputStream(mAudioRecordFile);
            //配置AudioRecord
            int audioSource = MediaRecorder.AudioSource.MIC;
            //所有android系统都支持
            int sampleRate = 44100;
            //单声道输入
            int channelConfig = AudioFormat.CHANNEL_IN_MONO;
            //PCM_16是所有android系统都支持的
            int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
            //计算AudioRecord内部buffer最小
            int minBufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat);
            //buffer不能小于最低要求，也不能小于我们每次我们读取的大小。
            mAudioRecord = new AudioRecord(audioSource, sampleRate, channelConfig, audioFormat, Math.max(minBufferSize, BUFFER_SIZE));

            mIsAudioRecording = true;
            //开始录音
            mAudioRecord.startRecording();
            //循环读取数据，写入输出流中
            while (mIsAudioRecording) {
                //只要还在录音就一直读取
                int read = mAudioRecord.read(mBuffer, 0, BUFFER_SIZE);
                if (read > 0) {
                    mAudioRecordFos.write(mBuffer, 0, read);
                }
            }
            //退出循环，停止录音，释放资源
            audioDestroy();
        } catch (IOException e) {
            Log.e("Fire", "MediaActivity:163行:" + e.toString());
            mAudioRecord = null;
        }
    }

    private void audioDestroy() {
        if (mAudioRecord != null) {
            mAudioRecord.stop();
            mAudioRecord.release();
            mAudioRecord = null;
            Log.w("Fire", "MediaActivity:213行:Audio 录音" + (System.currentTimeMillis() - mAudioStartTime));
        }
        CloseUtils.closeIO(mAudioRecordFos);
    }

}
