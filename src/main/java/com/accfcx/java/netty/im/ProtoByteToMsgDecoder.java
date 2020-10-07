package com.accfcx.java.netty.im;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author accfcx
 * @desc
 * Head-Content复杂数据包
 * 二进制ByteBuf->Pojo
 * 长度 + 其它字段【版本、魔数】 + 内容
 */
public class ProtoByteToMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

    }
}
