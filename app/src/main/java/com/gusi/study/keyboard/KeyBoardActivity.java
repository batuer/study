package com.gusi.study.keyboard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import butterknife.OnClick;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;
import com.gusi.study.utils.KeyUtils;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;

public class KeyBoardActivity extends BaseActivity {

  private List<String> mList;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolBar(mToolbar, true, "KeyBoard");
    new KeyUtils(this);
    initData();
  }

  private void initData() {
    mList = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      mList.add("item" + i);
    }
  }

  @Override protected int getLayout() {
    return R.layout.activity_key_board;
  }

  @OnClick(R.id.btn1) public void btn1(View view) {
    try {
      for (String s : mList) {
        if (s.equals("item5")) {
          mList.remove(s);
        }
      }
    } catch (Exception e) {
      Logger.e(e.toString());
    } finally {
      initData();
    }
  }

  @OnClick(R.id.btn2) public void btn2(View view) {
    try {
      for (int i = 0; i < mList.size(); i++) {
        String s = mList.get(i);
        Log.w("Study", ":--:" + s);
        if (s.equals("item5")) {
          mList.remove(s);
        }
      }
    } catch (Exception e) {
      Logger.e(e.toString());
    } finally {
      initData();
    }
  }

  @OnClick(R.id.btn3) public void btn3(View view) {
    try {
      for (int i = 0; i < 10; i++) {
        String s = mList.get(i);
        if (s.equals("item5")) {
          mList.remove(s);
        }
      }
    } catch (Exception e) {
      Logger.e(e.toString());
    } finally {
      initData();
    }
  }
}
