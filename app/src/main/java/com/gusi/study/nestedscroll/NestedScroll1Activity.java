package com.gusi.study.nestedscroll;

import android.os.Bundle;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

/**
 * @author ylw
 * @Date :
 */

public class NestedScroll1Activity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_nested_scroll1);
  }

  @Override protected int getLayout() {
    return 0;
  }
}
