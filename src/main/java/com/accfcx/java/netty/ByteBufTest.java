package com.accfcx.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author accfcx
 * @desc ByteBuf
 */
public class ByteBufTest {
    public static void main(String[] args) {
//        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(9, 100);
//        byteBuf.writeBytes(new byte[]{1,2,3,4});
//        // 不更改readerIndex
//        for (int i = 0; i < byteBuf.readableBytes(); i++) {
//            System.out.println(byteBuf.getByte(i));
//        }
//        // 更改 readerIndex
//        while(byteBuf.isReadable()) {
//            System.out.println(byteBuf.readByte());
//        }

        ByteBufTest.referenceCount();
    }


    /**
     * 引用计数
     */
    static void referenceCount() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        System.out.println("after create " + buf.refCnt());
        buf.retain();
        System.out.println("after first retain " + buf.refCnt());


        buf.release();
        System.out.println("after first release " + buf.refCnt());

        buf.release();
        System.out.println("after second release " + buf.refCnt());

        buf.retain();
    }
}
