package com.gusi.study.surface;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @Author ylw  2018/6/8 09:29
 */
public class SurfaceViewL extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    /**
     * lockCanvas()获取到的Canvas对象还是上次的Canvas对象，并不是一个新的对象。之前的绘图都将被保留，
     * 如果需要擦除，可以在绘制之前通过drawColor()方法来进行清屏
     * <p>
     * 绘制要充分利用SurfaceView的三个回调方法，在surfaceCreate()方法中开启子线程进行绘制。在子线程中，
     * 使用一个while(isRunning)循环来不停地绘制。具体的绘制过程，由lockCanvas()方法进行绘制，并通过
     * unlockCanvasAndPost(mCanvas)进行画布内容的提交
     */

    private SurfaceHolder mSurfaceHolder;
    private volatile boolean isRunning = false;
    private boolean needDraw = false;
    private Canvas mCanvas;

    // 画笔
    Paint mPaint;
    // 路径
    Path mPath;

    private float mLastX, mLastY;//上次的坐标

    public SurfaceViewL(Context context) {
        this(context, null);
    }

    public SurfaceViewL(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SurfaceViewL(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SurfaceViewL(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mSurfaceHolder = getHolder();//得到SurfaceHolder对象
        mSurfaceHolder.addCallback(this);//注册SurfaceHolder
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);//保持屏幕长亮

        //画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStrokeWidth(10f);
        mPaint.setColor(Color.parseColor("#FF4081"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //路径
        mPath = new Path();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        new Thread(this).start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            if (needDraw) {
                performDraw();
            } else {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                needDraw = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - mLastX);
                float dy = Math.abs(y - mLastY);
                if (dx >= 3 || dy >= 3) {
                    mPath.quadTo(mLastX, mLastY, (mLastX + x) / 2, (mLastY + y) / 2);
                }
                mLastX = x;
                mLastY = y;

                needDraw = true;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                needDraw = false;
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.w("Fire", "SurfaceViewL:142行:" + canvas);
    }

    private void performDraw() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            Log.w("Fire", "SurfaceViewL:149行:" + mCanvas);
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
        } finally {
            if (mCanvas != null) {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
