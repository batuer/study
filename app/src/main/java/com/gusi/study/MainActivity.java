package com.gusi.study;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.gusi.study.Lottie.LottieActivity;
import com.gusi.study.ScrollTv.ScrollTvActivity;
import com.gusi.study.anim.AnimActivity;
import com.gusi.study.base.BaseActivity;
import com.gusi.study.bigrcv.BigRcvActivity;
import com.gusi.study.canvas.CanvasActivity;
import com.gusi.study.constraint.Constraint1Activity;
import com.gusi.study.constraint.ConstraintActivity;
import com.gusi.study.dragshadow.DragShadowActivity;
import com.gusi.study.drawable.DrawableActivity;
import com.gusi.study.excel.ExcelActivity;
import com.gusi.study.flippertv.FlipperTvActivity;
import com.gusi.study.floating.FloatingActivity;
import com.gusi.study.flow.FlowActivity;
import com.gusi.study.formlayout.FormActivity;
import com.gusi.study.formlayout.horizontalweight.HorizontalWeightActivity;
import com.gusi.study.granzort.GranzortActivity;
import com.gusi.study.intentservice.IntentServiceActivity;
import com.gusi.study.keyboard.KeyBoardActivity;
import com.gusi.study.loading.LoadingActivity;
import com.gusi.study.markdwon.MarkDownActivity;
import com.gusi.study.nestedscroll.NestedScroll1Activity;
import com.gusi.study.nestedscroll.ScrollingActivity;
import com.gusi.study.piechart.PieChartActivity;
import com.gusi.study.protobuf.ProtobufActivity;
import com.gusi.study.rainbow.RainbowActivity;
import com.gusi.study.screenshot.ScreenShotActivity;
import com.gusi.study.sheet.BottomSheetActivity;
import com.gusi.study.span.SpanActivity;
import com.gusi.study.surface.SurfaceActivity;
import com.gusi.study.swipe.SwipeActivity;
import com.gusi.study.threadlocal.ThreadLocalActivity;
import com.gusi.study.today.TodayActivity;
import com.gusi.study.transparent.TransparentActivity;
import com.gusi.study.vlayout.VLayoutActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * @author ylw   2017-11-14 16:03
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.rcv)
    RecyclerView mRcv;
    List<String> list = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolBar(mToolbar, true, "Study");
        initData();
        mRcv.setLayoutManager(new LinearLayoutManager(this));
        mRcv.setHasFixedSize(true);
        mRcv.setAdapter(new Adapter());

    }

    private void initData() {
        list.add("PieChartView");
        list.add("Drawable");
        list.add("FlowLayout");
        list.add("Constraint");
        list.add("Constraint1");
        list.add("FormLayout");
        list.add("ScrollTv");
        list.add("Loading");
        list.add("Granzort");
        list.add("KeyBoard");
        list.add("FloatBall");
        list.add("FlipperTv");
        list.add("TodayTabLayout");
        list.add("NestedScroll");
        list.add("Scroll");
        list.add("Rainbow");
        list.add("VLayout");
        list.add("Span");

        list.add("ThreadLocal");
        list.add("Swipe");
        list.add("Transparent");
        list.add("BigRcv");
        list.add("Canvas");
        list.add("Lottie");
        list.add("DragShadow");
        list.add("ScreenShot");
        list.add("HorizontalWeight");
        list.add("Protobuf");
        list.add("Excel");
        list.add("BottomSheet");
        list.add("Anim");
        list.add("Surface");
        list.add("IntentService");
        list.add("MarkDown");
        list.add("Fragment");

        Collections.sort(list);
    }

    private void itemClick(String txt) {
        Intent intent = new Intent();
        switch (txt) {
            case "PieChartView":
                intent.setClass(this, PieChartActivity.class);
                break;
            case "Drawable":
                intent.setClass(this, DrawableActivity.class);
                break;
            case "FlowLayout":
                intent.setClass(this, FlowActivity.class);
                break;
            case "Constraint":
                intent.setClass(this, ConstraintActivity.class);
                break;
            case "Constraint1":
                intent.setClass(this, Constraint1Activity.class);
                break;
            case "FormLayout":
                intent.setClass(this, FormActivity.class);
                break;
            case "ScrollTv":
                intent.setClass(this, ScrollTvActivity.class);
                break;
            case "Loading":
                intent.setClass(this, LoadingActivity.class);
                break;
            case "Granzort":
                intent.setClass(this, GranzortActivity.class);
                break;
            case "KeyBoard":
                intent.setClass(this, KeyBoardActivity.class);
                break;

            case "FloatBall":
                intent.setClass(this, FloatingActivity.class);
                break;
            case "FlipperTv":
                intent.setClass(this, FlipperTvActivity.class);
                break;
            case "TodayTabLayout":
                intent.setClass(this, TodayActivity.class);
                break;
            case "NestedScroll":
                intent.setClass(this, NestedScroll1Activity.class);
                break;

            case "Scroll":
                intent.setClass(this, ScrollingActivity.class);
                break;
            case "Rainbow":
                intent.setClass(this, RainbowActivity.class);
                break;
            case "VLayout":
                intent.setClass(this, VLayoutActivity.class);
                break;
            case "Span":
                intent.setClass(this, SpanActivity.class);
                break;

            case "ThreadLocal":
                intent.setClass(this, ThreadLocalActivity.class);
                break;
            case "Swipe":
                intent.setClass(this, SwipeActivity.class);
                break;
            case "Transparent":
                intent.setClass(this, TransparentActivity.class);
                break;
            case "BigRcv":
                intent.setClass(this, BigRcvActivity.class);
                break;

            case "Canvas":
                intent.setClass(this, CanvasActivity.class);
                break;
            case "Lottie":
                intent.setClass(this, LottieActivity.class);
                break;
            case "DragShadow":
                intent.setClass(this, DragShadowActivity.class);
                break;
            case "ScreenShot":
                intent.setClass(this, ScreenShotActivity.class);
                break;
            case "HorizontalWeight":
                intent.setClass(this, HorizontalWeightActivity.class);
                break;
            case "Protobuf":
                intent.setClass(this, ProtobufActivity.class);
                break;
            case "Excel":
                intent.setClass(this, ExcelActivity.class);
                break;
            case "BottomSheet":
                intent.setClass(this, BottomSheetActivity.class);
                break;
            case "Anim":
                intent.setClass(this, AnimActivity.class);
                break;
            case "Surface":
                intent.setClass(this, SurfaceActivity.class);
                break;
            case "IntentService":
                intent.setClass(this, IntentServiceActivity.class);
                break;
            case "MarkDown":
                intent.setClass(this, MarkDownActivity.class);
                break;
            case "Fragment":
                intent.setClass(this, com.gusi.study.fragment.FragmentActivity.class);
                break;
        }
        startActivity(intent);
    }

    class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Button btn = new Button(MainActivity.this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            btn.setLayoutParams(params);
            btn.setPadding(0, 10, 0, 10);

            return new RecyclerView.ViewHolder(btn) {

            };
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            final Button btn = (Button) holder.itemView;
            btn.setText(list.get(position));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick(btn.getText().toString());
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
