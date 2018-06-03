package com.gusi.study.floating;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class FloatingActivity extends BaseActivity {
  public static int OVERLAY_PERMISSION_REQ_CODE = 1234;
  @BindView(R.id.time_tv) TimeTv mTv;

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
    startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
        Uri.parse("package:" + getPackageName()));
    startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
  }

  @OnClick(R.id.btn_trans) public void trans() {
    Intent intent = new Intent(this, Float1Activity.class);
    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
        this,mTv, "timetv");


    intent.putExtra("Key",mTv.getText().toString());
    ContextCompat.startActivity(this, intent, options.toBundle());


  }



  @SuppressLint("NewApi") @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
      if (Settings.canDrawOverlays(this)) {
        //Toast.makeText(FloatingActivity.this, "权限授予成功！", Toast.LENGTH_SHORT).add();
        ////启动FxService
        //startService(floatWinIntent);
      } else {
        Toast.makeText(FloatingActivity.this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
      }
    }
  }
}
