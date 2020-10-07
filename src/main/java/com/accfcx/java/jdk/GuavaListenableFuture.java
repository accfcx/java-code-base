package com.accfcx.java.jdk;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author accfcx
 * @desc
 * Runnable
 * Callable
 * FutureTask
 *
 * Guava: ListenableFuture(Callable) -> FutureCallback
 *
 * Netty: Future -> GenericFutureListener 基于监听器的异步非阻塞回调
 */
public class GuavaListenableFuture {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        ListeningExecutorService pool2 = MoreExecutors.listeningDecorator(pool);

        ListenableFuture<Boolean> future =  pool2.submit(new MyCallable());

        Futures.addCallback(future,
                new FutureCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        System.out.println("成功结果：" + aBoolean);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("遇到异常:" + throwable);
                    }
                },
                pool2);
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try{
                Thread.sleep(5 * 1000);
                System.out.println("runnable go");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class MyCallable implements Callable {
        @Override
        public Boolean call() {
            try{
                Thread.sleep(5 * 1000);
                System.out.println("Callable go");
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
