package com.accfcx.java.concurrent2;

/**
 * @author accfcx
 * @desc
 */
public class DeadLockTest {
    private static Object o1 = new Object();
    private static Object o2 = new Object();

    static class A implements Runnable {
        @Override
        public void run() {
            synchronized (o1) {
                System.out.println("A get o1");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("A get o2");
                }
            }
        }
    }

    static class B implements Runnable {
        @Override
        public void run() {
            synchronized (o2) {
                System.out.println("B get o2");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(" B get o1");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new A());
        Thread t2 = new Thread(new B());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("main over");
    }
}
