package com.gusi.study.formlayout.horizontalweight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.blankj.utilcode.util.SizeUtils;
import com.gusi.study.R;

/**
 * @Author ylw  2018-03-27 10:10
 */
public class HorizontalWeightBorderView extends ViewGroup {

  private int mWeightTotal;
  private Paint mPaint;
  private Path mBorderPath;
  private int mTopBorder;
  private int mBottomBorder;
  private int mLeftBorder;
  private int mRightBorder;
  private int mChildGap;

  public HorizontalWeightBorderView(Context context, AttributeSet attrs) {
    super(context, attrs);
    TypedArray array =
        context.obtainStyledAttributes(attrs, R.styleable.HorizontalWeightBorderView);
    try {
      mWeightTotal = array.getInteger(R.styleable.HorizontalWeightBorderView_weight_total, 0);
      int border = array.getDimensionPixelSize(R.styleable.HorizontalWeightBorderView_border,
          SizeUtils.dp2px(1));
      mTopBorder =
          array.getDimensionPixelOffset(R.styleable.HorizontalWeightBorderView_top_border, border);
      mLeftBorder =
          array.getDimensionPixelOffset(R.styleable.HorizontalWeightBorderView_left_border, border);
      mRightBorder =
          array.getDimensionPixelOffset(R.styleable.HorizontalWeightBorderView_right_border,
              border);
      mBottomBorder =
          array.getDimensionPixelOffset(R.styleable.HorizontalWeightBorderView_bottom_border,
              border);
      mChildGap =
          array.getDimensionPixelOffset(R.styleable.HorizontalWeightBorderView_child_gap, border);
    } finally {
      array.recycle();
    }

    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setColor(Color.BLACK);
    mPaint.setStyle(Paint.Style.STROKE);

    mBorderPath = new Path();
    setWillNotDraw(false);
  }

  //height wrapContent
  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int count = getChildCount();
    if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY && mWeightTotal > 0) {
      int widthSize = MeasureSpec.getSize(widthMeasureSpec);
      int usableWidth = widthSize - mLeftBorder - mRightBorder - (count - 1) * mChildGap;
      int maxChildHeight = 0;
      for (int i = 0; i < count; i++) {
        View child = getChildAt(i);
        LayoutParams params = (LayoutParams) child.getLayoutParams();
        int childWidth = (int) (usableWidth * (float) params.weight / mWeightTotal);
        int widthSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);

        int heightSpec = 0;
        if (params.height == -2) {
          //heightSpec = MeasureSpec.makeMeasureSpec((1 << 30 - 1), MeasureSpec.AT_MOST);
          heightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        } else if (params.height == -1) {
          heightSpec = heightMeasureSpec;
        } else {
          heightSpec = MeasureSpec.makeMeasureSpec(params.height, MeasureSpec.EXACTLY);
        }
        child.measure(widthSpec, heightSpec);
        maxChildHeight = Math.max(maxChildHeight, child.getMeasuredHeight());
      }
      setMeasuredDimension(widthSize, maxChildHeight + mTopBorder + mBottomBorder);
    } else {
      //throw new Exception("不支持");
    }
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int measuredHeight = getMeasuredHeight();
    //layout
    int count = getChildCount();

    int startLeft = mLeftBorder;
    for (int i = 0; i < count; i++) {
      View child = getChildAt(i);
      LayoutParams params = (LayoutParams) child.getLayoutParams();
      int childMeasuredWidth = child.getMeasuredWidth();
      int childMeasuredHeight = child.getMeasuredHeight();

      int top = 0;
      int bottom = 0;
      switch (params.verticalGravity) {
        case LayoutParams.VERTICAL_TOP:
          top = mTopBorder;
          bottom = top + childMeasuredHeight;
          break;
        case LayoutParams.VERTICAL_CENTER:
          top = ((measuredHeight - childMeasuredHeight - mTopBorder - mBottomBorder) / 2)
              + mTopBorder;
          bottom = top + childMeasuredHeight;
          break;
        case LayoutParams.VERTICAL_BOTTOM:
          bottom = measuredHeight - mBottomBorder;
          top = bottom - childMeasuredHeight;
          break;
      }
      child.layout(startLeft, top, startLeft + childMeasuredWidth, bottom);
      startLeft = startLeft + childMeasuredWidth + mChildGap;
    }
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int measuredWidth = getMeasuredWidth();
    int measuredHeight = getMeasuredHeight();
    if (mTopBorder > 0) {
      mBorderPath.reset();
      mBorderPath.moveTo(0, (float) mTopBorder / 2);
      mBorderPath.lineTo(measuredWidth, (float) mTopBorder / 2);
      mPaint.setStrokeWidth(mTopBorder);
      canvas.drawPath(mBorderPath, mPaint);
    }
    if (mRightBorder > 0) {
      mBorderPath.reset();
      mBorderPath.moveTo(measuredWidth - (float) mRightBorder / 2, 0);
      mBorderPath.lineTo(measuredWidth - (float) mRightBorder / 2, measuredHeight);
      mPaint.setStrokeWidth(mRightBorder);
      canvas.drawPath(mBorderPath, mPaint);
    }
    if (mBottomBorder > 0) {
      mBorderPath.reset();
      mBorderPath.moveTo(0, measuredHeight - (float) mBottomBorder / 2);
      mBorderPath.lineTo(measuredWidth, measuredHeight - (float) mBottomBorder / 2);
      mPaint.setStrokeWidth(mBottomBorder);
      canvas.drawPath(mBorderPath, mPaint);
    }
    if (mLeftBorder > 0) {
      mBorderPath.reset();
      mBorderPath.moveTo((float) mLeftBorder / 2, 0);
      mBorderPath.lineTo((float) mLeftBorder / 2, measuredHeight);
      mPaint.setStrokeWidth(mLeftBorder);
      canvas.drawPath(mBorderPath, mPaint);
    }

    mPaint.setStrokeWidth(mChildGap);
    for (int len = getChildCount(), i = 0; i < len; i++) {
      if (i == len - 1) continue;
      View child = getChildAt(i);
      mBorderPath.reset();
      mBorderPath.moveTo(child.getRight() + (float) mChildGap / 2, 0);
      mBorderPath.lineTo(child.getRight() + (float) mChildGap / 2, measuredHeight);
      canvas.drawPath(mBorderPath, mPaint);
    }
  }

  @Override protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
    return p instanceof LayoutParams;
  }

  @Override public LayoutParams generateLayoutParams(AttributeSet attrs) {
    return new LayoutParams(getContext(), attrs);
  }

  @Override protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
    return new LayoutParams(p);
  }

  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    private static final int VERTICAL_TOP = 0;
    private static final int VERTICAL_CENTER = 1;
    private static final int VERTICAL_BOTTOM = 2;

    public int weight;
    public int verticalGravity = VERTICAL_CENTER;

    public LayoutParams(Context c, AttributeSet attrs) {
      super(c, attrs);
      TypedArray array =
          c.obtainStyledAttributes(attrs, R.styleable.HorizontalWeightBorderView_child);
      try {
        weight = array.getInteger(R.styleable.HorizontalWeightBorderView_child_weight, 0);
        verticalGravity =
            array.getInt(R.styleable.HorizontalWeightBorderView_child_vertical_gravity,
                VERTICAL_CENTER);
      } finally {
        array.recycle();
      }
    }

    public LayoutParams(ViewGroup.LayoutParams p) {
      super(p);
    }
  }
}
