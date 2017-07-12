package com.gusi.study.flow;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

public class FlowActivity extends BaseActivity {
  private String[] mVals = new String[] {
      "Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello", "Android", "Weclome",
      "Button ImageView", "TextView", "Helloworld", "Android", "Weclome Hello", "Button Text",
      "TextView"
  };
  private String[] mVals1 = new String[] {
      "Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello", "Android", "Weclome",
      "Button ImageView", "TextView", "Helloworld", "Android", "Weclome Hello", "Button Text",
      "TextView", "Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello", "Android",
      "Weclome", "Button ImageView"
  };

  private String[] mVals2 = new String[] {
      "Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello", "Android", "Weclome",
      "Button ImageView", "TextView", "Helloworld", "Android", "Weclome Hello", "Button Text",
      "TextView"
  };
  @BindView(R.id.tool_bar) Toolbar mToolbar;
  @BindView(R.id.id_flowlayout) TagFlowLayout mFlowLayout;
  @BindView(R.id.id_flowlayout1) TagFlowLayout mFlowLayout1;
  @BindView(R.id.id_flowlayout2) TagFlowLayout mFlowLayout2;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolBar(mToolbar, true, "Flow");
    final LayoutInflater mInflater = LayoutInflater.from(this);
    mFlowLayout.setAdapter(new TagAdapter<String>(mVals) {
      @Override public View getView(FlowLayout parent, int position, String s) {
        TextView tv = (TextView) mInflater.inflate(R.layout.tv, mFlowLayout, false);
        tv.setText(s);
        return tv;
      }
    });

    mFlowLayout1.setMaxSelectCount(3);
    mFlowLayout1.setAdapter(new TagAdapter<String>(mVals1) {

      @Override public View getView(FlowLayout parent, int position, String s) {
        TextView tv = (TextView) mInflater.inflate(R.layout.tv, mFlowLayout1, false);
        tv.setText(s);
        return tv;
      }
    });

    mFlowLayout2.setAdapter(new TagAdapter<String>(mVals2) {

      @Override public View getView(FlowLayout parent, int position, String s) {
        TextView tv = (TextView) mInflater.inflate(R.layout.tv, mFlowLayout2, false);
        tv.setText(s);
        return tv;
      }
    });
  }

  @Override protected int getLayout() {
    return R.layout.activity_flow;
  }
}
