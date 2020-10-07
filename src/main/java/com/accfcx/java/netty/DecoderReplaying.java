package com.accfcx.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author accfcx
 * @desc
 * RelayingDecoder 适合处理简单逻辑
 * ByteToMessageDecoder
 * MessageToMessageDecoder
 *
 * 内置Decoder
 * LineBasedFrameDecoder
 * DelimiterBasedFrameDecoder
 * LengthFieldBasedFrameDecoder
 */
public class DecoderReplaying extends ReplayingDecoder<DecoderReplaying.Status> {
    enum Status {
        PARSE_1,
        PARSE_2,
    }

    private int first;
    private int second;

    public DecoderReplaying() {
        super(Status.PARSE_1);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
//        Integer i = byteBuf.readInt();
//        System.out.println("解码：" + i);
//        list.add(i);
        switch (state()) {
            case PARSE_1 -> {
                first = byteBuf.readInt();
                System.out.println("first: " + first);
                checkpoint(Status.PARSE_2);
                break;
            }
            case PARSE_2 -> {
                second = byteBuf.readInt();
                System.out.println("second: " + second);
                Integer sum = first + second;
                list.add(sum);
                checkpoint(Status.PARSE_1);
                break;
            }

        }
    }
}
