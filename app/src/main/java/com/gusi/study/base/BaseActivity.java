package com.gusi.study.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.gusi.study.R;
import com.gusi.study.loading.LoadingView;

/**
 * 作者：${ylw} on 2017-06-21 10:17
 */
public abstract class BaseActivity extends AppCompatActivity {

  private Unbinder mBind;
  @Nullable @BindView(R.id.tool_bar) protected Toolbar mToolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayout());
    mBind = ButterKnife.bind(this);
  }

  @LayoutRes protected abstract int getLayout();

  @Override protected void onDestroy() {
    super.onDestroy();
    if (mBind != null) {
      mBind.unbind();
    }
  }

  /**
   * 初始化 Toolbar
   */
  protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
    toolbar.setTitle(title);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
  }

  protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, int resTitle) {
    initToolBar(toolbar, homeAsUpEnabled, getString(resTitle));
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      if (!interceptLoading()) {
        finish();
      }
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      if (interceptLoading()) return true;
    }
    return super.onKeyDown(keyCode, event);
  }

  private boolean interceptLoading() {
    ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
    for (int i = 0; i < decorView.getChildCount(); i++) {
      View childAt = decorView.getChildAt(i);
      if (childAt instanceof LoadingView) {
        if (childAt.getVisibility() == View.VISIBLE) return true;
      }
    }
    return false;
  }
}
