package com.accfcx.java.concurrent.ch2;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author accfcx
 * @desc 非阻塞-硬件保证原子性 - 如果对象的预期值是X，则更新为Y
 * compareAndSwap(obj, X, Y)
 * Unsafe
 * ABA问题-变量的状态值发生了环形转换
 */
public class CASTest {
    private static Unsafe unsafe = Unsafe.getUnsafe();
    private static long offset1;
    static{
        try{
            offset1 = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多线程CAS失败时，while进行重试
     * @param object
     * @param offset
     * @param update
     * @return
     */
    public final long getAndSetLong(Object object, long offset, long update) {
        long value;
        do{
            value = unsafe.getLongVolatile(object, offset);
        }while(!unsafe.compareAndSwapLong(object, offset, value,  update));
        return value;
    }
}
