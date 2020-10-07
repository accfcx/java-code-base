package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc 环路等待 - 有序申请共享资源
 */
public class DeadLockTest {
    private static Object A = new Object();
    private static Object B = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            synchronized (A) {
                System.out.println("1 get A");
                System.out.println("1 try get B");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("1 get B");
                }
            }
        });
        Thread thread2 = new Thread(()->{
            synchronized (A) {
                System.out.println("2 get A");
                System.out.println("2 try get B");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("2 get B");
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("main over");
    }
}
