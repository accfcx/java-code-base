package com.accfcx.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;

/**
 * @author accfcx
 * @desc EmbeddedChannel
 */
public class ChannelMock {
    public static void main(String[] args) {
        // Handler
        HandlerLifeCycle handler = new HandlerLifeCycle();
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                embeddedChannel.pipeline().addLast(handler);
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(10);
        channel.writeInbound(buf);
        channel.flush();

        buf.clear();
        buf.writeInt(20);
        channel.writeInbound(buf);
        channel.flush();

        channel.close();
    }
}
