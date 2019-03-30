package com.gusi.study.download;

/**
 * @Author ylw  2019/3/30 11:51
 */
public class DownloadEvent {
    public static final int HANDLER_HEAD = 892;
    public static final int HANDLER_MSG = 893;
    public int mEventType;
    public String mMsg;

    public DownloadEvent(int eventType, String msg) {
        mEventType = eventType;
        mMsg = msg;
    }
}
