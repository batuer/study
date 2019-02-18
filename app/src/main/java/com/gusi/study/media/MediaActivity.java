package com.gusi.study.media;

import android.os.Environment;
import android.view.View;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

import java.io.File;
import java.util.List;

public class MediaActivity extends BaseActivity {

    private Audio mAudio;
    private Media mMedia;

    @Override
    protected int getLayout() {
        return R.layout.activity_media;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(mToolbar, true, "Record");
        mAudio = new Audio();
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
                PCM.mixAudios(mixFileList.toArray(new File[mixFileList.size()]), destFile);

                mAudio.play(destFile);
            }
        }).start();
    }

}
