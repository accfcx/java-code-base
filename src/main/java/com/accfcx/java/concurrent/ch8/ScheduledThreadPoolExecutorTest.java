package com.accfcx.java.concurrent.ch8;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author accfcx
 * @desc
 *
 * DelayedWorkQueue: 延迟队列
 * ScheduledFutureTask-FutureTask, state[正常执行，执行异常，取消任务，任务中断]， period[0-一次性任务，<0 固定延迟，>0固定频率]
 *
 * schedule(task, delay, unit) - 一次性的延迟任务
 * scheduleWithFixedDelay(task, initDelay, delay, unit) 任务执行完成后，延迟delay再次执行
 * scheduleAtFixedRate(task, initDelay, period, unit) 任务initDelay + n * delay;不会并发执行，后一个任务等到当前的执行完成再执行
 */
public class ScheduledThreadPoolExecutorTest {
    static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);

    public static void main(String[] args) {
        System.out.println(new Date() + " main start");
        ScheduledFuture<String> result = scheduledThreadPoolExecutor.schedule(()->{
            System.out.println(new Date() + " task is start");
            return "hello";
        }, 10, TimeUnit.SECONDS);

        try{
            System.out.println( " task is over. " + result.get() +  new Date());
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(new Date() + " main is over");

        scheduledThreadPoolExecutor.shutdownNow();
    }
}
