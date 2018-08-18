package com.gusi.study.span

import android.text.SpannableString
import com.gusi.study.R
import com.gusi.study.base.BaseActivity
import kotlinx.android.synthetic.main.activity_span.*

class SpanActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_span
    override fun initView() {
        super.initView()
        initToolBar(mToolbar, true, "Span")

        val span = SpannableString("FrameSpanFrameSpanFrameSpanFrameSpanFrameSpanFrameSpan")
        span.setSpan(FrameSpan(), 1, 4, SpannableString.SPAN_EXCLUSIVE_INCLUSIVE)
        tv_frame_span.setText(span)
    }

}
