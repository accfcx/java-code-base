package com.accfcx.java.jvm.ch12;

/**
 * @author accfcx
 * @desc 适合开关型的线程共享并发问题
 * 属于无状态
 */
public class VolatileTest2 {
    private volatile Boolean canRun = true;

    public void shutdown() {
        canRun = false;
    }

    public void doWork() {
        while(canRun) {

        }
    }
}
