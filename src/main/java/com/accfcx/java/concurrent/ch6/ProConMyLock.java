package com.accfcx.java.concurrent.ch6;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

/**
 * @author accfcx
 * @desc
 * 自定义锁实现生产消费者模型
 *
 * 锁
 * 2个条件变量
 *
 *
 * while循环的原因: 虚假唤醒
 */
public class ProConMyLock {
    private static MyAQSLock lock = new MyAQSLock();

    private static Condition notFull = lock.newCondition();
    private static Condition notEmpty = lock.newCondition();

    private static Queue<String> queue = new LinkedBlockingQueue<>();

    private static int size = 10;

    public static void main(String[] args) throws InterruptedException {
        Thread producer  = new Thread(()->{
            lock.lock();
            try{
                while(queue.size() == size) {
                    // 生产者获取锁后，再根据条件判断是否需要阻塞到条件队列中
                    notFull.await();
                }
                Date now = new Date();
                System.out.println("生产了：" + now);
                queue.add("" + now);

                // 放了一个元素，通知阻塞在为空的条件队列中的线程
                notEmpty.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        producer.start();

        Thread consumer = new Thread(()->{
                lock.lock();

                try{
                    while(queue.size() == 0) {
                        notEmpty.await();
                    }

                    System.out.println("消费了：" + queue.remove());

                    notFull.signalAll();
                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
        });
        consumer.start();

//        producer.join();
//        consumer.join();

    }
}
