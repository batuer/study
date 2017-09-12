package com.gusi.study.granzort;

import android.os.Bundle;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class GranzortActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolBar(mToolbar, true, "Form");
  }

  @Override protected int getLayout() {
    return R.layout.activity_granzort;
  }
}
