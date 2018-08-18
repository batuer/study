package com.gusi.study.dragshadow;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import com.gusi.study.R;
import java.lang.reflect.Field;

/**
 * @Author ylw  2018-02-23 10:56
 */
public class DragShadowView extends ConstraintLayout {

  private Button mBtnDragShadow;
  private boolean mDraging = false;

  public DragShadowView(Context context, AttributeSet attrs) {
    super(context, attrs);
    setOnDragListener(new DragListener());
    try {
      Class<?> aClass = Class.forName(ViewDebug.class.getName());
      Field field = aClass.getDeclaredField("DEBUG_DRAG");
      field.setAccessible(true);
      field.setBoolean(field, true);
      //field.set(ViewDebug., true);
    } catch (Exception e) {
      Log.e("Fire", "DragShadowView:52行:" + e.toString());
    }
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    mBtnDragShadow = (Button) findViewById(R.id.btn_drag);

    mBtnDragShadow.setOnLongClickListener(new OnLongClickListener() {
      @Override public boolean onLongClick(View view) {
        //触感反馈 忽略系统设置
        int flag = HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING;
        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, flag);
        //设置拖动
        //http://www.cnblogs.com/jerehedu/p/4427588.html
        Intent intent = new Intent();
        intent.putExtra("Name", "name");
        ClipData data = ClipData.newIntent("Label", intent);
        DragShadowBuilder shadow = new DragShadowBuilder(view);
        startDrag(data, shadow, view, DRAG_FLAG_GLOBAL);
        startDragShadow();
        return false;//不消费事件
      }
    });
  }

  //http://m.blog.csdn.net/article/details?id=50956454
  private class DragListener implements OnDragListener {
    @Override public boolean onDrag(View v, DragEvent event) {
      //坐标 setOnDragListener(new DragListener());
      ClipData clipData = event.getClipData();
      int action = event.getAction();
      Log.d("Fire", "DragListener:56行:" + action + ":-:" + event.getX() + ":-:" + event.getY());
      //if (mChangeTableDragging || mCallMoveCopyDragging) { //只处理拖动
      if (true) { //只处理拖动
        float eventY = event.getY();
        float eventX = event.getX();
        switch (action) {
          case DragEvent.ACTION_DRAG_STARTED:
            break;//1. 开始拖拽
          case DragEvent.ACTION_DRAG_ENDED:
            break;//4. 结束拖拽,(获取不到xy坐标,拖放点在View外部)
          case DragEvent.ACTION_DROP:
            break;//3. 结束拖拽,用户释放拖放前，拖放点在View内部，
          case DragEvent.ACTION_DRAG_LOCATION:
            break; //2.拖拽中
          case DragEvent.ACTION_DRAG_ENTERED:
            break;//5.拖拽中(拖拽出父View，又拖回来 or 刚开始拖拽)
          case DragEvent.ACTION_DRAG_EXITED:
            break; //6.拖出View (拖到状态栏...不处理)
          default:
            break;
        }
        return true;
      } else {
        return false;
      }
    }
  }

  private void startDragShadow() {
    mDraging = true;
  }

  private void endDragShadow() {
    mDraging = false;
  }
}
