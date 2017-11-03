package com.gusi.study.eventbus;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;
import org.greenrobot.eventbus.EventBus;

public class EventBusActivity extends BaseActivity {

  @Override protected int getLayout() {
    return R.layout.activity_event_bus;
  }

  @Override protected void initView() {
    super.initView();
    EventBus.getDefault().register(this);
    EventBus.getDefault().post(this);
    EventBus.getDefault().unregister(this);
    initToolBar(mToolbar, true, "EventBus");
  }
}
