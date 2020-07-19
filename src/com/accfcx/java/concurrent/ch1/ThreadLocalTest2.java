package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc ThreadLocal 父子线程数据不能继承
 */
public class ThreadLocalTest2 {
    private static ThreadLocal threadLocal = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("hello world");

        Thread thread = new Thread(()->{
            System.out.println(Thread.currentThread() + "" + threadLocal.get());
        });
        thread.start();
thread.join();
        System.out.println(Thread.currentThread() + "" + threadLocal.get());
    }
}
