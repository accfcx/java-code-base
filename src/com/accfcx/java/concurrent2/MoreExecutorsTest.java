package com.accfcx.java.concurrent2;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author accfcx
 * @desc Guava
 */
public class MoreExecutorsTest {
    public static void main(String[] args) {
//        Executor executor = MoreExecutors.directExecutor();
//        executor.execute(()->{
//            System.out.println(Thread.currentThread().getName());
//        });

        Executor executor = MoreExecutors.getExitingExecutorService((ThreadPoolExecutor) Executors.newFixedThreadPool(2));
        executor.execute(()->{
            System.out.println(Thread.currentThread().getName());
        });
        MoreExecutorsTest.test();
    }

    public static void test() {
//        Map<Integer, Integer> map = new ConcurrentSkipListMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            map.put(i, i);
        }
        map.forEach((k, v)->{
            System.out.println(k +  " " + v);
        });
    }
}
