package com.psi.psieasymanager.business.reserve.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.gusi.study.R;

/**
 * 作者：${ylw} on 2017-07-05 18:05
 */
public class RightArrowTv extends View {

  private Paint mPaint;
  private BitmapDrawable mRightDrawable;
  private String mText;
  private int mColor;
  private int mTextSize;
  private Rect mBound;

  public RightArrowTv(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RightArrowTv);
    mRightDrawable = (BitmapDrawable) array.getDrawable(R.styleable.RightArrowTv_right_arrow);
    mText = array.getString(R.styleable.RightArrowTv_text);
    mColor = array.getColor(R.styleable.RightArrowTv_text_color, Color.GRAY);
    mTextSize = array.getDimensionPixelSize(R.styleable.RightArrowTv_text_size, 30);
    array.recycle();


    /*
     * 初始化画笔
     */
    mBound = new Rect();
    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.FILL);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //canvas.save();
    mPaint.setTextSize(mTextSize);
    mPaint.getTextBounds(mText, 0, mText.length(), mBound);
    mPaint.setColor(mColor);
    Paint.FontMetricsInt fm = mPaint.getFontMetricsInt();
    if (mRightDrawable != null) {
      int intrinsicHeight = mRightDrawable.getIntrinsicHeight();
      int intrinsicWidth = mRightDrawable.getIntrinsicWidth();

      float startY = getHeight() / 2 - fm.descent + (fm.bottom - fm.top) / 2;
      float startX = (getWidth() - mBound.width() - intrinsicWidth) / 2;
      canvas.drawText(mText, startX, startY, mPaint);

      //canvas.save();
      canvas.translate(startX + mBound.width(), (getHeight() - intrinsicHeight)/2);
      mRightDrawable.setBounds(0,0,intrinsicWidth,intrinsicHeight);
      mRightDrawable.draw(canvas);
      //canvas.restore();
    } else {
      float startY = getHeight() / 2 - fm.descent + (fm.bottom - fm.top) / 2;
      float startX = getWidth() / 2 - mBound.width() / 2;
      canvas.drawText(mText, startX, startY, mPaint);
    }
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
  }
}
