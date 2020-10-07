package com.accfcx.java.concurrent.ch1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author accfcx
 * @desc
 * wait必须先获取到object的监视器锁-1 synchronized(object) 2 调用object的 synchronized方法
 *
 * 唤醒 - notify() notifyAll() interrupt()
 * 防止虚假唤醒 - while(唤醒条件不满足) {
 *     object.wait()
 * }
 */
public class ObjectWaitAndNotify {
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {

        FutureTask futureTask = new FutureTask(new MyCallable());
        new Thread(futureTask, "test thread").start();
        try{
            System.out.println(futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread() + " execute call()");
            synchronized (object) {
                object.wait();
            }
            System.out.println(Thread.currentThread() + " will exit");
            return "go";
        }
    }
}
