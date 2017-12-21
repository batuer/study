package com.gusi.study.constraint;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

/**
 * @author ylw
 * @Date
 */
public class ConstraintActivity extends BaseActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (mToolbar != null) {
      initToolBar(mToolbar, true, "Constraint");
    }
  }

  @Override protected int getLayout() {
    return R.layout.activity_constraint;
  }
}
