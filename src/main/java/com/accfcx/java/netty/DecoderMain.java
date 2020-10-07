package com.accfcx.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;

/**
 * @author accfcx
 * @desc
 */
public class DecoderMain {
    public static void main(String[] args) {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
//                embeddedChannel.pipeline().addLast(new DecoderTest());
                embeddedChannel.pipeline().addLast(new DecoderReplaying());
                embeddedChannel.pipeline().addLast(new DecoderHandler());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        for (int i = 0; i < 6; i++) {
            ByteBuf byteBuf = Unpooled.buffer();
            byteBuf.writeInt(i);
            channel.writeInbound(byteBuf);
        }
    }
}
