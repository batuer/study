package com.gusi.study.threadlocal;

import android.util.Log;
import android.view.View;
import butterknife.OnClick;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class ThreadLocalActivity extends BaseActivity {
  private ThreadLocal<String> mThreadLocal = new ThreadLocal<String>() {
    @Override protected String initialValue() {
      return super.initialValue();
    }
  };

  @Override protected int getLayout() {
    return R.layout.activity_thread_local;
  }

  @Override protected void initView() {
    super.initView();
    initToolBar(mToolbar, true, "ThreadLocal");
  }

  @OnClick(R.id.btn) public void btnClick(View view) {
    mThreadLocal.set(Thread.currentThread().getName());
    mThreadLocal.set(Thread.currentThread().getName() + "(1)");
    Log.w("Fire",
        "ThreadLocalActivity:24行:" + Thread.currentThread().getName() + ":-:" + mThreadLocal.get());

    new Thread("Thread1") {
      @Override public void run() {
        mThreadLocal.set(Thread.currentThread().getName());
        Log.w("Fire", "ThreadLocalActivity:29行:" + Thread.currentThread().getName() + ":-:"
            + mThreadLocal.get());
      }
    }.start();
    new Thread("Thread2") {
      @Override public void run() {
        Log.w("Fire", "ThreadLocalActivity:35行:" + Thread.currentThread().getName() + ":-:"
            + mThreadLocal.get());
      }
    }.start();
  }
}
