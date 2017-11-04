package com.hencoder.hencoderpracticedraw7.practice.practice03;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hencoder.hencoderpracticedraw7.R;

public class Practice03OfObjectLayout extends RelativeLayout {
  Practice03OfObjectView view;
  Button animateBt;

  public Practice03OfObjectLayout(Context context) {
    super(context);
  }

  public Practice03OfObjectLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public Practice03OfObjectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();

    view = (Practice03OfObjectView) findViewById(R.id.objectAnimatorView);
    animateBt = (Button) findViewById(R.id.animateBt);

    animateBt.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        ObjectAnimator animator =
            ObjectAnimator.ofObject(view, "position", new PointFEvaluator(), new PointF(0, 0),
                new PointF(1, 1));
        //animator.setInterpolator(new LinearInterpolator());
        animator.setInterpolator(new SpringInterpolator(new SpringInterpolator.XListener() {
          @Override public void getX(float x, float y) {

          }
        }));

        animator.setEvaluator(new PointFEvaluator());
        animator.setDuration(1000);
        animator.start();
      }
    });
  }

  private class PointFEvaluator implements TypeEvaluator<PointF> {

    // 重写 evaluate() 方法，让 PointF 可以作为属性来做动画
    @Override public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
      //new Point()
      PointF pointF = new PointF();
      pointF.x = startValue.x + fraction * (endValue.x - startValue.x);
      pointF.y = startValue.y + fraction * (endValue.y - startValue.y);
      return pointF;
    }
  }
}
