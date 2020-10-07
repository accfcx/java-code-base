package com.accfcx.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author accfcx
 * @desc
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    public static EchoClientHandler INSTANCE = new EchoClientHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        int len = byteBuf.readableBytes();
        byte[] arr = new byte[len];
        byteBuf.getBytes(0, arr);
        System.out.println("接受到:" + new String(arr, "UTF-8"));
//        byteBuf.release();
        super.channelRead(ctx, msg);
    }
}
