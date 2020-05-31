package com.accfcx.algo.distributed;

/**
 * @author accfcx
 */
public class ClockTest {
    public static void main(String[] args) throws Exception{
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
        Thread.sleep(1000);
        System.out.println(System.nanoTime());
    }
}
