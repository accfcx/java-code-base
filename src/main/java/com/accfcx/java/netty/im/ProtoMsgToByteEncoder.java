package com.accfcx.java.netty.im;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author accfcx
 * @desc
 * 复杂数据包-Head-Content
 * Pojo->ByteBuf
 * 字节码长度 + 其它字段[版本、魔数] + 字节码内容
 */
public class ProtoMsgToByteEncoder extends MessageToByteEncoder<Integer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Integer integer, ByteBuf byteBuf) throws Exception {

    }
}
