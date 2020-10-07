package com.accfcx.java.concurrent2;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author accfcx
 * @desc 线程池执行任务异常堆栈
 */
public class ThreadPoolExceptionTest {
    static class Div implements Runnable {
        int a, b;

        public Div(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
//            try{
//                System.out.println(a/b);
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
            System.out.println(a/b);
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());
        for (int i = 0 ; i < 5;i ++){
            poolExecutor.execute(new Div(100, i));
        }
    }
}
