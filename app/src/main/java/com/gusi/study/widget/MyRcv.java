package com.gusi.study.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @Author ylw  2018/7/10 22:33
 */
public class MyRcv extends RecyclerView {
    public MyRcv(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public void setViewCacheExtension(ViewCacheExtension extension) {
//        super.setViewCacheExtension(extension);
//    }

    class ViewCacheExtension extends RecyclerView.ViewCacheExtension {
        @Override
        public View getViewForPositionAndType(Recycler recycler, int position, int type) {
            return null;
        }
    }

    class RcvAdapter extends RecyclerView.Adapter{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class LvAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
