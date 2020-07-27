package com.accfcx.java.concurrent.ch3;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author accfcx
 * @desc 高并发-多线程CAS竞争原子变量时，只有一个线程成功，
 * 其它线程都会无限循环自旋等待
 *
 * 分为Base + Array[Cell]
 * 以及避免伪共享的 @sun.misc.Contended
 *
 *
 * 1 线程分配给哪个Cell
 * 2 初始化Cell数组
 * 3 扩容Cell数组
 * 4 线程访问Cell冲突解决
 * 5 线程操作Cell的原子性
 */
public class LongAdderTest {
    private static LongAdder adder = new LongAdder();

    private static Object object = new Object();

    public static void main(String[] args) {
        // 10 - 1010
        // 5  -  101
//        System.out.println(11 & 5);
       LongAdderTest.test();
       LongAdderTest.test2();
       LongAdderTest.test3();
    }

    public static void test() {
//        if (cs == null || (m = cs.length - 1) < 0 ||
//                (c = cs[getProbe() & m]) == null ||
//                !(uncontended = c.cas(v = c.value, v + x)))
//            longAccumulate(x, null, uncontended);
        int i = 0;
        done: for (;;) {
            int total = 10;
            if (i != total) {
                System.out.println("继续done for循环: " + i);
                i ++;
            } else {
                break done;
            }
        }
        System.out.println("done over");
    }

    public static void test2() {
        int count = 0;
        Integer[] array = null;
//        while(true) {
//            if (count == 1){
//                return;
//            }
//            if (array == null) {
//                array = new Integer[2];
//                array[0] = 0;
//                array[1] = 2;
//            } else {
//                array = Arrays.copyOf(array, array.length << 1);
//                Arrays.stream(array).forEach(System.out::println);
//            }
//            count ++;
//        }
        array = new Integer[2];
        Integer[] y = array;
        array[0] = 0;
        array[1] = 2;

        y = Arrays.copyOf(array, array.length << 1);
        Arrays.stream(y).forEach(System.out::println);
    }

    public static void test3() {
        synchronized (object) {
            System.out.println("first");
            synchronized (object) {
                System.out.println("second");
            }
        }
    }
}
