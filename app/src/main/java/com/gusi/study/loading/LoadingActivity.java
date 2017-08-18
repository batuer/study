package com.gusi.study.loading;

import android.widget.Button;
import butterknife.OnClick;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class LoadingActivity extends BaseActivity {

  @Override protected int getLayout() {
    return R.layout.activity_loading;
  }

  @OnClick(R.id.btn_show_loading) public void showLoading(Button btn) {
      Loading.create(this).show();
  }
}
