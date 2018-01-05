package com.gusi.study.floating;

import butterknife.BindView;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

/**
 * @Author ylw  2018-01-05 15:58
 */
public class Float1Activity extends BaseActivity {
  @BindView(R.id.time_tv) TimeTv mTv;

  @Override protected int getLayout() {
    return R.layout.activity_float1;
  }

  @Override protected void initView() {
    initToolBar(mToolbar, true, "Float1");
    String extra = getIntent().getStringExtra("Key");
    mTv.setText(extra);
  }
}
