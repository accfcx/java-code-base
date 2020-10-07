package com.accfcx.java.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author accfcx
 * @desc
 */
public class DecoderHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Integer i = (Integer)msg;
        System.out.println("收到:" + i);
        super.channelRead(ctx, msg);
    }
}
