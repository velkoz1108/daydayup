package com.eden;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            Bootstrap client = new Bootstrap();

            client.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new MyChannelHandler());
                        }
                    });
            Channel channel = client.connect("192.168.1.184", 42501).sync().channel();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


            while (true) {
                String input = in.readLine();
                System.out.println("input = " + input);
                StringBuffer sb = new StringBuffer();
                sb.append("GET ").append(input).append(" HTTP/1.1\n")
                        .append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3\n")
                        .append("User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.108 Safari/537.36\n")
                        .append("Host: 192.168.1.184:42501\n")
                        .append("Connection: keep-alive\n")
                        .append("Accept-Language: zh-CN,zh;q=0.9,zh-TW;q=0.8\n")
                        .append("Pragma: no-cache\n")
                        .append("Cache-Control: no-cache\n")
                        .append("\r\n");

                channel.writeAndFlush(sb);
            }
        } finally {
            loopGroup.shutdownGracefully();
        }
    }
}
