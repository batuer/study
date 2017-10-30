package com.gusi.study.today;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.gusi.study.R;

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
    mTvBg.setTextColor(bgColor);
    mTvPre.setTextColor(selectedColor);
    TextViewCompat.setTextAppearance(mTvBg, tabTextAppearance);
    TextViewCompat.setTextAppearance(mTvPre, tabTextAppearance);
  }
}
