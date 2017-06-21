package com.gusi.study.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：${ylw} on 2017-06-21 10:17
 */
public class BaseActivity extends AppCompatActivity {

  private Unbinder mBind;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBind = ButterKnife.bind(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (mBind != null) {
      mBind.unbind();
    }
  }
}
