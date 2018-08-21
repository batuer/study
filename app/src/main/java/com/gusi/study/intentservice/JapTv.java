package com.gusi.study.intentservice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Button;

import com.blankj.utilcode.util.SizeUtils;

/**
 * @Author ylw  2018/8/21 12:07
 */
public class JapTv extends Button {

    private final Paint mPaint;

    public JapTv(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(SizeUtils.dp2px(1));
        mPaint.clearShadowLayer();
    }

    @Override
    public void draw(Canvas canvas) {
        int height = getHeight();
        int width = getWidth();
        if (height > 0) {
            int num = 6;
            float radius = height * 1.0f / (2 * num + num + 1);
            Path path = new Path();
            float pathHeight = 0f;
            path.moveTo(0, 0);
            for (int i = 0; i < num; i++) {
                path.lineTo(0, pathHeight);
                pathHeight = pathHeight + radius;
                path.lineTo(0, pathHeight);
                RectF rectF = new RectF(-radius, pathHeight, radius, pathHeight + 2 * radius);
                path.arcTo(rectF, -90, 180);
                pathHeight = pathHeight + 2 * radius;

            }
            path.lineTo(0, height);
            path.lineTo(width, height);
            pathHeight = height;
            for (int i = 0; i < num; i++) {
                pathHeight = pathHeight - radius;
                path.lineTo(width, pathHeight);
                RectF rectF = new RectF(width - radius, pathHeight - 2 * radius, width + radius,
                        pathHeight);
                path.arcTo(rectF, 90, 180);
                pathHeight = pathHeight - 2 * radius;
            }
            path.lineTo(width, 0);
//            path.lineTo(0, 0);
            path.close();
            canvas.clipPath(path);
            super.draw(canvas);
        } else {
            super.draw(canvas);
        }
    }


//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        int height = getHeight();
//        int width = getWidth();
//        int num = 6;
//        if (height > 0) {
//            float radius = height * 1.0f / (2 * num + num + 1);
//            Path path = new Path();
//            float pathHeight = 0f;
//            path.moveTo(0, 0);
//            for (int i = 0; i < num; i++) {
//                path.lineTo(0, pathHeight);
//                pathHeight = pathHeight + radius;
//                path.lineTo(0, pathHeight);
//                RectF rectF = new RectF(-radius, pathHeight, radius, pathHeight + 2 * radius);
//                path.arcTo(rectF, -90, 180);
//                pathHeight = pathHeight + 2 * radius;
//
//            }
//            path.lineTo(0, height);
//            path.lineTo(width, height);
//            pathHeight = height;
//            for (int i = 0; i < num; i++) {
//                pathHeight = pathHeight - radius;
//                path.lineTo(width, pathHeight);
//                RectF rectF = new RectF(width - radius, pathHeight - 2 * radius, width + radius,
//                        pathHeight);
//                path.arcTo(rectF, 90, 180);
//                pathHeight = pathHeight - 2 * radius;
//            }
//            path.lineTo(width, 0);
////            path.lineTo(0, 0);
//            path.close();
//            canvas.drawPath(path, mPaint);
//        }
//
//
//    }
}
