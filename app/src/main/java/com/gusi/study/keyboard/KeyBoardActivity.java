package com.gusi.study.keyboard;

import android.os.Bundle;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;
import com.gusi.study.utils.KeyUtils;

public class KeyBoardActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolBar(mToolbar, true, "KeyBoard");
    new KeyUtils(this);
  }

  @Override protected int getLayout() {
    return R.layout.activity_key_board;
  }
}
