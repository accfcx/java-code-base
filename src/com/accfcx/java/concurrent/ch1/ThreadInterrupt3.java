package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc
 * thread.interrupted() 和 Thread.interrupted()方法获取的是当前线程的中断标志
 * 所以当前线程是哪个？ - 并非是方法接受者 而是所处的主线程！！！
 */
public class ThreadInterrupt3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(ThreadInterrupt3::run);
        thread.start();

        Thread.sleep(3000);
        thread.interrupt();

        thread.join();
        System.out.println("main over");
    }

    private static void run() {
        try {
            System.out.println("start sleep");
            Thread.sleep(2000);
            System.out.println("sleep over");
        } catch (Exception e) {
            System.out.println("sleep interruption ");
            return;
        }
        System.out.println("sleep finish and doing other code");
    }
}
