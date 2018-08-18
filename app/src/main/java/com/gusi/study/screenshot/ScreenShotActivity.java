package com.gusi.study.screenshot;

import android.graphics.Bitmap;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;
import com.gusi.study.utils.BitmapUtils;

public class ScreenShotActivity extends BaseActivity {
  @BindView(R.id.ll_screen) LinearLayout mLlScreen;
  @BindView(R.id.iv_screen) ImageView mIvScreen;

  @Override protected int getLayout() {
    return R.layout.activity_screen_shot;
  }

  @Override protected void initView() {
    super.initView();
    mLlScreen.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override public void onGlobalLayout() {
            Bitmap bitmap = BitmapUtils.getCacheBitmapFromView(mLlScreen);
            if (bitmap != null) {
              Bitmap bitmap1 = BitmapUtils.rotateBitmap(bitmap, 90);
              mIvScreen.setImageBitmap(bitmap1);
            }
          }
        });
  }
}
