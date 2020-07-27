package com.accfcx.java.concurrent.ch10;

import java.util.concurrent.CountDownLatch;

/**
 * @author accfcx
 * @desc
 */
public class CountDownLatchTest {
    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            try{
                Thread.sleep(1000);
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
            System.out.println("thread 1 is over");
        });


        Thread thread2 = new Thread(()->{
            try{
                Thread.sleep(10000);
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
            System.out.println("thread 2 is over");
        });

        thread1.start();
        thread2.start();

        System.out.println("wait threads over");
        countDownLatch.await();

        System.out.println("main is over");
    }
}
