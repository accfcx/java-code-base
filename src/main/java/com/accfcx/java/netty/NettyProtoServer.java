package com.accfcx.java.netty;

import com.accfcx.java.protocol.MsgProtos;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

/**
 * @author accfcx
 * @desc
 * Client: Proto.POJO -> byte
 *
 * Server: byte->Proto.POJO
 * ProtobufVarint32FrameDecoder【根据可变长度，找二进制字节码】->ProtobufDecoder[二进制字节码->POJO]->自定义解码器/Handler
 */
public class NettyProtoServer {
    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();

        EventLoopGroup masterLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        try{
            bootstrap.group(masterLoopGroup, workerLoopGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.localAddress(9090);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel){
                    socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                    socketChannel.pipeline().addLast(new ProtobufDecoder(MsgProtos.Msg.getDefaultInstance()));
                    socketChannel.pipeline().addLast(new CustomDecoder());
                }
            });
            ChannelFuture channelFuture = bootstrap.bind().sync();
            System.out.println(channelFuture.channel().localAddress());
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerLoopGroup.shutdownGracefully();
            masterLoopGroup.shutdownGracefully();
        }
    }

    static class CustomDecoder extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            MsgProtos.Msg obj = (MsgProtos.Msg)msg;
            System.out.println("业务处理的数据包：" + obj);
            super.channelRead(ctx, msg);
        }
    }
}
