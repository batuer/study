package com.gusi.study.media;

import android.media.MediaRecorder;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

import java.io.File;
import java.util.List;

public class MediaActivity extends BaseActivity {

    private Audio mAudio;
    private Media mMedia;
    private Audio1 mAudio1;
    private EditText mEtWeight;

    @Override
    protected int getLayout() {
        return R.layout.activity_media;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(mToolbar, true, "Record");
        mEtWeight = findViewById(R.id.et_weight);
        mAudio = new Audio();
        mAudio1 = new Audio1();
        mMedia = new Media();
    }

    public void mediaStart(View view) {
        mMedia.record();
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
        mMedia.stop();
    }

    public void mediaPlay(View view) {
        mMedia.play();
    }


    public void audioStart(View view) {
        mAudio.record();
    }

    public void audioPause(View view) {
    }

    public void audioStop(View view) {
        mAudio.stop();
    }

    public void audioPlay(View view) {
        mAudio.play(mAudio.getAudioRecordFile());
    }

    public void mixPCMPlay(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File destFile = new File(Environment.getExternalStorageDirectory()
                        .getAbsolutePath() +
                        "/recorderDemo/mix" + System.currentTimeMillis() + ".pcm");
                List<File> mixFileList = mAudio.getMixFileList();

                double weight = Double.parseDouble(mEtWeight.getText()
                        .toString()
                        .trim());
                if (weight < 0) {
                    weight = 0.1;
                }
                if (weight > 1) {
                    weight = 1;
                }

                PCM.mixAudios(mixFileList.toArray(new File[mixFileList.size()]), destFile,weight);

                mAudio.play(destFile);
            }
        }).start();


    }

    public void audioSystemStart(View v) {
        mAudio1.record1(MediaRecorder.AudioSource.MIC);
    }

    public void audioSystemStop(View v) {
        mAudio1.stop();
    }

    public void audioSystemPlay(View v) {
        mAudio1.play1(mAudio1.getAudioRecordFile());
    }

}
