package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc 创建子线程时，根据父线程的InheritableThreadLocalsMap数据，复制到子线程的map中
 */
public class InheritableTheadLocalTest {
    private static InheritableThreadLocal threadLocal = new InheritableThreadLocal();
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
