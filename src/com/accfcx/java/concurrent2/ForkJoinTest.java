package com.accfcx.java.concurrent2;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author accfcx
 * @desc todo java.lang.NoClassDefFoundError: Could not initialize class java.util.concurrent.locks.AbstractQueuedSynchronizer$Node
 * 异常什么原因： 划分层次过多导致的线程过多或者调用层次过多，栈溢出
 */
public class ForkJoinTest {
    static class CountTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 50;
        long start;
        long end;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            boolean forked = (end - start) < THRESHOLD;
            if (forked) {
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                long step = (start + end) / 100;
                ArrayList<CountTask> list = new ArrayList<>();
                long pos = start;
                for (int i = 0; i < 100;i++) {
                    long lastPos = pos + step;
                    if (lastPos > end) {
                        lastPos = end;
                    }
                    CountTask subTask = new CountTask(pos, lastPos);
                    pos += step + 1;
                    list.add(subTask);
                    subTask.fork();
                }
                for (CountTask task: list) {
                    sum += task.join();
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0, 1000000);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);

        try{
            System.out.println(System.currentTimeMillis());
            System.out.println(" sum = " + result.get());
            System.out.println(System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}