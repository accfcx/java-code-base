package com.accfcx.java.concurrent.ch10;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author accfcx
 * @desc
 * 回环屏障
 *
 * 全部进入屏障点后，先执行 barrier中的task
 * 然后执行最后一个进入屏障点的task
 * 唤醒其它的所有线程
 *
 * CyclicBarrier可以多次重用的关键在于内部有2个字段:parties[线程个数], count[await,递减到0时，重置为parties目的是重用]
 *
 * todo 原理未看
 */
public class CyclicBarrierTest1 {
    private static CyclicBarrier barrier = new CyclicBarrier(2, ()->{
        System.out.println(Thread.currentThread() + " barrier over");
    });

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(()->{
           try{
               System.out.println(Thread.currentThread() + " enter task-1");
               barrier.await();
               System.out.println(Thread.currentThread() + " out task-1");
           }catch (Exception e) {
               e.printStackTrace();
           }
        });

        executorService.submit(()->{
            try{
                System.out.println(Thread.currentThread() + " enter task-2");
                barrier.await();
                System.out.println(Thread.currentThread() + " out task-2");
            }catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}
