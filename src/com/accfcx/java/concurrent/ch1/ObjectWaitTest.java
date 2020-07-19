package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc
 */
public class ObjectWaitTest {
    private static volatile Object S = new Object();
    private static volatile Object T = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(()->{
            synchronized (S) {
                // 线程A获取A的监视器锁
                System.out.println("Thread A get monitor Lock of S");
                synchronized (T) {
                    System.out.println("Thread A get monitor Lock of T");

                    try {
                        // 线程A释放S的监视器锁，并且线程A阻塞
                        S.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Thread A occur exception");
                        e.printStackTrace();
                    }
                }
            }
        }, "ThreadA");

        Thread threadB = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (S) {
                // 线程B获取A的监视器锁
                System.out.println("ThreadB get monitor Lock of S");

                System.out.println("ThreadB try to get Lock of T");
                System.out.println("ThreadB interrupt threadA");
                threadA.interrupt();

                synchronized (T) {
                    System.out.println("ThreadB get monitor Lock of T");
                    try {
                        // 线程B释放S的监视器锁，并且线程B阻塞
                        S.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "ThreadB");

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("main over");
    }
}
