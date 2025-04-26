package com.generals.handlerstudy;

/**
 * @Desc : Message类
 * @Author : zzx
 * @Date : 2025/4/26 11:43
 */

public class Message {

    //唯一标识
    public int what;
    //数据
    public Object obj;
    //时间戳
    public long when;
    //下一个节点
    public Message next;
    //将Runnable包装成Message
    public Runnable callback;
    //指向 Message 的发送者，同时也是 Message 的最终处理者
    public Handler target;

}
