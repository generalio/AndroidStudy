package com.generals.handlerstudy;

import android.os.SystemClock;

/**
 * @Desc : 类的描述
 * @Author : zzx
 * @Date : 2025/4/26 21:46
 */

public class Handler {

    private MessageQueue mQueue;

    public Handler(MessageQueue mQueue) {
        this.mQueue = mQueue;
    }

    public final void post(Runnable r) {
        sendMessageDelayed(getPostMessage(r), 0);
    }

    public final void postDelayed(Runnable r, long delayMillis) {
        sendMessageDelayed(getPostMessage(r), delayMillis);
    }

    public final void sendMessage(Message r) {
        sendMessageDelayed(r, 0);
    }

    public final void sendMessageDelayed(Message msg, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        sendMessageAtTime(msg, SystemClock.uptimeMillis() + delayMillis);
    }

    public void sendMessageAtTime(Message msg, long uptimeMillis) {
        msg.target = this;
        mQueue.enqueueMessage(msg, uptimeMillis);
    }

    private static Message getPostMessage(Runnable r) {
        Message m = new Message();
        m.callback = r;
        return m;
    }

    //由外部来重写该方法，以此来消费 Message
    public void handleMessage(Message msg) {

    }

    //用于分发消息
    public void dispatchMessage(Message msg) {
        if (msg.callback != null) {
            msg.callback.run();
        } else {
            handleMessage(msg);
        }
    }

}
