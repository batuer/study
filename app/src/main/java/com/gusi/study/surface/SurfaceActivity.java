package com.gusi.study.surface;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

/**
 * @Author ylw  2018/6/8 09:25
 */
public class SurfaceActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_surface;
    }

    @Override
    protected void initView() {
        initToolBar(mToolbar, true, "Surface");
    }
}
