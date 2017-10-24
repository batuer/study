package com.gusi.study.floating;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;
import butterknife.OnClick;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class FloatingActivity extends BaseActivity {
  public static int OVERLAY_PERMISSION_REQ_CODE = 1234;
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolBar(mToolbar, true, "FloatBall");
  }

  @Override protected int getLayout() {
    return R.layout.activity_floating;
  }

  @OnClick(R.id.btn_floatball) public void floatBall(View view) {
    //目的就是启动Service来打开悬浮窗体
    startService(new Intent(FloatingActivity.this, FloatService.class));
    finish();
  }

  @OnClick(R.id.btn_start_floatball) public void startService(View view) {
    //startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
        Uri.parse("package:" + getPackageName()));
    startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
      if (Settings.canDrawOverlays(this)) {
        //Toast.makeText(FloatingActivity.this, "权限授予成功！", Toast.LENGTH_SHORT).show();
        ////启动FxService
        //startService(floatWinIntent);
      } else {
        Toast.makeText(FloatingActivity.this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
      }
    }
  }
}
