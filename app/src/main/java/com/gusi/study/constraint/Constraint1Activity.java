package com.gusi.study.constraint;

import android.support.constraint.Group;
import android.support.constraint.Placeholder;
import android.view.View;
import android.widget.LinearLayout;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class Constraint1Activity extends BaseActivity {
    //@BindView(R.id.place_holder) Placeholder mPlaceholder;

    @Override
    protected int getLayout() {
        return R.layout.activity_constraint1;
    }

    @Override
    protected void initView() {
        initToolBar(mToolbar, true, "Constraint1");
    }

    public void angle(View view) {
        invisibileChild();
        findViewById(R.id.cl_angle).setVisibility(View.VISIBLE);
    }


    public void margin(View view) {
        invisibileChild();
        findViewById(R.id.cl_margin).setVisibility(View.VISIBLE);
    }

    public void gonemargin(View view) {
        findViewById(R.id.view2).setVisibility(View.GONE);
    }

    public void bias(View view) {
        invisibileChild();
        findViewById(R.id.cl_bias).setVisibility(View.VISIBLE);
    }

    public void ratio(View view) {
        invisibileChild();
        findViewById(R.id.cl_ratio).setVisibility(View.VISIBLE);
    }

    public void chains(View view) {
        invisibileChild();
        findViewById(R.id.cl_chains).setVisibility(View.VISIBLE);
    }

    public void optimizer(View view) {
        invisibileChild();
        findViewById(R.id.cl_optimizer).setVisibility(View.VISIBLE);
    }

    public void barrier(View view) {
        invisibileChild();
        findViewById(R.id.cl_barrier).setVisibility(View.VISIBLE);
    }

    public void group(View view) {
        invisibileChild();
        findViewById(R.id.cl_group).setVisibility(View.VISIBLE);
    }

    public void groupClick(View view) {
        Group group = findViewById(R.id.group);
        int visibility = group.getVisibility();
        boolean b = visibility == View.VISIBLE;
        group.setVisibility(b ? View.GONE : View.VISIBLE);

    }

    private void invisibileChild() {
        LinearLayout ll = findViewById(R.id.ll);
        for (int i = 0; i < ll.getChildCount(); i++) {
            if (i > 1) {
                ll.getChildAt(i)
                        .setVisibility(View.GONE);
            }
        }
    }

    public void Placeholder(View view) {
        invisibileChild();
        findViewById(R.id.cl_place_holder).setVisibility(View.VISIBLE);
    }

    public void placeClick(View view) {
        Placeholder placeholder = findViewById(R.id.placeholder);
        boolean b = placeholder.getContent()
                .getId() == R.id.textview;
        placeholder.setContentId(b ? R.id.textview1 : R.id.textview);
    }

    public void GuideLine(View view) {
        invisibileChild();
        findViewById(R.id.cl_guide_line).setVisibility(View.VISIBLE);
    }
}
