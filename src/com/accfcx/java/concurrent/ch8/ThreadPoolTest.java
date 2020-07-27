package com.accfcx.java.concurrent.ch8;

/**
 * @author accfcx
 * @desc
 * Executors: 线程池工具类
 *
 * newFixedThreadPool - LinkedBlockingQueue<Runnable>
 * newSingleThreadExecutor - LinkedBlockingQueue<Runnable>
 * newCachedThreadPool - SynchronousQueue - 同步队列中的任务会立马执行；最多只有一个任务
 *
 * RejectedExecutionHandler: 队列满，线程池达到maximunPoolSize
 *  AbortPolicy - 抛出异常
 *  CallerRunsPolicy - 调用者线程执行
 *  DiscardOldestPolicy - poll丢弃一个任务
 *  DiscardPolicy - 丢弃，不抛出异常
 *
 *  todo - ThreadPoolExecutor 原理 - 状态控制；execute()方法  addWorker()方法
 *
 *  基本原理 - 是否达到核心线程；任务队列是否满；线程池是否满；最后饱和策略
 */
public class ThreadPoolTest {
}
