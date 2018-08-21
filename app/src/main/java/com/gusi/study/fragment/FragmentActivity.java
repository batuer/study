package com.gusi.study.fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

public class FragmentActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(mToolbar, true, "Fragment");
    }

    public void fragment1(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_fragment, AFragment.newInstance());
        transaction.commit();
    }

    public void fragment2(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_fragment, BFragment.newInstance());
        transaction.commit();
    }
}
