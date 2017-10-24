package com.gusi.study.flippertv;

import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class FlipperTvActivity extends BaseActivity {
  @BindView(R.id.tool_bar) Toolbar mToolbar;

  @Override protected int getLayout() {
    return R.layout.activity_flipper_tv;
  }

  @Override protected void initView() {
    initToolBar(mToolbar, true, "FlipperTv");

  }
}
