package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc isInterrupted()方法返回线程中断标志
 */
public class ThreadInterrupt2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("线程没有被中断");
            }
            System.out.println(Thread.currentThread() + "线程中断标志为true");
        });
        thread.start();

        Thread.sleep(1);
        try{
            thread.interrupt();
        }catch (Exception e) {
            e.printStackTrace();
        }

        thread.join();
        System.out.println("main over");
    }
}
