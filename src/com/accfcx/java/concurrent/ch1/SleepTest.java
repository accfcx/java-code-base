package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc 线程sleep期间，如果获取了g共享变量的监视器锁，不会释放
 */
public class SleepTest {
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(()->{
            synchronized (object) {
                System.out.println("A get lock of A");
                try{
                    Thread.sleep(10000);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        });

        Thread B = new Thread(()->{
            synchronized (object) {
                System.out.println(A.getState());
                System.out.println("B get lock of A");
            }

        });

        A.start();
        B.start();
        A.interrupt();

        A.join();
        B.join();
//        System.out.println("main over");

    }
}
