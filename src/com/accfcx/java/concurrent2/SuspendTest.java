package com.accfcx.java.concurrent2;

/**
 * @author accfcx
 * @desc
 * resume/suspend 出现永远挂起问题
 */
public class SuspendTest {
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new TestThread("测试1");
        Thread t2 = new TestThread("测试2");
        t1.start();

        Thread.sleep(6000);

        t2.start();

        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
    }


    static class TestThread extends Thread {
        public TestThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + " in");
                Thread.currentThread().suspend();
            }
        }
    }
}
