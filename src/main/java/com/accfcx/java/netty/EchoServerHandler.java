package com.accfcx.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author accfcx
 * @desc
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    public static EchoServerHandler INSTANCE = new EchoServerHandler();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("direct mem? " + in.hasArray());

        int len = in.readableBytes();
        byte[] bytes = new byte[len];
        in.getBytes(0, bytes);

        System.out.println("read str from client: " + new String(bytes, "UTF-8"));

        System.out.println("write before: msg.refCnt: " + in.refCnt());

        //
        in.retain();
        ChannelFuture f = ctx.writeAndFlush(msg);

        f.addListener((listener)-> System.out.println("write after: msg.refCnt:" + in.refCnt()));
        super.channelRead(ctx, msg);
    }
}
