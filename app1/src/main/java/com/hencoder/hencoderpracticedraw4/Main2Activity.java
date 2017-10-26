package com.hencoder.hencoderpracticedraw4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

public class Main2Activity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);

    final Fl fl = (Fl) findViewById(R.id.fl);

    SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
    final Tv tv = (Tv) findViewById(R.id.tv);
    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.w("Fire", ":--:" + progress);
        tv.change(progress);
        fl.change(progress);
      }

      @Override public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
  }
}
