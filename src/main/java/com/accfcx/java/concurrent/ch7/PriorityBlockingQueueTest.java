package com.accfcx.java.concurrent.ch7;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.IntStream;

/**
 * @author accfcx
 * @desc
 *
 * 优先级-无界阻塞；平衡二叉树堆
 *
 * array,size, allocationSpinLock[自旋锁，CAS保证只有一个线程扩容]
 *
 * 比较器comparator
 *
 * 全局锁ReentrantLock，控制同时只有一个线程入队、出队
 *
 * notEmpty：take阻塞
 * 无notFull,因为put是非阻塞【原因是无界的，不会满】
 *
 * put, offer 非阻塞
 * take
 */
public class PriorityBlockingQueueTest {

    //验证优先级任务的执行用法

    public static void main(String[] args) {
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();
        Random random = new Random();
        IntStream.range(0, 10).forEach((i)->{
            Task task = new Task();
            task.setPriority(random.nextInt(10));
            task.setTaskName("task-" + i);
            queue.put(task);
        });

        while(!queue.isEmpty()) {
            Task task = queue.poll();
            task.run();
        }
    }


    static class Task implements Comparable<Task>, Runnable{
        private int priority = 0;
        private String taskName;


        @Override
        public int compareTo(Task o) {
            if (this.priority >= o.getPriority()) {
                return 1;
            } else {
                return -1;
            }
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println("Task: " + this.taskName + " is running. " + this.priority);
            try{
                Thread.sleep(500);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("task : " + this.taskName + " is over. "  + this.priority);
        }
    }
}
