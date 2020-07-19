package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc
 * currentThread().isInterrupted() ->仅返回中断标志
 * Thread.interrupted()->返回中断标志，并重置为false
 */
public class ThreadInterrupted4 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
//            while(!Thread.currentThread().isInterrupted()) {
            while(!Thread.interrupted()) {
                System.out.println("未发生中断:" + Thread.currentThread().isInterrupted());
            }
            System.out.println("发生了中断，现在的中断标志:" + Thread.currentThread().isInterrupted());
        });
        thread.start();
        Thread.sleep(9);

        thread.interrupt();

        thread.join();

        System.out.println("main over");
    }
}
