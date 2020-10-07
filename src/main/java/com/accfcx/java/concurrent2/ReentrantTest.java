package com.accfcx.java.concurrent2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author accfcx
 * @desc
 */
public class ReentrantTest implements Runnable{
    private static int num = 0;
    private static ReentrantLock lock = new ReentrantLock();

    private String name;

    public ReentrantTest(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        System.out.println(this + " Thread: " + Thread.currentThread().getName());
        for (int i = 0; i < 10000; i++) {
            lock.lock();
            try {
                num++;
            }finally {
                lock.unlock();
            }
        }
    }

    @Override
    public String toString() {
        return "ReentrantTest{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantTest runnable = new ReentrantTest("t1");
        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(new ReentrantTest("t2"));
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(num);
    }
}
