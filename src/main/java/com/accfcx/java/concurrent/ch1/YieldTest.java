package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc sleep and yield 区别 - 线程调度器是否立马进行线程调度
 */
public class YieldTest implements Runnable{
    Thread thread = null;
    public YieldTest(String name) {
        thread = new Thread(this, name);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                System.out.println(Thread.currentThread() + "yield cpu");
//                Thread.yield();
            } else {
                System.out.println(Thread.currentThread() + "执行 " + i);
            }
        }
        System.out.println(Thread.currentThread() + " is over");
    }

    public static void main(String[] args) {
        new YieldTest("thread-1");
        new YieldTest("thread-2");
        new YieldTest("thread-3");
    }
}
