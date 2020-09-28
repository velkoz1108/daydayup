package com.example.demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Date;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf buf;


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        buf = ctx.alloc().buffer(4); // (1)
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        buf.release(); // (1)
        buf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg;
        buf.writeBytes(m); // (2)
        m.release();

        if (buf.readableBytes() >= 2) { // (3)
            CharSequence sss = buf.readCharSequence(8, CharsetUtil.US_ASCII);
            System.out.println("short ----"+sss);
            ctx.close();
        }


        /*1、在TCP/IP中，Netty 会把读到的数据放到 ByteBuf 的数据结构中。*/
      /*  ByteBuf m = (ByteBuf) msg; // (1)
        try {
            CharSequence sss = m.readCharSequence(1, CharsetUtil.US_ASCII);
            System.out.println("short ----"+sss);
            System.out.println(System.currentTimeMillis() + "--------channelRead---------" + s);
            ctx.close();
        } finally {
            m.release();
        }*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
