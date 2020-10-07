package com.accfcx.java.concurrent.ch3;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author accfcx
 * @desc 原子操作类-内部是Unsafe的CAS操作
 *
 * volatile只保证内存可见性，无法做到原子性
 * 锁[lock synchronized]或者无锁[cas] 能保证原子性
 *
 * CAS的jdk实现是基于硬件的类Unsafe来
 *
 * 高并发 AtomicLong存在性能问题
 * JDK8 替代类-LongAdder
 */
public class AtomicLongTest {
    private static AtomicLong atomicLong = new AtomicLong(0);

    private static Integer[] one = {0,1,2,3,0,1};;
    private static Integer[] two = {0,1,2,3,0,1,0,0,0};

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            for (Integer v : one) {
                if (v.intValue() == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        Thread thread2 = new Thread(()->{
            for (Integer v : two) {
                if (v.intValue() == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        thread1.start();
        thread2.start();


        thread1.join();
        thread2.join();

        System.out.println(" NUM: " + atomicLong.get());
    }

}
