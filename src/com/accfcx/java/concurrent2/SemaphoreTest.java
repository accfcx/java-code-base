package com.accfcx.java.concurrent2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author accfcx
 * @desc
 *
 * 一个锁是关联多个线程，控制访问同一段代码的行为
 */
public class SemaphoreTest implements Runnable {
    private Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {

        try{
            semaphore.acquire();
            Thread.sleep(10000);
            System.out.println(Thread.currentThread() + " done " + this);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    @Override
    public String toString() {
        return "SemaphoreTest{" +
                "semaphore=" + semaphore +
                '}';
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        final SemaphoreTest test = new SemaphoreTest();
        final SemaphoreTest test2 = new SemaphoreTest();
        for (int i = 1; i < 21; i++) {
            service.submit(test);
            service.submit(test2);
        }
        service.shutdown();
    }
}
