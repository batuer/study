package com.gusi.study.dragshadow;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class DragShadowActivity extends BaseActivity {

  @Override protected int getLayout() {
    return R.layout.activity_drag_shadow;
  }

  @Override protected void initView() {
    initToolBar(mToolbar, true, "DragShadow");
  }
}
