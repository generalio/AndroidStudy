package com.generals.handlerstudy;

/**
 * @Desc : 类的描述
 * @Author : zzx
 * @Date : 2025/4/26 22:41
 */

public class Looper {
    final MessageQueue mQueue;

    final Thread mThread;

    private static Looper sMainLooper;

    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();

    private Looper() {
        mQueue = new MessageQueue();
        mThread = Thread.currentThread();
    }

    public static void prepare() {
        if(sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    public static void prepareMainLooper() {
        prepare();
        synchronized (Looper.class) {
            if(sMainLooper != null) {
                throw new IllegalStateException("The main Looper has already been prepared.");
            }
            sMainLooper = myLooper();
        }
    }

    public static Looper getMainLooper() {
        synchronized (Looper.class) {
            return sMainLooper;
        }
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        final Looper me = new Looper();
        if(me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        final MessageQueue queue = me.mQueue;
        for(;;) {
            Message msg = queue.next();
            msg.target.dispatchMessage(msg);
        }
    }
}
