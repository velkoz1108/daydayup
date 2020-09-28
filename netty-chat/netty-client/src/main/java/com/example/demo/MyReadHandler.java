package com.example.demo;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/*拦截链的事件模型 没有写好哦*/
public class MyReadHandler extends SimpleChannelHandler {

    public void messageReceived(ChannelHandlerContext ctx, MessageEvent evt) {
        Object message = evt.getMessage();
        // Do something with the received message.
        System.out.println("--------MyReadHandler----------");
        // And forward the event to the next handler.
        ctx.sendUpstream(evt);
    }
}
