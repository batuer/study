package com.gusi.study.Lottie;

import butterknife.BindView;
import com.airbnb.lottie.LottieAnimationView;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class LottieActivity extends BaseActivity {
  @BindView(R.id.lottie) LottieAnimationView mLottieAnimationView;

  @Override protected int getLayout() {
    return R.layout.activity_lottie;
  }

  @Override protected void initView() {
    super.initView();
    initToolBar(mToolbar, true, "Lottie");

    //lottieAnimationView = (LottieAnimationView) findViewById(R.id.animation_view);
    mLottieAnimationView.setImageAssetsFolder("images");
    mLottieAnimationView.setAnimation("data.json");
    mLottieAnimationView.playAnimation();
  }
}
