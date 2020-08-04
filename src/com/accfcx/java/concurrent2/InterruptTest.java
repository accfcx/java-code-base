package com.accfcx.java.concurrent2;

/**
 * @author accfcx
 * @desc
 * Thread: X
 * X.interrupt() 实例方法，中断线程
 * boolean X.isInterrupted() 实例方法，判断是否中断
 * static Thread.interrupted() 静态方法，判断是否中断，并清除中断标志
 */
public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("响应中断");
                    break;
                }
                System.out.println("go");
                Thread.yield();
                System.out.println("now");
            }
        });
        thread.start();

        Thread.sleep(10);
        thread.interrupt();
    }
}
