package com.gusi.study.drawable;

import android.os.Bundle;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class DrawableActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolBar(mToolbar, true, "Drawable");
  }

  @Override protected int getLayout() {
    return R.layout.activity_drawable;
  }
}
