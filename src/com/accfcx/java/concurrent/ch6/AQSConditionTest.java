package com.accfcx.java.concurrent.ch6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author accfcx
 * @desc
 * synchronized(X) 内置锁 ；共享变量X
 *
 * 在调用共享变量X notify wait前，必须获取到锁
 *
 * 在条件变量Condition signal await前，必须获取到锁
 *
 * 锁的实现是基于AQS- 一个锁可以有多个ConditionObject
 *
 *
 *
 *
 *
 * 理论上 ConditionObject.await() 类似synchronized共享变量的wait 会阻塞线程，并且释放锁
 */
public class AQSConditionTest {
    static Object object = new Object();


    public static void main(String[] args) {
         ReentrantLock lock = new ReentrantLock();
         Condition conditionObject1 = lock.newCondition();
        lock.lock();
        try{
            System.out.println("begin await");
            conditionObject1.await(2, TimeUnit.SECONDS);
            System.out.println("end await");
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("释放锁");
        }
//
//        new Thread(()->{
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
////            lock.lock();
//
//            try{
//                System.out.println("begin signal");
//                conditionObject1.signal();
//                System.out.println("end ");
//            } finally {
////                lock.unlock();
//            }
//        }).start();

        synchronized (object) {
            try{
                System.out.println("begin wait");
                object.wait();
                System.out.println("end wait");
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
