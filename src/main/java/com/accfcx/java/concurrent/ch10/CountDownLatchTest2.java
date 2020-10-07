package com.accfcx.java.concurrent.ch10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author accfcx
 * @desc
 *
 * 基于AQS实现
 */
public class CountDownLatchTest2 {
    static CountDownLatch downLatch = new CountDownLatch(2);

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(()->{
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                downLatch.countDown();
            }
            System.out.println(Thread.currentThread() + " finish task1" );
        });


        executorService.submit(()->{
            try{
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                downLatch.countDown();
            }
            System.out.println(Thread.currentThread() + " finish task2" );
        });

        downLatch.await();

        System.out.println("main is over");

        executorService.shutdown();
    }
}
