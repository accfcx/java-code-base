package com.accfcx.java.concurrent2.future;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author accfcx
 * @desc
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> task = new FutureTask<String>(new Call());
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(task);

        Thread.sleep(4000);

        System.out.println("start");
        System.out.println(task.get());
        System.out.println("end");

        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
    }

    static class Call implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(1000 * 5);
            return "hello";
        }
    }
}
