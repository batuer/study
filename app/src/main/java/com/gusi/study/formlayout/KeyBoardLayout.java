package com.gusi.study.formlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.gusi.study.R;

/**
 * 作者：${ylw} on 2017-08-08 10:37
 */
public class KeyBoardLayout extends ViewGroup {
  private static final String TAG = "StudyFire";
  private static final String G = ":--:";
  private int mVerticalDivide;
  private int mHorizonalDivide;
  private int mDivideColor;
  private int mColumnCount;
  private int mRowCount;
  private final int mHeight;
  private final int mWidth;
  private final Paint mPaint;
  private final Path mPath;

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

    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setColor(Color.BLACK);
    mPaint.setStrokeWidth(4.0f);

    mPath = new Path();
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    //if (mColumnCount < 1 || mRowCount < 1 || mHeight < 1 || mWidth < 1) return;
    //int childCount = getChildCount();
    //for (int i = 0; i < childCount; i++) {
    //  TextView child = (TextView) getChildAt(i);
    //  LayoutParams params = (LayoutParams) child.getLayoutParams();
    //  int leftMargin = params.leftMargin;
    //  int rightMargin = params.rightMargin;
    //  int topMargin = params.topMargin;
    //  int bottomMargin = params.bottomMargin;
    //  measureChild(child, widthMeasureSpec, heightMeasureSpec);
    //  int width = child.getMeasuredWidth();//padding 已计算在内
    //  int height = child.getMeasuredHeight();
    //  String text = child.getText().toString();
    //}
    //setMeasuredDimension(mWidth, mHeight);

    //获得行 列的 真实高度
    getRealWidthOrHeight(widthMeasureSpec, heightMeasureSpec);
  }

  private void getRealWidthOrHeight(int widthMeasureSpec, int heightMeasureSpec) {
    int childCount = getChildCount();
    int realWidth = 0;
    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    boolean needMeasureWidth = widthMode == MeasureSpec.AT_MOST;
    if (widthMode == MeasureSpec.EXACTLY) {
      realWidth = MeasureSpec.getSize(widthMeasureSpec);
    }
    int realHeight = 0;
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    boolean needMeasureHeight = heightMode == MeasureSpec.AT_MOST;
    if (heightMode == MeasureSpec.EXACTLY) {
      realHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    if (needMeasureHeight || needMeasureWidth) {
      SparseArray<Integer> widthArray = (needMeasureWidth ? new SparseArray<Integer>() : null);
      SparseArray<Integer> heightArray = (needMeasureHeight ? new SparseArray<Integer>() : null);
      for (int i = 0; i < childCount; i++) {
        View child = getChildAt(i);
        measureChild(child, widthMeasureSpec, heightMeasureSpec);
        LayoutParams params = (LayoutParams) child.getLayoutParams();
        if (needMeasureWidth) {
          Integer widthTotal = widthArray.get(params.mRowIndex);
          int childTotalWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
          if (widthTotal == null) {
            widthArray.put(params.mRowIndex, childTotalWidth);
          } else {
            widthTotal += childTotalWidth;
            widthArray.put(params.mRowIndex, widthTotal);
          }
        }

        if (needMeasureHeight) {
          Integer heightTotal = heightArray.get(params.mColumnIndex);
          int childTotalHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
          if (heightTotal == null) {
            heightArray.put(params.mColumnIndex, childTotalHeight);
          } else {
            heightTotal += childTotalHeight;
            heightArray.put(params.mColumnIndex, heightTotal);
          }
        }
      }

      if (needMeasureWidth) {
        int size = widthArray.size();
        for (int i = 0; i < size; i++) {
          Integer rowTotalWidth = widthArray.get(widthArray.keyAt(i));
          realWidth = Math.max(realWidth, rowTotalWidth);
        }
      }

      if (needMeasureHeight) {
        int size = heightArray.size();
        for (int i = 0; i < size; i++) {
          Integer columnTotalHeight = heightArray.get(heightArray.keyAt(i));
          realHeight = Math.max(realHeight, columnTotalHeight);
        }
      }
    }
    setMeasuredDimension(realWidth, realHeight);
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int childCount = getChildCount();

    int unitWidth = getMeasuredWidth() / mColumnCount;
    int unitHeight = getMeasuredHeight() / mRowCount;
    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(i);
      LayoutParams params = (LayoutParams) child.getLayoutParams();
      int columnIndex = params.mColumnIndex;
      int rowIndex = params.mRowIndex;
      int horizontalSpan = params.mHorizontalSpan;
      int verticalSpan = params.mVerticalSpan;

      //int left = columnIndex * unitWidth + mVerticalDivide * unitWidth;
      int left = columnIndex * unitWidth + params.leftMargin;
      //int top = rowIndex * unitHeight + mHorizonalDivide * unitHeight;
      int top = rowIndex * unitHeight + params.topMargin;
      //int right = left + horizontalSpan * unitWidth + (horizontalSpan - 1) * mVerticalDivide;
      int right = left + unitWidth - params.rightMargin;
      //int bottom = top + verticalSpan * unitHeight + (verticalSpan - 1) * mHorizonalDivide;
      int bottom = top + unitHeight - params.bottomMargin;

      //child.setBackgroundColor(ColorUtils.getRandomColor());
      //if (i == 0) {
      //  child.layout(left + 10, top + 10, right - 10, bottom - 10);
      //} else {
      //  child.layout(left, top, right, bottom);
      //}
      child.layout(left, top, right, bottom);
    }
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    int childCount = getChildCount();
    mPath.reset();
    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(i);
      int left = child.getLeft();
      int top = child.getTop();
      int bottom = child.getBottom();
      int right = child.getRight();
      Log.w(TAG, left + G + right + G + top + G + bottom);
      if (left > 0) {
        mPath.moveTo(left, bottom);
      } else {
        mPath.moveTo(left, top);
        mPath.lineTo(left, bottom);
      }
      mPath.lineTo(right, bottom);
      mPath.lineTo(right, top);
      if (top > 0) {

      } else {
        mPath.lineTo(left, top);
      }
    }
    canvas.drawPath(mPath, mPaint);
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
      TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.KeyBoardLayout_Child);
      mColumnIndex = array.getInt(R.styleable.KeyBoardLayout_Child_columnIndex, -1);
      mRowIndex = array.getInt(R.styleable.KeyBoardLayout_Child_rowIndex, -1);
      mHorizontalSpan = array.getInt(R.styleable.KeyBoardLayout_Child_horizontal_span, 0);
      mVerticalSpan = array.getInt(R.styleable.KeyBoardLayout_Child_vertical_span, 0);
      array.recycle();
    }

    public LayoutParams(ViewGroup.LayoutParams source) {
      super(source);
    }
  }
}
