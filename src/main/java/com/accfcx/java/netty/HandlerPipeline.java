package com.accfcx.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.embedded.EmbeddedChannel;

/**
 * @author accfcx
 * @desc Handler流水线
 */
public class HandlerPipeline {
    public static void main(String[] args) {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                // InBound -双链表 从前往后
                embeddedChannel.pipeline().addLast(new HandlerA());
                embeddedChannel.pipeline().addLast(new HandlerB());
                embeddedChannel.pipeline().addLast(new HandlerC());
                // OutBound - 从后往前
                embeddedChannel.pipeline().addLast(new HandlerOutA());
                embeddedChannel.pipeline().addLast(new HandlerOutB());
                embeddedChannel.pipeline().addLast(new HandlerOutC());

            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);

        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(10);

        channel.writeInbound(byteBuf);
        channel.flush();

        byteBuf.clear();

        byteBuf.writeBoolean(true);
        channel.writeOutbound(byteBuf);
        channel.flush();
    }

    static class HandlerA extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("A read " + msg);
            super.channelRead(ctx, msg);
        }
    }

    static class HandlerB extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("B read " + msg );
            super.channelRead(ctx, msg);
        }
    }

    static class HandlerC extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("C read " + msg);
            super.channelRead(ctx, msg);
        }
    }

    static class HandlerOutA extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("OUtA write" + msg);
            super.write(ctx, msg, promise);
        }
    }

    static class HandlerOutB extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("OUtB write" + msg);
            super.write(ctx, msg, promise);
        }
    }

    static class HandlerOutC extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("OUtC write" + msg);
            super.write(ctx, msg, promise);
        }
    }
}
