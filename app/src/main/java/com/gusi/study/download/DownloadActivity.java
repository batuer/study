package com.gusi.study.download;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;

public class DownloadActivity extends BaseActivity {

    @BindView(R.id.et_url)
    EditText mEtUrl;
    @BindView(R.id.tv_head)
    TextView mTvHead;
    @BindView(R.id.tv_msg)
    TextView mTvMsg;
    private DownLoad mDownLoad;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault()
                .unregister(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_download;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(mToolbar, true, "Download");
        mDownLoad = new DownLoad();
        EventBus.getDefault()
                .register(this);
        File cache = new File(Environment.getExternalStorageDirectory(), "ACache");
        if (!cache.exists()) {
            cache.mkdirs();
        }
        Log.w("Fire", "DownLoad:32行:" + cache.getAbsolutePath());
        Log.w("Fire", "DownloadActivity:57行:" + cache.getPath());
    }

    public void download(View view) {
        final String url = mEtUrl.getText()
                .toString()
                .trim();
        if (TextUtils.isEmpty(url)) {
            ToastUtils.showShort("下载地址不能为空!");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                mDownLoad.downloadFilePlus(url);
            }
        }).start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void downloadEvent(DownloadEvent event) {
        if (event.mEventType == DownloadEvent.HANDLER_HEAD) {
            mTvHead.setText(event.mMsg);
        }
        if (event.mEventType == DownloadEvent.HANDLER_MSG) {
            mTvMsg.setText(event.mMsg);
        }
    }

}
