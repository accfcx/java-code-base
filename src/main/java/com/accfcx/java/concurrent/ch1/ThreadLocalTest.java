package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc 线程本地变量
 * Thread-ThreadLocalMap<ThreadLocal, T(Value)>
 *     每个ThreadLocal变量 T，在每个线程中都会产生一个 以同一个T为key的entity
 */
public class ThreadLocalTest {
    static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            local.set("thread 1 local variable");
            print("Thread 1");
            System.out.println(local.get());
        });


        Thread thread2 = new Thread(()->{
            local.set("thread 2 local variable");
            print("Thread 2");
            System.out.println(local.get());
        });

        thread1.start();

        thread2.start();
        System.out.println("main thread: " + local.get());
    }

    static void print(String str) {
        System.out.println(local);
        System.out.println(str + " :" + local.get());
//        local.remove();
    }

}
