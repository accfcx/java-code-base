package com.accfcx.java.concurrent2;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author accfcx
 * @desc
 */
public class CountDownTest implements Runnable {
    private final static CountDownLatch latch = new CountDownLatch(10);

    @Override
    public void run() {
        try{
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("finish");

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownTest t = new CountDownTest();
        for (int i = 0; i < 10; i++) {
            executorService.submit(t);
        }
        latch.await();
        System.out.println("10 is over");
        executorService.shutdown();
    }
}
