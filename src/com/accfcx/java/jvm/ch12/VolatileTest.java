package com.accfcx.java.jvm.ch12;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author accfcx
 * @desc volatile 关键字保证变量的线程可见性
 * 在线程对变量从主内存中read到工作内存中，拿到的是最新值，
 * 但是在实际计算的时候，不一定是最新值，再write到主内存时，可能出现old value覆盖new value的情况
 *
 * 字节码文件中 race++对于4条字节码指令，字节码指令不一定是原子的，可能由多条机器码指令
 */
public class VolatileTest {
    private static volatile int race = 0;
//    private static AtomicInteger race = new AtomicInteger(0);
    private static final int COUNT = 10;

    public static synchronized  void inc() {
//        (VolatileTest.class) {
            VolatileTest.race++;
//        race.incrementAndGet();
//        }
    }

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        Thread[] threads = new Thread[COUNT];

        for (int i = 0; i < threads.length; i++){
            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    inc();
                }
            });
            threads[i].start();
        }

        if (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println("Value: " + VolatileTest.race + " Time:" + (System.currentTimeMillis() - start));
    }
}
