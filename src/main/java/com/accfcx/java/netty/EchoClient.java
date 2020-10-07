package com.accfcx.java.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * @author accfcx
 * @desc
 */
public class EchoClient {
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup worker = new NioEventLoopGroup();
        try{
            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.remoteAddress("127.0.0.1", 9090);
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(EchoClientHandler.INSTANCE);
                }
            });

            ChannelFuture f = bootstrap.connect();
            f.addListener((ChannelFuture listener)->{
                if (listener.isSuccess()) {
                    System.out.println("CLient 连接成功");
                }else {
                    System.out.println("Client 连接失败");
                }
            });

            f.sync();

            Channel channel = f.channel();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String text = scanner.next();
                byte[] bytes = (System.currentTimeMillis() + text).getBytes();
                ByteBuf byteBuf = channel.alloc().buffer();
                byteBuf.writeBytes(bytes);
                channel.writeAndFlush(byteBuf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
