package com.gusi.study.widget;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * User: ylw
 * Date: 2016-05-21
 * Time: 18:28
 * FIXME
 */
public class OnRcvItemClickListener implements RecyclerView.OnItemTouchListener {

  private RecyclerView mRcv;
  private final GestureDetectorCompat mGestureDetector;
  private RcvItemClickListener mItemClickListener;
  private RcvItemLongClickListener mItemLongClickListener;

  public OnRcvItemClickListener(RecyclerView rcv, RcvItemClickListener listener) {
    this.mRcv = rcv;
    if (listener instanceof RcvItemLongClickListener) {
      this.mItemLongClickListener = (RcvItemLongClickListener) listener;
    } else {
      this.mItemClickListener = listener;
    }

    Context context = rcv.getContext();
    mGestureDetector = new GestureDetectorCompat(context, new ItemTouchHelperGestureListener());
  }

  @Override public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
    mGestureDetector.onTouchEvent(e);
    return false;
  }

  @Override public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    mGestureDetector.onTouchEvent(e);
  }

  @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

  }

  class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
    @Override public boolean onSingleTapUp(MotionEvent e) {
      View child = mRcv.findChildViewUnder(e.getX(), e.getY());
      if (child != null) {
        RecyclerView.ViewHolder vh = mRcv.getChildViewHolder(child);
        if (mItemClickListener != null) {
          mItemClickListener.onItemClick(vh);
        }
        if (mItemLongClickListener != null) {
          mItemLongClickListener.onItemClick(vh);
        }
      }
      return true;
    }

    @Override public void onLongPress(MotionEvent e) {
      View child = mRcv.findChildViewUnder(e.getX(), e.getY());
      if (child != null) {
        RecyclerView.ViewHolder vh = mRcv.getChildViewHolder(child);
        if (mItemLongClickListener != null) {
          mItemLongClickListener.onItemLongClick(vh);
        }
      }
    }

  }

  public interface RcvItemClickListener {
    void onItemClick(RecyclerView.ViewHolder vh);
  }

  public interface RcvItemLongClickListener extends RcvItemClickListener {
    void onItemLongClick(RecyclerView.ViewHolder vh);
  }
}