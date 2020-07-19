package com.accfcx.java.concurrent.ch2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author accfcx
 * @desc Unsafe类提供原子性操作-方法都是native方法
 *
 * 1 offset - unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
 * 2 array
 * 3 cas
 * 4 volatile 直接更新
 * 5 park 阻塞线程；中断或者unpark会唤醒
 * 6  不能直接获取Unsafe对象-需要通过反射
 */
public class UnsafeTest {

//    static final Unsafe unsafe = Unsafe.getUnsafe();
    static Unsafe unsafe = null ;
    static long offset = 0;

    volatile long state = 0;

    static{
        try{
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            offset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UnsafeTest unsafeTest = new UnsafeTest();

        Boolean suc = unsafe.compareAndSwapInt(unsafeTest, offset, 0, 100);
        System.out.println(suc + " " + unsafeTest.getState());
    }

    public static Unsafe getUnsafe() {
        return unsafe;
    }

    public static long getOffset() {
        return offset;
    }

    public static void setOffset(long offset) {
        UnsafeTest.offset = offset;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }
}
