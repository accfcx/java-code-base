package com.accfcx.java.concurrent2;

import java.util.concurrent.*;

/**
 * @author accfcx
 * @desc
 */
public class ThreadPoolTest {

    static class MyTask implements Runnable {
        private String taskName;

        public MyTask(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId() + " is executing task:" + this.taskName);
            try{
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5,5, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(((MyTask)r).getTaskName() + " is over");
            }



            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(((MyTask)r).getTaskName() + " is start");
            }
        };
        for (int i = 0; i < 5; i++) {
            MyTask task = new MyTask("Task" + i);
            executorService.execute(task);
            Thread.sleep(1000);
        }
        executorService.shutdown();
    }
}
