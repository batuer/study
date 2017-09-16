package com.gusi.study.granzort;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.gusi.study.R;

/**
 * 作者：${ylw} on 2017-09-12 14:07
 */
public class GranzortView1 extends View {
  private static final String TAG = "Study";
  private Paint mPaint;
  private Path mPath;
  private PathMeasure mPathMeasure;

  private int mState = 0;
  private Path mDrawPath;

  public GranzortView1(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  private Handler mHandler = new Handler(Looper.getMainLooper()) {
    @Override public void handleMessage(Message msg) {
      if (mState == 0) {
        mState = 2;
        valueAnimator.start();
      } else if (mState == 2) {
        mState = 1;
        valueAnimator.start();
      }
    }
  };

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    Log.w(TAG, "--onSizeChanged--");
    initPaint();
    initPath();
    initAnimator();
    valueAnimator.start();
  }

  private ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
  private Animator.AnimatorListener animatorListener;
  private float distance;
  private ValueAnimator valueAnimator;

  private void initAnimator() {
    animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        distance = (float) animation.getAnimatedValue();
        invalidate();
      }
    };

    animatorListener = new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {
      }

      @Override public void onAnimationEnd(Animator animation) {
        mHandler.sendEmptyMessage(0);
        // TODO: 2017-09-14  奇怪 必须用handler
      }

      @Override public void onAnimationCancel(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {
      }
    };

    valueAnimator = ValueAnimator.ofFloat(0, 1).setDuration(3000);

    valueAnimator.addUpdateListener(animatorUpdateListener);

    valueAnimator.addListener(animatorListener);

    //valueAnimator.setInterpolator(new DecelerateInterpolator());
    valueAnimator.setInterpolator(new LinearOutSlowInInterpolator());
  }

  private void initPath() {
    mPath = new Path();
    mPathMeasure = new PathMeasure();
    mDrawPath = new Path();

    int width = getMeasuredWidth();
    int height = getMeasuredHeight();

    mPath.moveTo(0, height / 2);//1起点
    //path.quadTo (float x1, float y1, float x2, float y2)，
    // 其中，x1/y1 对应上图 P1 点，称做控制点，x2/y2 对应 P2 点，就叫他终点把，P0点么就是当前 path 所在的起点。
    mPath.quadTo(width / 2, 0, width, height / 2);
    mPath.quadTo(width / 2, height, 0, height / 2);

    mPathMeasure.setPath(mPath, false);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawColor(Color.WHITE);
    //int height = getMeasuredHeight();
    //int width = getMeasuredWidth();
    //canvas.drawCircle(width/2,height/2,width/2 - 5,mPaint);

    //RectF rectF = new RectF(30, 20, 300, 150);
    //canvas.drawArc(rectF, 0, 360, false, mPaint);

     /*
         * 方法 说明 drawRect 绘制矩形 drawCircle 绘制圆形 drawOval 绘制椭圆 drawPath 绘制任意多边形
         * drawLine 绘制直线 drawPoin 绘制点
         */
    //// 创建画笔
    //Paint p = new Paint();
    //p.setColor(Color.RED);// 设置红色
    //
    //canvas.drawText("画圆：", 10, 20, p);// 画文本
    //canvas.drawCircle(60, 20, 10, p);// 小圆
    //p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
    //canvas.drawCircle(120, 20, 20, p);// 大圆
    //
    //canvas.drawText("画线及弧线：", 10, 60, p);
    //p.setColor(Color.GREEN);// 设置绿色
    //canvas.drawLine(60, 40, 100, 40, p);// 画线
    //canvas.drawLine(110, 40, 190, 80, p);// 斜线
    ////画笑脸弧线
    //p.setStyle(Paint.Style.STROKE);//设置空心
    //RectF oval1=new RectF(150,20,180,40);
    //canvas.drawArc(oval1, 180, 180, false, p);//小弧形
    //oval1.set(190, 20, 220, 40);
    //canvas.drawArc(oval1, 180, 180, false, p);//小弧形
    //oval1.set(160, 30, 210, 60);
    //canvas.drawArc(oval1, 0, 180, false, p);//小弧形
    //
    //canvas.drawText("画矩形：", 10, 80, p);
    //p.setColor(Color.GRAY);// 设置灰色
    //p.setStyle(Paint.Style.FILL);//设置填满
    //canvas.drawRect(60, 60, 80, 80, p);// 正方形
    //canvas.drawRect(60, 90, 160, 100, p);// 长方形
    //
    //canvas.drawText("画扇形和椭圆:", 10, 120, p);
    //    /* 设置渐变色 这个正方形的颜色是改变的 */
    //Shader mShader = new LinearGradient(0, 0, 100, 100,
    //    new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
    //        Color.LTGRAY }, null, Shader.TileMode.REPEAT); // 一个材质,打造出一个线性梯度沿著一条线。
    //p.setShader(mShader);
    //// p.setColor(Color.BLUE);
    //RectF oval2 = new RectF(60, 100, 200, 240);// 设置个新的长方形，扫描测量
    //canvas.drawArc(oval2, 200, 130, true, p);
    //// 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
    ////画椭圆，把oval改一下
    //oval2.set(210,100,250,130);
    //canvas.drawOval(oval2, p);
    //
    //canvas.drawText("画三角形：", 10, 200, p);
    //// 绘制这个三角形,你可以绘制任意多边形
    //Path path = new Path();
    //path.moveTo(80, 200);// 此点为多边形的起点
    //path.lineTo(120, 250);
    //path.lineTo(80, 250);
    //path.close(); // 使这些点构成封闭的多边形
    //canvas.drawPath(path, p);
    //
    //// 你可以绘制很多任意多边形，比如下面画六连形
    //p.reset();//重置
    //p.setColor(Color.LTGRAY);
    //p.setStyle(Paint.Style.STROKE);//设置空心
    //Path path1=new Path();
    //path1.moveTo(180, 200);
    //path1.lineTo(200, 200);
    //path1.lineTo(210, 210);
    //path1.lineTo(200, 220);
    //path1.lineTo(180, 220);
    //path1.lineTo(170, 210);
    //path1.close();//封闭
    //canvas.drawPath(path1, p);
    //    /*
    //     * Path类封装复合(多轮廓几何图形的路径
    //     * 由直线段*、二次曲线,和三次方曲线，也可画以油画。drawPath(路径、油漆),要么已填充的或抚摸
    //     * (基于油漆的风格),或者可以用于剪断或画画的文本在路径。
    //     */
    //
    ////画圆角矩形
    //p.setStyle(Paint.Style.FILL);//充满
    //p.setColor(Color.LTGRAY);
    //p.setAntiAlias(true);// 设置画笔的锯齿效果
    //canvas.drawText("画圆角矩形:", 10, 260, p);
    //RectF oval3 = new RectF(80, 260, 200, 300);// 设置个新的长方形
    //canvas.drawRoundRect(oval3, 20, 15, p);//第二个参数是x半径，第三个参数是y半径
    //
    ////画贝塞尔曲线
    //canvas.drawText("画贝塞尔曲线:", 10, 310, p);
    //p.reset();
    //p.setStyle(Paint.Style.STROKE);
    //p.setColor(Color.GREEN);
    //Path path2=new Path();
    //path2.moveTo(100, 320);//设置Path的起点
    //path2.quadTo(150, 310, 170, 400); //设置贝塞尔曲线的控制点坐标和终点坐标
    //canvas.drawPath(path2, p);//画出贝塞尔曲线
    //
    ////画点
    //p.setStyle(Paint.Style.FILL);
    //p.setColor(Color.YELLOW);
    //canvas.drawText("画点：", 10, 390, p);
    //canvas.drawPoint(60, 390, p);//画一个点
    //canvas.drawPoints(new float[]{60,400,65,400,70,400}, p);//画多个点
    //
    ////画图片，就是贴图
    //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    //canvas.drawBitmap(bitmap, 250,360, p);

    //贝塞尔曲线
    //http://blog.csdn.net/ccy0122/article/details/77427795

    //mPaint.setColor(getResources().getColor(R.color.other));
    //mPaint.setStrokeWidth(10);
    //canvas.drawPoint(width / 2, 5, mPaint);//P1
    //canvas.drawPoint(width - 5, height / 2, mPaint);//P2
    //
    //mPaint.setStrokeWidth(5);
    //mPaint.setColor(Color.YELLOW);
    //mPaint.setStyle(Paint.Style.STROKE);
    //Path path = new Path();
    //path.moveTo(0, height / 2);//1起点
    ////path.quadTo (float x1, float y1, float x2, float y2)，
    //// 其中，x1/y1 对应上图 P1 点，称做控制点，x2/y2 对应 P2 点，就叫他终点把，P0点么就是当前 path 所在的起点。
    //path.quadTo(width / 2, 0, width, height / 2);
    //path.quadTo(width / 2, height, 0, height / 2);
    //canvas.drawPath(path, mPaint);
    mDrawPath.reset();
    if (mState == 0 || mState == 2) {
      //mPathMeasure.setPath(mPath, false);
      float stopD = distance * mPathMeasure.getLength();
      float startD = stopD - (0.5f - Math.abs(0.5f - distance)) * 200;
      mPathMeasure.getSegment(startD, stopD, mDrawPath, true);
      canvas.drawPath(mDrawPath, mPaint);
    } else if (mState == 1) {
      //path 是否自动闭合
      //mPathMeasure.setPath(mPath, false);
      float stop = distance * mPathMeasure.getLength();
      mPathMeasure.getSegment(0, stop, mDrawPath, true);
      canvas.drawPath(mDrawPath, mPaint);
    }
  }

  private void initPaint() {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setColor(getResources().getColor(R.color.other));
    mPaint.setStyle(Paint.Style.STROKE); //画笔风格  填充、描边、描边或填充
    mPaint.setStrokeWidth(5);
    mPaint.setStrokeCap(Paint.Cap.ROUND);
    mPaint.setStrokeJoin(Paint.Join.BEVEL);
    mPaint.setShadowLayer(15, 0, 0, Color.WHITE);//白色光影效果
  }
}
