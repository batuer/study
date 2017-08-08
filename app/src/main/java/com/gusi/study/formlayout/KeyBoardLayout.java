package com.gusi.study.formlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.gusi.study.R;
import com.orhanobut.logger.Logger;

/**
 * 作者：${ylw} on 2017-08-08 10:37
 */
public class KeyBoardLayout extends ViewGroup {

  private int mVerticalDivide;
  private int mHorizonalDivide;
  private int mDivideColor;
  private int mColumnCount;
  private int mRowCount;
  private final int mHeight;
  private final int mWidth;

  public KeyBoardLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KeyBoardLayout);
    mVerticalDivide =
        array.getDimensionPixelSize(R.styleable.KeyBoardLayout_vertical_divide_space, 0);
    mHorizonalDivide =
        array.getDimensionPixelSize(R.styleable.KeyBoardLayout_horizontal_divide_space, 0);
    mDivideColor = array.getColor(R.styleable.KeyBoardLayout_divide_color, Color.BLACK);

    mColumnCount = array.getInt(R.styleable.KeyBoardLayout_column_count, 0);
    mRowCount = array.getInt(R.styleable.KeyBoardLayout_row_count, 0);

    mHeight = array.getDimensionPixelSize(R.styleable.KeyBoardLayout_height, 0);
    mWidth = array.getDimensionPixelSize(R.styleable.KeyBoardLayout_width, 0);
    array.recycle();
    setWillNotDraw(false);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    if (mColumnCount < 1 || mRowCount < 1 || mHeight < 1 || mWidth < 1) return;
    setMeasuredDimension(mWidth, mHeight);
    //for (int i = 0; i < getChildCount(); i++) {
    //  View view = getChildAt(i);
    //  view.setwid
    //}

  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int childCount = getChildCount();
    int unitWidth = (mWidth - mColumnCount * mVerticalDivide) / childCount;
    int unitHeight = (mHeight - mRowCount * mHorizonalDivide) / childCount;

    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(i);
      LayoutParams params = (LayoutParams) child.getLayoutParams();
      int columnIndex = params.mColumnIndex;
      int rowIndex = params.mRowIndex;
      int horizontalSpan = params.mHorizontalSpan;
      int verticalSpan = params.mVerticalSpan;

      int left = columnIndex * unitWidth + mVerticalDivide * unitWidth;
      int top = rowIndex * unitHeight + mHorizonalDivide * unitHeight;
      int right = left + horizontalSpan * unitWidth + (horizontalSpan - 1) * mVerticalDivide;
      int bottom = top + verticalSpan * unitHeight + (verticalSpan - 1) * mHorizonalDivide;
      Logger.w(
          left + ":--:" + top + ":--:" + right + ":--:" + bottom + ":---:\n" + columnIndex + ":--:"
              + rowIndex + ":--:" + horizontalSpan + ":--:" + verticalSpan);
      child.layout(left, top, right, bottom);
    }
  }

  @Override protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
    return p instanceof LayoutParams;
  }

  @Override public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
    return new LayoutParams(getContext(), attrs);
  }

  @Override protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
    return new LayoutParams(p);
  }

  public static class LayoutParams extends ViewGroup.MarginLayoutParams {

    private int mColumnIndex;
    private int mRowIndex;
    private int mHorizontalSpan;
    private int mVerticalSpan;

    public LayoutParams(Context c, AttributeSet attrs) {
      super(c, attrs);
      TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.KeyBoardLayout);
      mColumnIndex = array.getInt(R.styleable.KeyBoardLayout_Child_columnIndex, 0);
      mRowIndex = array.getInt(R.styleable.KeyBoardLayout_Child_rowIndex, 0);
      mHorizontalSpan = array.getInt(R.styleable.KeyBoardLayout_Child_horizontal_span, 1);
      mVerticalSpan = array.getInt(R.styleable.KeyBoardLayout_Child_vertical_span, 1);
      Logger.i(attrs + ":--:" + mColumnIndex);
      array.recycle();
    }

    public LayoutParams(ViewGroup.LayoutParams source) {
      super(source);
    }
  }
}
