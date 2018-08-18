package com.gusi.study.svg;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
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
        TranslateAnimation animation1 = new TranslateAnimation(type1, 0, type1, 2f, type1, 0, type1, 0);
        animation1.setInterpolator(new BounceInterpolator());
        animation1.setRepeatCount(-1);
        animation1.setRepeatMode(Animation.REVERSE);
        animation1.setDuration(2000);
        view1.startAnimation(animation1);

        int type2 = Animation.RELATIVE_TO_PARENT;
        View view2 = findViewById(R.id.view2);
        TranslateAnimation animation2 = new TranslateAnimation(type2, 0, type2, 0.2f, type2, 0, type2, 0);
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

    }
}
