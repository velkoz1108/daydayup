package com.eden;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class MyChannelHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("--- 客户端收到消息了start ---");
        System.out.println(msg);
        System.out.println("--- 客户端收到消息了end ---" + System.currentTimeMillis());
    }
}
