package com.accfcx.java.concurrent.ch7;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author accfcx
 * @desc
 * 无界阻塞延迟队列
 *
 * available - 条件变量-调用take的阻塞
 * leader - 记录获取到最先要过期task的线程，其它线程再次获取take,peek返回的第一个task时，无线阻塞
 * 只有getDelayTime <= 0时，才会poll
 *
 * todo - 并没有和示例的结果一致？？？
 */
public class DelayQueueTest {

    public static void main(String[] args) {
        DelayQueue<DelayTask> queue = new DelayQueue<>();

        Random random = new Random();

        for (int i = 0; i < 10; i++){
            DelayTask task = new DelayTask((long) random.nextInt(500), " task: " + i);
            queue.offer(task);
        }

        DelayTask task = null;
        try{
            for (;;) {
                while((task = queue.take()) != null) {
                    System.out.println(task);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class DelayTask implements Delayed {
        /**
         * 延迟时间
         */
        private Long delayTime;
        /**
         * 到期时间
         */
        private Long expire;
        private String taskName;

        public DelayTask(Long delayTime, String taskName) {
            this.delayTime = delayTime;
            this.taskName = taskName;
            expire = System.currentTimeMillis() + delayTime;
        }

        // 剩余时间 = 到期时间-当前时间
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MICROSECONDS);
        }

        /**
         * 按照剩余的过期时间比较
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "DelayTask{" +
                    "delayTime=" + delayTime +
                    ", expire=" + expire +
                    ", taskName='" + taskName + '\'' +
                    '}';
        }
    }
}
