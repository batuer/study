package com.gusi.study.svg;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PointFEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class SvgActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_svg;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(mToolbar, true, "SVG");
        //兼容api16
        ImageView ivAnimatedVector = findViewById(R.id.iv_animated_vector);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            AnimatedVectorDrawable morphing = (AnimatedVectorDrawable) getDrawable(R.drawable.anim);
            ivAnimatedVector.setImageDrawable(morphing);
            if (morphing != null) {
                morphing.start();
            }
        } else {
//            Bitmap bitmap = getBitmapFromVectorDrawable(this, R.drawable.anim);
//            ivAnimatedVector.setImageBitmap(bitmap);
        }

    }

    public Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void anim(View view) {
        ImageView iv = (ImageView) view;
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) iv.getDrawable();
        drawable.start();

    }

    public void trans(View view) {
        TranslateAnimation animation = new TranslateAnimation(0, 200, 0, 0);
        animation.setInterpolator(new BounceInterpolator());
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setDuration(2000);
        view.startAnimation(animation);


        View view1 = findViewById(R.id.view1);
        int type1 = Animation.RELATIVE_TO_SELF;
        TranslateAnimation animation1 = new TranslateAnimation(type1, 0, type1, 2f, type1, 0,
                type1, 0);
        animation1.setInterpolator(new BounceInterpolator());
        animation1.setRepeatCount(-1);
        animation1.setRepeatMode(Animation.REVERSE);
        animation1.setDuration(2000);
        view1.startAnimation(animation1);

        int type2 = Animation.RELATIVE_TO_PARENT;
        View view2 = findViewById(R.id.view2);
        TranslateAnimation animation2 = new TranslateAnimation(type2, 0, type2, 0.2f, type2, 0,
                type2, 0);
        animation2.setInterpolator(new BounceInterpolator());
        animation2.setRepeatCount(-1);
        animation2.setRepeatMode(Animation.REVERSE);
        animation2.setDuration(2000);
        view2.startAnimation(animation2);


        View view3 = findViewById(R.id.view3);
        ValueAnimator animator = ObjectAnimator.ofFloat(view3, "translationX", 0, 200);
        animator.setInterpolator(new BounceInterpolator());
        animator.setRepeatCount(-1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setDuration(2000);
        animator.start();

        View view4 = findViewById(R.id.view4);
        view4.animate()
                .translationX(200)
                .setInterpolator(new BounceInterpolator())
                .setDuration(2000)
                .start();

        keyFrame(findViewById(R.id.objectAnimatorView));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ObjectAnimatorBinding")
    private void evaluator(View view) {
        ObjectAnimator animator = ObjectAnimator.ofObject(view, "position",
                new PointFEvaluator(), new PointF(0, 0), new PointF(1, 1));
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
    }

    private void propertyValuesHolder(View view) {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 0, 1);
        ObjectAnimator.ofPropertyValuesHolder(view, holder1, holder2, holder3)
                .start();
    }

    private void keyFrame(View view) {
        Keyframe keyframe1 = Keyframe.ofFloat(0, 0); // 开始：progress 为 0
        Keyframe keyframe2 = Keyframe.ofFloat(0.5f, 100); // 进行到一半是，progres 为 100
        Keyframe keyframe3 = Keyframe.ofFloat(1, 80); // 结束时倒回到 80
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("progress", keyframe1,
                keyframe2, keyframe3);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, holder);
        animator.setDuration(2000);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        animator.start();
    }
}
