package com.gusi.study.formlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.gusi.study.R;
import com.gusi.study.utils.ScreenUtils;
import com.orhanobut.logger.Logger;

/**
 * 作者：${ylw} on 2017-07-18 16:52
 */
public class FormLayout extends ViewGroup {
  private static final String TAG = "Study";
  private float[] mChildWeight;//表格的每列的权重，用来计算每列表格的宽度,如果为空表示平均分配
  private int columnCount;//表格的总列数
  private int rowCount;//表格的总行数
  private int dividerSize = 2;//表格边框的宽度

  private int[] mChildColumnWidth;//表格每列的宽度
  private int[] mChildRowHeight;//表格每行的高度

  private Path mDividerPath;//边框的路径
  private Paint mDividerPaint;//画笔

  public FormLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FormLayout);

    CharSequence[] weightArray = array.getTextArray(R.styleable.FormLayout_column_weight_array);

    setWeight(weightArray);

    columnCount = array.getInt(R.styleable.FormLayout_columnCount, 0);
    rowCount = array.getInt(R.styleable.FormLayout_rowCount, 0);

    int mDividerColor = array.getColor(R.styleable.FormLayout_dividerColor, Color.BLACK);
    dividerSize = array.getDimensionPixelSize(R.styleable.FormLayout_dividerWidth, dividerSize);

    array.recycle();

    mChildColumnWidth = new int[columnCount];
    mChildRowHeight = new int[rowCount];

    mDividerPath = new Path();
    mDividerPaint = new Paint();
    mDividerPaint.setColor(mDividerColor);
    mDividerPaint.setAntiAlias(true);
    mDividerPaint.setStrokeWidth(dividerSize);
    mDividerPaint.setStyle(Paint.Style.STROKE);


    /*
      ViewGroup默认情况下，出于性能考虑，会被设置成WILL_NOT_DROW，这样，ondraw就不会被执行了。
        如果我们想重写一个viewgroup的ondraw方法，有两种方法：
           1，构造函数中，给viewgroup设置一个颜色。
           2，构造函数中，调用setWillNotDraw（false），去掉其WILL_NOT_DRAW flag。
     在viewgroup初始化的时候，它调用了一个私有方法：initViewGroup，它里面会有一句setFlags（WILLL_NOT_DRAW,DRAW_MASK）;
     相当于调用了setWillNotDraw（true），所以说，对于ViewGroup，他就认为是透明的了，如果我们想要重写onDraw，就要调用
     setWillNotDraw（false）
     */

    setWillNotDraw(false);
  }

  private void setWeight(CharSequence[] weightArray) {
    if (weightArray == null) return;
    mChildWeight = new float[weightArray.length];
    for (int i = 0; i < weightArray.length; i++) {
      float w = Float.parseFloat(weightArray[i].toString().trim());
      mChildWeight[i] = w;
    }
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    if (columnCount == 0 || rowCount == 0) return;
    //int mode = MeasureSpec.getMode(widthMeasureSpec);
    int widthSize = MeasureSpec.getSize(widthMeasureSpec);
    //计算每列的宽度
    fillChildWidth(widthSize);

    for (int i = 0; i < mChildRowHeight.length; i++) {
      //计算每行的高度
      mChildRowHeight[i] = getChildAtRowMaxHeight(i);
    }
    final int childCount = getChildCount();

    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(i);
      LayoutParams params = (LayoutParams) child.getLayoutParams();
      //计算总的宽度
      int childWidth = 0;
      for (int j = params.columnIndex; j < (params.columnCount + params.columnIndex); j++) {
        childWidth += mChildColumnWidth[j];
      }
      childWidth += (params.columnCount - 1) * dividerSize;
      //计算总的高度
      int childHeight = 0;
      for (int j = params.rowIndex; j < (params.rowIndex + params.rowCount); j++) {
        childHeight += mChildRowHeight[j];
      }
      childHeight += (params.rowCount - 1) * dividerSize;

      int childWidthMeasureSpec =
          getChildMeasureSpec(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
              getPaddingLeft() + getPaddingRight() + params.leftMargin + params.rightMargin,
              childWidth);
      int childHeightMeasureSpec =
          getChildMeasureSpec(MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY),
              getPaddingTop() + getPaddingBottom() + params.topMargin + params.bottomMargin,
              childHeight);
      child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }
    //计算布局的总高度
    int allHeight = 0;
    for (int h : mChildRowHeight) {
      allHeight += h;
    }
    allHeight += (rowCount + 1) * dividerSize;
    allHeight += getPaddingBottom() + getPaddingTop();
    super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(allHeight, MeasureSpec.EXACTLY));
  }

  /**
   * 获取每行的最大的高度
   *
   * @param row 行数
   * @return 最大高度
   */
  private int getChildAtRowMaxHeight(int row) {
    final int childCount = getChildCount();
    int maxHeight = 0;
    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(i);
      LayoutParams params = (LayoutParams) child.getLayoutParams();
      //Log.i(TAG, params.rowIndex + " :--: " + params.rowCount + " :--: " + row);
      //Log.w(TAG, ((row >= params.rowIndex && row < (params.rowIndex + params.rowCount))+"--"+child));
      if (row >= params.rowIndex && row < (params.rowIndex + params.rowCount)) { //表示在获取范围内
        int childWidth = 0;
        for (int j = params.columnIndex; j < (params.columnCount + params.columnIndex); j++) {
          childWidth += mChildColumnWidth[j]; //计算所占列的总宽度
        }
        childWidth += (params.columnCount - 1) * dividerSize; //计算总的宽度

        int childHeight = getChildMeasureHeight(child, params, childWidth);
        int rowHeight =
            (childHeight - (params.rowCount - 1) * dividerSize) / params.rowCount;  //把宽度分给所占行的高度
        maxHeight = Math.max(maxHeight, rowHeight);
      }
    }
    return maxHeight;
  }

  /**
   * 获取行高
   *
   * @param child 子view
   * @param lp 子view的LayoutParams
   * @param childWidth 子view的宽度
   * @return 获取子view的高度
   */
  private int getChildMeasureHeight(View child, LayoutParams lp, int childWidth) {
    int childWidthMeasureSpec =
        getChildMeasureSpec(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
            getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin, childWidth);
    int childHeightMeasureSpec =
        getChildMeasureSpec(MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.UNSPECIFIED),
            getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    return child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
  }

  /**
   * 计算每列的位置
   *
   * @param widthSize 宽度
   */
  private void fillChildWidth(int widthSize) {
    int paddingLeft = getPaddingLeft();
    int paddingRight = getPaddingRight();
    widthSize = widthSize - paddingLeft - paddingRight - (columnCount + 1) * dividerSize;
    if (mChildWeight != null && mChildWeight.length == mChildColumnWidth.length) {
      float allWeight = 0.0f;
      for (float w : mChildWeight) {
        allWeight += w;
      }
      float weightStep = widthSize / allWeight;
      for (int i = 0; i < mChildWeight.length; i++) {
        mChildColumnWidth[i] = Math.round(mChildWeight[i] * weightStep);
      }
    } else {
      float weightStep = widthSize / mChildColumnWidth.length;
      for (int i = 0; i < mChildColumnWidth.length; i++) {
        mChildColumnWidth[i] = Math.round(weightStep);
      }
    }
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    final int childTop = getPaddingTop() + dividerSize;
    final int childLeft = getPaddingLeft() + dividerSize;
    final int count = getChildCount();

    for (int i = 0; i < count; i++) {
      View child = getChildAt(i);
      LayoutParams params = (LayoutParams) child.getLayoutParams();
      int t = getCurrentTop(childTop, params.rowIndex) + params.rowIndex * dividerSize;
      int l = getCurrentLeft(childLeft, params.columnIndex) + params.columnIndex * dividerSize;
      int b = t + child.getMeasuredHeight();
      int r = l + child.getMeasuredWidth();
      child.layout(l, t, r, b);
    }
  }

  private int getCurrentTop(int childTop, int rowIndex) {
    for (int i = 0; i < rowIndex; i++) {
      childTop += mChildRowHeight[i];
    }
    return childTop;
  }

  private int getCurrentLeft(int childLeft, int columnIndex) {
    for (int i = 0; i < columnIndex; i++) {
      childLeft += mChildColumnWidth[i];
    }
    return childLeft;
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //画边框
    mDividerPath.reset();
    final int childCount = getChildCount();
    final int halfSize = dividerSize / 2;

    int screenWidth = ScreenUtils.getScreenWidth();

    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(i);
      int left = child.getLeft();
      int top = child.getTop();
      int bottom = child.getBottom();
      int right = child.getRight();
      if (left <= 2){
        left = 3;
      }
      if (top <= 2){
        top = 3;
      }
      if ((screenWidth - right) <= 2){

      }

      mDividerPath.moveTo(left - halfSize, top - halfSize);
      mDividerPath.lineTo(left - halfSize, bottom + halfSize);
      mDividerPath.lineTo(right + halfSize, bottom + halfSize);
      mDividerPath.lineTo(right + halfSize, top - halfSize);
      mDividerPath.lineTo(left - halfSize, top - halfSize);

      Logger.w(left + ":--:" + right + ":--:" + top + ":--:" + bottom+":--:"+halfSize+":--:"+ screenWidth);
    }
    canvas.drawPath(mDividerPath, mDividerPaint);
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

  /**
   * Per child parameters for children views of the {@link FormLayout}.
   */
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    public int rowIndex;
    public int rowCount;
    public int columnIndex;
    public int columnCount;

    public LayoutParams(Context c, AttributeSet attrs) {
      super(c, attrs);
      TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.FormLayout_Layout);
      rowIndex = array.getInt(R.styleable.FormLayout_Layout_layout_rowIndex, 0);
      rowCount = array.getInt(R.styleable.FormLayout_Layout_layout_rowCount, 1);
      columnIndex = array.getInt(R.styleable.FormLayout_Layout_layout_columnIndex, 0);
      columnCount = array.getInt(R.styleable.FormLayout_Layout_layout_columnCount, 1);
      array.recycle();
    }

    public LayoutParams(@Px int width, @Px int height) {
      super(new ViewGroup.LayoutParams(width, height));
    }

    public LayoutParams(LayoutParams source) {
      super(source);
      rowIndex = source.rowIndex;
      rowCount = source.rowCount;
      columnIndex = source.columnIndex;
      columnCount = source.columnCount;
    }

    public LayoutParams(ViewGroup.LayoutParams source) {
      super(source);
    }
  }
}
