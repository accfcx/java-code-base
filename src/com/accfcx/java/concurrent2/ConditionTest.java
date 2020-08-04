package com.accfcx.java.concurrent2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author accfcx
 * @desc
 */
public class ConditionTest implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    @Override
    public void run() {
        try{
            lock.lock();
            condition.await();
            System.out.println("and go");
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionTest test = new ConditionTest();
        Thread t1 = new Thread(test);
        t1.start();
        Thread.sleep(1000);
        lock.lock();
        condition.signalAll();
//        lock.unlock();
        System.out.println("signal ");


    }
}
