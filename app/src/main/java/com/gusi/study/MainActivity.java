package com.gusi.study;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.gusi.study.base.BaseActivity;
import com.gusi.study.flow.FlowActivity;
import com.gusi.study.ui.PieChartActivity;

public class MainActivity extends BaseActivity {
  @BindView(R.id.tool_bar) Toolbar mToolbar;

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

  @OnClick(R.id.btn_flow) public void flowLayout(View view) {
    startActivity(new Intent(this, FlowActivity.class));
  }
}
