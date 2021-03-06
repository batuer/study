package com.gusi.study;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.gusi.study.base.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

/**
 * @author ylw   2017-11-14 16:03
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.rcv)
    RecyclerView mRcv;
    private List<ActivityInfo> mActivityInfoList;
    //    List<String> list = new ArrayList<>();

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
        Adapter adapter = new Adapter();
        mRcv.setAdapter(adapter);
        initPermission();
    }

    private void initPermission() {
        if (!PermissionUtils.isGranted(PermissionConstants.STORAGE, PermissionConstants.MICROPHONE)) {
            PermissionUtils.permission(PermissionConstants.STORAGE, PermissionConstants.MICROPHONE)
                    .request();
        }
    }

    private void initData() {
        PackageManager pm = getPackageManager();
        try {
            String packageName = getPackageName();
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            mActivityInfoList = new ArrayList<>();
            for (ActivityInfo activityInfo : packageInfo.activities) {
                if (!activityInfo.name.contains(getPackageName())) continue;
                mActivityInfoList.add(activityInfo);
            }

            Collections.sort(mActivityInfoList, new Comparator<ActivityInfo>() {
                @Override
                public int compare(ActivityInfo o1, ActivityInfo o2) {
                    return o1.name.substring(o1.name.lastIndexOf(".") + 1, o1.name.length())
                            .replace("Activity", "")
                            .compareTo(o2.name.substring(o2.name.lastIndexOf(".") + 1, o2.name.length())
                                    .replace("Activity", ""));
                }
            });
        } catch (PackageManager.NameNotFoundException e) {
        }

    }


    class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Button btn = new Button(MainActivity.this);
            ViewGroup.LayoutParams params =
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            btn.setLayoutParams(params);
            btn.setPadding(0, 10, 0, 10);
            return new RecyclerView.ViewHolder(btn) {

            };
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            final Button btn = (Button) holder.itemView;
            final ActivityInfo activityInfo = mActivityInfoList.get(position);
            String name = activityInfo.name;
            name = name.substring(name.lastIndexOf(".") + 1, name.length())
                    .replace("Activity", "");
            btn.setText(name);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClassLoader classLoader = getClassLoader();
                    try {
                        Class<?> aClass = classLoader.loadClass(activityInfo.name);
                        startActivity(new Intent(MainActivity.this, aClass));
                    } catch (ClassNotFoundException e) {
                        Log.e("Fire", "Adapter:114行:" + e.toString());
                    }

                }
            });
        }

        @Override
        public int getItemCount() {
            return mActivityInfoList.size();
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
