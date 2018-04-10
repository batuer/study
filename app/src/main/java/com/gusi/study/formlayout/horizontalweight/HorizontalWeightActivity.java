package com.gusi.study.formlayout.horizontalweight;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class HorizontalWeightActivity extends BaseActivity {

  @Override protected int getLayout() {
    return R.layout.activity_horiaontal_weight;
  }

  @Override protected void initView() {
    super.initView();
    initToolBar(mToolbar, true, "HorizontalWeight");
    //RecyclerView rcv = (RecyclerView) findViewById(R.id.rcv);
    //rcv.setLayoutManager(new LinearLayoutManager(this));
    //rcv.setAdapter(new Adapter());
  }

  class Adapter extends RecyclerView.Adapter<ViewHolder> {

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = getLayoutInflater().inflate(R.layout.item_horizontal_weight, parent, false);
      return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
      holder.mTv1.setText("Tv1 : " + position);
      holder.mTv2.setText("Tv2 : " + position);
      holder.mTv3.setText(holder.mTv3.getText() + " Tv3 : " + position);
      holder.mTv4.setText("Tv4 : " + position);
    }

    @Override public int getItemCount() {
      return 20;
    }
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    private TextView mTv1, mTv2, mTv3, mTv4;

    public ViewHolder(View itemView) {
      super(itemView);
      mTv1 = (TextView) itemView.findViewById(R.id.tv_1);
      mTv2 = (TextView) itemView.findViewById(R.id.tv_2);
      mTv3 = (TextView) itemView.findViewById(R.id.tv_3);
      mTv4 = (TextView) itemView.findViewById(R.id.tv_4);
    }
  }
}
