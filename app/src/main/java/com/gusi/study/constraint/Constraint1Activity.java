package com.gusi.study.constraint;

import android.support.constraint.Placeholder;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class Constraint1Activity extends BaseActivity {
  @BindView(R.id.place_holder) Placeholder mPlaceholder;

  @Override protected int getLayout() {
    return R.layout.activity_constraint1;
  }

  @Override protected void initView() {
    initToolBar(mToolbar, true, "Constraint1");
  }

  @OnClick(R.id.tv_4) public void tv4Click(View view) {
    mPlaceholder.setContentId(view.getId());
  }

  @OnClick(R.id.tv_5) public void tv5Click(View view) {
    mPlaceholder.setContentId(view.getId());
  }

  @OnClick(R.id.place_holder) public void placeHolderClick(View view) {
    mPlaceholder.setContentId(R.id.place_holder);
  }
}
