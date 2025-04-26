package com.generals.handlerstudy;

import android.os.SystemClock;

/**
 * @Desc : Message队列
 * @Author : zzx
 * @Date : 2025/4/26 11:46
 */

public class MessageQueue {

    //链表中的首条消息
    private Message mMessages;

    void enqueueMessage(Message msg, long when) {
        synchronized (this) {
            //用于标记是否需要唤醒 next 方法
            boolean needWake = false;
            Message p = mMessages;
            //如果链表头为空或者时间比当前插入时间戳大，就将msg作为头部
            if(p == null || when == 0 || when < p.when) {
                msg.next = p;
                mMessages = msg;
                //需要唤醒
                needWake = mBlocked;
            } else {
                Message prev;
                //从链表头向链表尾遍历，寻找链表中第一条时间戳比 msg 大的消息，将 msg 插到该消息的前面
                for(;;) {
                    prev = p;
                    p = p.next;
                    if(p == null || when < p.when) {
                        break;
                    }
                }
                msg.next = p;
                prev.next = msg;
            }
            if(needWake) {
                //唤醒 next() 方法
                nativeWake();
            }
        }
    }

    private boolean mBlocked = false;

    Message next() {
        int nextPollTimeoutMills = 0;
        for(;;) {
            nativePollOnce(nextPollTimeoutMills);
            synchronized (this) {
                //获取当前时间
                final long now = SystemClock.uptimeMillis();
                Message msg = mMessages;
                if(msg != null) {
                    if(now < msg.when) {
                        //如果当前时间还未到达消息的的处理时间，那么就计算还需要等待的时间
                        nextPollTimeoutMills = (int) Math.min(msg.when - now, Integer.MAX_VALUE);
                    } else {
                        //可以处理队头的消息了，第二条消息成为队头
                        mMessages = msg.next;
                        msg.next = null;
                        mBlocked = false;
                        return msg;
                    }
                } else {
                    nextPollTimeoutMills = -1;
                }
                mBlocked = true;
            }
        }
    }

    //休眠指定时间
    private void nativePollOnce(long nextPollTimeoutMillis) {

    }

    //唤醒 next() 方法
    private void nativeWake() {

    }

}
