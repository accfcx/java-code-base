package com.accfcx.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

/**
 * @author accfcx
 * @desc 编码器位于最后一个OutBoundHandler
 * MessageToMessage
 */
public class EncoderMain {


    static class MsgToByteEncoder extends MessageToByteEncoder<Integer> {
        @Override
        protected void encode(ChannelHandlerContext channelHandlerContext, Integer integer, ByteBuf byteBuf) throws Exception {
            System.out.println("encode :" + integer);
            byteBuf.writeInt(integer);
        }
    }


    static class Byte2MsgCodec extends ByteToMessageCodec<Integer> {
        /**
         * Encode Integer->ByteBuf
         */
        @Override
        protected void encode(ChannelHandlerContext channelHandlerContext, Integer integer, ByteBuf byteBuf) throws Exception {

        }

        // Decode ByteBuf->Integer
        @Override
        protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        }
    }
}
