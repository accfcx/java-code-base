package com.accfcx.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author accfcx
 * @desc
 * ByteBuf->List<Integer>
 * List<Object>
 */
public class DecoderTest extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while (byteBuf.readableBytes() >= 4) {
            int i = byteBuf.readInt();
            System.out.println("decode: " + i);
            list.add(i);
        }

    }
}
