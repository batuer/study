package com.gusi.study.today;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gusi.study.layoutmanager.MyLayoutManager;
import com.gusi.study.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 作者：${ylw} on 2017-10-30 18:27
 */
public class PageFragment extends Fragment {

  private static final String TAG = "FirePage";
  private RecyclerView mRcv;

  public static PageFragment newInstance(String content) {
    Bundle args = new Bundle();
    PageFragment fragment = new PageFragment();
    args.putString("Content", content);
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    mRcv = (RecyclerView) inflater.inflate(R.layout.fragment_pager, container, false);
    return mRcv;
  }

  private String getRandColor() {
    String r, g, b;
    Random random = new Random();
    r = Integer.toHexString(random.nextInt(256)).toUpperCase();
    g = Integer.toHexString(random.nextInt(256)).toUpperCase();
    b = Integer.toHexString(random.nextInt(256)).toUpperCase();
    r = r.length() == 1 ? "0" + r : r;
    g = g.length() == 1 ? "0" + g : g;
    b = b.length() == 1 ? "0" + b : b;
    return "#" + r + g + b;
    //return r + g + b;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    String content = getArguments().getString("Content", "Null Content");
    final List<String> list = new ArrayList<>(20);
    for (int i = 0; i < 100; i++) {
      list.add(content + " : " + i);
    }

    LinearLayoutManager manager = new LinearLayoutManager(getContext());
    //manager.detachView(null);
    //manager.removeAndRecycleView(null,null);

    MyLayoutManager myLayoutManager = new MyLayoutManager();
    mRcv.setLayoutManager(myLayoutManager);
    mRcv.setHasFixedSize(true);
    final LayoutInflater inflater = LayoutInflater.from(getContext());

    mRcv.setAdapter(new RecyclerView.Adapter<VH>() {
      @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);
        Log.e(TAG, "onCreateViewHolder:");
        return new VH(view);
      }

      @Override public void onBindViewHolder(VH holder, int position) {
        Log.w(TAG, "onBindViewHolder:");
        holder.mTv.setText(list.get(position));
        holder.mTv.setGravity(Gravity.CENTER);
      }

      @Override public int getItemCount() {
        return list.size();
      }
    });


  }

  class VH extends RecyclerView.ViewHolder {
    private TextView mTv;

    public VH(View itemView) {
      super(itemView);
      this.mTv = (TextView) itemView;
    }
  }
}
