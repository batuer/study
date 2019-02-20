package com.gusi.study.media;

import android.media.MediaRecorder;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AudioActivity extends BaseActivity {


    private TextView mTvPcm;
    private EditText mEtWeight;
    private List<File> mFileList;
    private Audio2 mAudio2;
    private File mMixFile;

    @Override
    protected int getLayout() {
        return R.layout.activity_audio;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(mToolbar, true, "Audio");
        mTvPcm = findViewById(R.id.tv_pcm);
        mEtWeight = findViewById(R.id.et_weight);
        mFileList = new ArrayList<>();
        mAudio2 = new Audio2();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_audio, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_clear) {
            mFileList.clear();
            mTvPcm.setText("");
            File file = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/recorderDemo/");
            if (file.exists()) {
                for (File listFile : file.listFiles()) {
                    listFile.delete();
                }
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startSystemRecord(View view) {
        mAudio2.record(MediaRecorder.AudioSource.REMOTE_SUBMIX);
    }

    public void startMicRecord(View view) {
        mAudio2.record(MediaRecorder.AudioSource.MIC);
    }

    public void stopRecord(View view) {
        mAudio2.stop();
        //
        File recordFile = mAudio2.getAudioRecordFile();
        if (recordFile != null) {
            mFileList.add(recordFile);
            StringBuilder sb = new StringBuilder();
            String s = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/recorderDemo/";
            for (File file1 : mFileList) {
                String path = file1.getPath();
                sb.append(path.replace(s, "") + "\n");
            }
            mTvPcm.setText(sb.toString());
        }

    }


    public void play(View view) {
        File recordFile = mAudio2.getAudioRecordFile();
        if (recordFile != null) {
            mAudio2.play(recordFile);
        } else {
            ToastUtils.showShort("没有可播放的PCM");
        }
    }

    public void stopPlay(View view) {
        mAudio2.stopPlay();
    }

    public void mix(View view) {
        if (mFileList.size() <= 1) {
            ToastUtils.showShort("FileList.size() = " + mFileList.size());
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                double weight = Double.parseDouble(mEtWeight.getText()
                        .toString()
                        .trim());
                if (weight < 0) {
                    weight = 0.1;
                }
                if (weight > 1) {
                    weight = 1;
                }

                mMixFile = new File(Environment.getExternalStorageDirectory()
                        .getAbsolutePath() +
                        "/recorderDemo/mix" + System.currentTimeMillis() + ".pcm");
                PCM.mixAudios(mFileList.toArray(new File[mFileList.size()]), mMixFile, weight);
            }
        }).start();
    }

    public void mixPlay(View view) {
        if (mMixFile == null) {
            ToastUtils.showShort("没有合并的PCM文件:" + mMixFile);
            return;
        }
        mAudio2.play(mMixFile);
    }


}
