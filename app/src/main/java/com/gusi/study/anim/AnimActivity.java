package com.gusi.study.anim;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class AnimActivity extends BaseActivity {

  @Override protected int getLayout() {
    return R.layout.activity_anim;
  }

  @Override protected void initView() {
    super.initView();
    initToolBar(mToolbar, true, "Anim");
  }
}
