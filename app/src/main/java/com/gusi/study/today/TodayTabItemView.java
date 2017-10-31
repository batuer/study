package com.gusi.study.today;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.gusi.study.R;

/**
 * 作者：${ylw} on 2017-10-30 18:10
 */
public class TodayTabItemView extends FrameLayout {

  private TextView mTvBg;
  private ClipTextView mTvPre;

  public TodayTabItemView(@NonNull Context context) {
    super(context);
  }

  public TodayTabItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public TodayTabItemView(@NonNull Context context, @Nullable AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    mTvBg = (TextView) findViewById(R.id.tv_bg);
    mTvPre = (ClipTextView) findViewById(R.id.tv_pre);
  }

  public void setAttrs(CharSequence text, int bgColor, int selectedColor, int tabTextAppearance) {
    mTvBg.setText(text);
    mTvPre.setText(text);
    mTvPre.setTextColor(Color.RED);
    mTvBg.setTextColor(Color.BLACK);
    //mTvPre.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
    //TextViewCompat.setTextAppearance(mTvBg, tabTextAppearance);
    //TextViewCompat.setTextAppearance(mTvPre, tabTextAppearance);

  }

  public void clipPercent(float clipPercent, int direction) {
    mTvPre.clipPercent(clipPercent, direction);
  }

  public void setSelectedChange(boolean selected) {
    mTvPre.setSelectedChange(selected);
  }

  //private void startScaleAnimation() {
  //  // 将一个TextView沿垂直方向先从原大小（1f）放大到5倍大小（5f），然后再变回原大小。
  //  //ObjectAnimator anim = ObjectAnimator.ofFloat(this, "scaleX","scaleY", 1f, 5f, 1f);
  //  //
  //  //anim.setDuration(5000);
  //  //
  //  //// 正式开始启动执行动画
  //  //anim.start();
  //  int toSelf = Animation.RELATIVE_TO_SELF;
  //  //float fromX, float toX, float fromY, float toY,int pivotXType, float pivotXValue, int pivotYType, float pivotYValue
  //  ScaleAnimation animation =
  //      new ScaleAnimation(1f, 1.1f, 1f, 1.1f, toSelf, getWidth() / 2, toSelf, getHeight() / 2);
  //  animation.setDuration(500);
  //  animation.setFillAfter(false);
  //  startAnimation(animation);
  //}
}
