package com.gusi.study.canvas

import com.gusi.study.R
import com.gusi.study.base.BaseActivity

class CanvasActivity : BaseActivity() {
    override fun getLayout() = R.layout.activity_canvas;
    override fun initView() {
        super.initView()
        initToolBar(mToolbar, true, "Canvas")
    }
}
