package com.gusi.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.gusi.study.base.BaseActivity;
import com.gusi.study.constraint.ConstraintActivity;
import com.gusi.study.drawable.DrawableActivity;
import com.gusi.study.flow.FlowActivity;
import com.gusi.study.formlayout.FormActivity;
import com.gusi.study.piechart.PieChartActivity;

public class MainActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolBar(mToolbar, true, "Study");
  }

  @Override protected int getLayout() {
    return R.layout.activity_main;
  }

  @OnClick(R.id.btn_pie_chart) public void pieChart(View view) {
    startActivity(new Intent(this, PieChartActivity.class));
  }

  @OnClick(R.id.btn_drawable) public void drawable(View view) {
    startActivity(new Intent(this, DrawableActivity.class));
  }

  @OnClick(R.id.btn_flow) public void flowLayout(View view) {
    startActivity(new Intent(this, FlowActivity.class));
  }

  @OnClick(R.id.btn_constraint) public void constraint(View view) {
    startActivity(new Intent(this, ConstraintActivity.class));
  }

  @OnClick(R.id.btn_form) public void formLayout(View view) {
    startActivity(new Intent(this, FormActivity.class));
  }
}
