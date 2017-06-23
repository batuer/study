package com.gusi.study.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;
import com.gusi.study.piechart.Entry;
import com.gusi.study.piechart.PieChartView;
import java.util.ArrayList;

public class PieChartActivity extends BaseActivity {

  @BindView(R.id.tool_bar) Toolbar mToolbar;
  private PieChartView mPieChart;
  private PieChartView mPieChart1;
  private PieChartView mPieChart12;
  private PieChartView mPieChart2;
  private PieChartView mPieChart3;

  @Override protected int getLayout() {
    return R.layout.activity_pie_chart;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolBar(mToolbar, true, "PieChart");

    mPieChart = (PieChartView) findViewById(R.id.pie_chart);
    mPieChart1 = (PieChartView) findViewById(R.id.pie_chart1);
    mPieChart12 = (PieChartView) findViewById(R.id.pie_chart12);
    mPieChart2 = (PieChartView) findViewById(R.id.pie_chart2);
    mPieChart3 = (PieChartView) findViewById(R.id.pie_chart3);

    CompanyInfo companyInfo = new CompanyInfo(20, 0, 0, 0, 0, 0);
    ArrayList tmp = createPieChart(companyInfo);
    mPieChart.setData(tmp, "总共有");

    CompanyInfo companyInfo1 = new CompanyInfo(450, 3, 0, 0, 0, 0);
    ArrayList tmp1 = createPieChart(companyInfo1);
    mPieChart1.setData(tmp1, "总共有");

    CompanyInfo companyInfo12 = new CompanyInfo(600, 0, 0,600, 0, 0);
    ArrayList tmp12 = createPieChart(companyInfo12);
    mPieChart12.setData(tmp12, "总共有");

    CompanyInfo companyInfo2 = new CompanyInfo(0, 0, 0, 50, 200, 10);
    ArrayList tmp2 = createPieChart(companyInfo2);
    mPieChart2.setData(tmp2, "总共有");

    CompanyInfo companyInfo3 = new CompanyInfo(15000, 10, 190, 7, 210, 80);
    ArrayList tmp3 = createPieChart(companyInfo3);
    mPieChart3.setData(tmp3, "总共有");
  }

  private ArrayList createPieChart(CompanyInfo companyInfo) {
    ArrayList tmp = new ArrayList();
    int count = 0;
    if (companyInfo.trademarks_count > 0) {
      count += companyInfo.trademarks_count;
      tmp.add(new Entry("商标", companyInfo.trademarks_count));//trademark
    }
    if (companyInfo.domains_count > 0) {
      count += companyInfo.domains_count;
      tmp.add(new Entry("域名", companyInfo.domains_count));//trademark
    }
    if (companyInfo.patents_count > 0) {
      count += companyInfo.patents_count;
      tmp.add(new Entry("专利", companyInfo.patents_count));//trademark
    }
    if (companyInfo.soft_count > 0) {
      count += companyInfo.soft_count;
      tmp.add(new Entry("软件著作权", companyInfo.soft_count));//trademark
    }
    if (companyInfo.original_count > 0) {
      count += companyInfo.original_count;
      tmp.add(new Entry("原创著作权公司资质认证", companyInfo.original_count));//trademark
    }
    if (companyInfo.certificate_count > 0) {
      count += companyInfo.certificate_count;
      tmp.add(new Entry("公司资质认证", companyInfo.certificate_count));//trademark
    }
    return tmp;
  }

  class CompanyInfo {
    public int trademarks_count;
    public int domains_count;
    public int patents_count;
    public int soft_count;
    public int original_count;
    public int certificate_count;

    public CompanyInfo(int trademarks_count, int domains_count, int patents_count, int soft_count, int original_count, int certificate_count) {
      this.trademarks_count = trademarks_count;
      this.domains_count = domains_count;
      this.patents_count = patents_count;
      this.soft_count = soft_count;
      this.original_count = original_count;
      this.certificate_count = certificate_count;
    }
  }
}
