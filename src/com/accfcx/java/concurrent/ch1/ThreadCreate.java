package com.accfcx.java.concurrent.ch1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author accfcx
 * @desc
 * 1 继承 执行逻辑写死在一个线程内部，没有把执行和任务分离
 * 2 Runnable 任务和线程分离
 * 1，2都没有返回值
 */
public class ThreadCreate {
    public static void main(String[] args) {
//        MyThread thread = new MyThread("gg");
//        System.out.println("main " + Thread.currentThread());
//        thread.start();

//        Runnable runnable = new MyRunnable();
//        Thread thread1 = new Thread(runnable, "Thread-1");
//        Thread thread2 = new Thread(runnable, "Thread-2");
//        thread1.start();
//        thread2.start();

        FutureTask futureTask = new FutureTask(new MyCall());
        new Thread(futureTask).start();
        try{
            Object result = futureTask.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 耗时任务，有返回值
     * 异步任务
     */
    public static class MyCall implements Callable {
        @Override
        public Object call() throws Exception {
            System.out.println(Thread.currentThread() + " execute callable");
            return "hello";
        }
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "task is running");
        }
    }

    public static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
//            super.run();
            System.out.println("my thread:" + Thread.currentThread());
        }
    }
}
