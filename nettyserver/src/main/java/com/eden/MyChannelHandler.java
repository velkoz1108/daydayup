package com.eden;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.BufferedReader;
import java.io.FileReader;

public class MyChannelHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("----------收到消息了Start -------");
        System.out.println(msg);
        System.out.println("----------收到消息了End -------" + System.currentTimeMillis());

        String[] split = msg.split("\n");
        String[] firstLine = split[0].split(" ");
        String method = firstLine[0];
        String url = firstLine[1];
        String httpVersion = firstLine[2];

        System.out.println("method = " + method);
        System.out.println("url = " + url);
        System.out.println("httpVersion = " + httpVersion);


        StringBuffer sb = new StringBuffer("HTTP/1.1 200 OK \n");
        if (url.contains("favicon.ico")) {
            //TODO 图片显示有问题
            sb.append("Content-Type: image/x-icon\n")
                    .append("Content-Length: ").append(946).append("\n")
                    .append("\r\n");

            BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/eden/favicon.ico"));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str);
            }
            bufferedReader.close();
        } else {
            sb.append("Content-Type: text/plain;charset=UTF-8\n")
                    .append("\r\n")
                    .append("Hello World");
        }

        ctx.writeAndFlush(sb.toString());

        ctx.close();

    }
}
