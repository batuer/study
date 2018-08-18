package com.gusi.study.rainbow;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

/**
 * @author ylw
 * @Date 2017年11月7日13:46:52
 */
public class RainbowActivity extends BaseActivity {

  @Override protected int getLayout() {
    return R.layout.activity_rainbow;
  }

  @Override protected void initView() {
    super.initView();
    initToolBar(mToolbar,true,"Rainbow");
  }
}
