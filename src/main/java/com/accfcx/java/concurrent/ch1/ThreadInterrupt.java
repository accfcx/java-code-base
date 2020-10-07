package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc
 */
public class ThreadInterrupt {
    private static Object object = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            synchronized (object) {
                try {
                    System.out.println("thread block");
                    object.wait();
                } catch (InterruptedException e) {
                    System.out.println("thread is blocking and interrupt");
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Thread.sleep(1000);

        thread.interrupt();

    }
}
