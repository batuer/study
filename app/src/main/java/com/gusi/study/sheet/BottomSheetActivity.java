package com.gusi.study.sheet;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class BottomSheetActivity extends BaseActivity {

    private TextView tv;

    @Override
    protected int getLayout() {
        return R.layout.activity_bottom_sheet;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(mToolbar, true, "BottomSheet");
    }

    public void add(final View v) {
        FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
        //LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        // TODO: 2018/6/3  parent 很重要影响
        final View view = getLayoutInflater().inflate(R.layout.layout_bottom, decorView, false);
        //LinearLayout.LayoutParams params =
        //    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        //        ViewGroup.LayoutParams.WRAP_CONTENT);
        //params.gravity = Gravity.BOTTOM;
        view.setId(R.id.bottom_view);
        //FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        decorView.addView(view);
        final View view1 = view.findViewById(R.id.view);

        /*  补间动画 */
        int toSelf = Animation.RELATIVE_TO_SELF;
        Animation animation = new TranslateAnimation(toSelf, 0, toSelf, 0, toSelf, 1.0f, toSelf, 0);
        animation.setDuration(500);
        animation.setInterpolator(new FastOutLinearInInterpolator());
        view1.startAnimation(animation);
    }

    public void add1(final View v) {
        FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
        //LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        // TODO: 2018/6/3  parent 很重要影响
        final View view = getLayoutInflater().inflate(R.layout.layout_bottom, decorView, false);
        //LinearLayout.LayoutParams params =
        //    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        //        ViewGroup.LayoutParams.WRAP_CONTENT);
        //params.gravity = Gravity.BOTTOM;
        view.setId(R.id.bottom_view);
        //FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        decorView.addView(view);
        final View view1 = view.findViewById(R.id.view);

        /*  属性动画 */
        view1.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        ObjectAnimator animation =
                                ObjectAnimator.ofFloat(view1, "translationY", view1.getHeight(), 0);
                        animation.setDuration(500);
                        animation.setInterpolator(new FastOutLinearInInterpolator());
                        //view1.startAnimation(animation);
                        animation.start();
                    }
                });
    }

    public void remove(View v) {
        final FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
        final View view = decorView.findViewById(R.id.bottom_view);
        View view1 = view.findViewById(R.id.view);

        /*  补间动画 */
        int toSelf = Animation.RELATIVE_TO_SELF;
        Animation animation = new TranslateAnimation(toSelf, 0, toSelf, 0, toSelf, 0, toSelf, 1.0f);
        animation.setDuration(500);
        animation.setInterpolator(new FastOutLinearInInterpolator());
        view1.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                decorView.removeView(view);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void remove1(View v) {
        final FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
        final View view = decorView.findViewById(R.id.bottom_view);
        View view1 = view.findViewById(R.id.view);
        /* 属性动画 */

        ObjectAnimator animator = ObjectAnimator.ofFloat(view1, "translationY", 0, view1.getHeight());
        animator.setDuration(500);
        animator.setInterpolator(new FastOutLinearInInterpolator());
        //view1.startAnimation(animation);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                decorView.removeView(view);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void dialog(View v) {
        Dialog dialog = new BottomDialog(this, R.style.dialog_bottom_full);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        View view = View.inflate(this, R.layout.dialog_bottom, null);
//        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        view.measure(0, spec);
        Log.w("Fire", "BottomSheetActivity:155行:" + view.getMeasuredHeight());
        window.setContentView(view);
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        dialog.show();

        tv = (TextView) view.findViewById(R.id.tv);


//        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.w("Fire", "BottomSheetActivity:175行:dispatchTouchEvent 开始");
        boolean b = super.dispatchTouchEvent(ev);
        Log.w("Fire", "BottomSheetActivity:177行:dispatchTouchEvent 结束" + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.w("Fire", "BottomSheetActivity:183行:onTouchEvent 开始");
        boolean b = super.onTouchEvent(event);
        Log.w("Fire", "BottomSheetActivity:185行:onTouchEvent 结束" + b);
        return b;
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv.setText(tv.getText() + "\n" + TimeUtils.getNowString());
            mHandler.sendEmptyMessageDelayed(0, 1000);
        }
    };
}
