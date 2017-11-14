package com.gusi.study.nestedscroll;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ylw   2017-11-14 15:59
 */
public class NestedScroll1Activity extends BaseActivity {
  @BindView(R.id.rcv) RecyclerView mRcv;
  private List<String> mList;
  private LayoutInflater mInflater;
  //@BindView(R.id.lv) ListView mLv;

  @Override protected int getLayout() {
    return R.layout.activity_nested_scroll1;
  }

  @Override protected void initView() {
    super.initView();
    initToolBar(mToolbar, true, "NestedScroll");
    mInflater = getLayoutInflater();

    mRcv.setHasFixedSize(true);
    mList = new ArrayList<>(20);
    for (int i = 0; i < 20; i++) {
      mList.add("Item : " + i);
    }
    //mLv.setAdapter(new BaseAdapter() {
    //  @Override public int getCount() {
    //    return mList.size();
    //  }
    //
    //  @Override public Object getItem(int position) {
    //    return mList.get(position);
    //  }
    //
    //  @Override public long getItemId(int position) {
    //    return position;
    //  }
    //
    //  @Override public View getView(int position, View convertView, ViewGroup parent) {
    //    if (convertView == null) {
    //      convertView = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
    //    }
    //    TextView tv = (TextView) convertView;
    //    tv.setText(mList.get(position));
    //    return convertView;
    //  }
    //});

    mRcv.setLayoutManager(new LinearLayoutManager(this));
    mRcv.setAdapter(new Adapter());
  }

  class Adapter extends RecyclerView.Adapter<VH> {

    @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
      return new VH(view);
    }

    @Override public void onBindViewHolder(VH holder, int position) {
      TextView tv = (TextView) holder.itemView;
      tv.setText(mList.get(position));
    }

    @Override public int getItemCount() {
      return mList.size();
    }
  }

  class VH extends RecyclerView.ViewHolder {
    public VH(View itemView) {
      super(itemView);
    }
  }
}
