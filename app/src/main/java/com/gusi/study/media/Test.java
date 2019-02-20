package com.gusi.study.media;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;

/**
 * @Author ylw  2019/2/20 21:36
 */
public class Test {
    boolean running;
    Runnable test = new Runnable() {
        public void run() {
            int samp_rate = 8000;
            int min = AudioRecord.getMinBufferSize(samp_rate, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT);
            //用于采集音频源
            AudioRecord record = new AudioRecord(MediaRecorder.AudioSource.MIC, samp_rate,
                    AudioFormat.CHANNEL_OUT_DEFAULT, AudioFormat.ENCODING_PCM_16BIT, min * 10);
            record.startRecording();

            //用于播放音频源
            int maxjitter = AudioTrack.getMinBufferSize(samp_rate, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT);
            AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, samp_rate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, maxjitter * 10, AudioTrack.MODE_STREAM);
            track.play();


            int frame_size = 320;//g726_32 : 4:1的压缩比
            byte[] audioData = new byte[frame_size / 4];
            short[] encodeData = new short[frame_size / 2];
            int num = 0;

            //库函数
            G726Codec codec = new G726Codec();
            short[] putIn = new short[160];

            // int result= 0;
            while (running) {
                num = record.read(encodeData, 0, 160);


                calc1(encodeData, 0, 160);

                int wirteNum = track.write(encodeData, 0, num);
                // if(wirteNum==160)
                // {
                // track.play();
                // }
                //
                // System.arraycopy(encodeData, 0, putIn, 0, 80);
                //
                // num = record.read(encodeData, 0, 160);
                // System.arraycopy(encodeData, 0, putIn, 80, 80);
                //
                // num = record.read(putIn, 0, 160);
                // Log.e(TAG, "num:"+num);
                //
                // if(num == AudioRecord.ERROR_INVALID_OPERATION || num == AudioRecord.ERROR_BAD_VALUE) {
                // Log.e(TAG, "Bad ");
                // continue;
                // }
                //
                // int iRet = codec.encode(encodeData, audioData);//先用G726进行编码
                // Log.e(TAG, "encode iRet:"+iRet);
                //
                // iRet = codec.decode(audioData, encodeData);//然后用g726进行解码
                // Log.e(TAG, "decode iRet:"+iRet);
                //
                // track.write(encodeData, 0, 160);
                //
                // try {
                // Thread.sleep(200);
                // } catch (InterruptedException e) {
                // e.printStackTrace();
                // }
            }

            record.stop();
            record.release();
            record = null;

            track.stop();
            track.release();
            track = null;
        }
    };

    void calc1(short[] lin, int off, int len) {
        int i, j;
        for (i = 0; i < len; i++) {
            j = lin[i + off];
            lin[i + off] = (short) (j >> 2);
        }
    }

//    protected void onDestroy() {
//        super.onDestroy();
//
//        running = false;
//    }
}
