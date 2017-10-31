package com.gusi.study.today;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.gusi.study.R;
import java.util.Random;

/**
 * 作者：${ylw} on 2017-10-30 18:10
 */
public class TodayTabLayoutItemView extends FrameLayout {

  private TextView mTvBg;
  private ClipTextView mTvPre;

  public TodayTabLayoutItemView(@NonNull Context context) {
    super(context);
  }

  public TodayTabLayoutItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public TodayTabLayoutItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    mTvBg.setTextColor(Color.BLACK);
    //mTvPre.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
    //TextViewCompat.setTextAppearance(mTvBg, tabTextAppearance);
    //TextViewCompat.setTextAppearance(mTvPre, tabTextAppearance);
    Random random = new Random();
    int i = random.nextInt(5);
    if (i > 2) {
      mTvPre.setVisibility(VISIBLE);
    } else {
      mTvPre.setVisibility(GONE);
    }
  }

  public void clipPercent(float clipPercent) {
    mTvPre.clipPercent(clipPercent);
  }
}
