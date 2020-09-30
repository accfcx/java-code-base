package com.accfcx.java.io.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author accfcx
 * @desc
 * 属性
 * 创建，视图
 * 字节排序
 */
public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(10);

        System.out.println(bb.get(9));
        bb.array();
//        bb.put((byte)'h');
        bb.put((byte)'e');
        bb.put((byte)'l');
        bb.put((byte)'l');
        bb.put((byte)'o');

        bb.flip();
//        bb.flip();


        System.out.println(bb.get());
        System.out.println(bb.get());
        System.out.println(ByteOrder.nativeOrder());
        System.out.println(bb.order());
        System.out.println(bb.order(ByteOrder.LITTLE_ENDIAN));
        System.out.println(bb.order());
//        System.out.println(bb.put((byte)'y'));
    }
}
