package com.gusi.study.loading;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import butterknife.OnClick;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class LoadingActivity extends BaseActivity {

  @Override protected int getLayout() {
    return R.layout.activity_loading;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolBar(mToolbar, true, "Loading");
  }

  @OnClick(R.id.btn_show_loading) public void showLoading(Button btn) {
      Loading.create(this).show();
  }
}
