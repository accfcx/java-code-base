package com.accfcx.java.concurrent.ch3;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author accfcx
 * @desc 随机数
 * 1 seed: seed = f(seed) 根据旧值计算出新值
 * 2 randValue = g(seed)
 * 多线程避免使用了同样的旧种子，产生了同样的随机数 - JDK7解决方案：计算新种子值时，使用CAS更新种子值
 *
 * ThreadLocalRandom
 *
 * User Thread->
 *
 * ThreadLocalRandom ->
 *  current->返回static的ThreadLocalRandom实例
 *  nextInt->Unsafe直接更新Thread.threadLocalRandomSeed种子变量，
 * Thread[
 *      threadLocalRandomSeed: 线程seed,
 *      threadLocalRandomProbe,
 *      threadLocalRandomSecondarySeed]
 *
 * Random CAS同步种子原子变量多线程下性能问题
 * 改进：借用ThreadLocal原理，种子放在Thread的field中，通过ThreadLocalRandom直接操作线程内的变量
 */
public class ThreadLocalRandomTest {

    public static void main(String[] args) {
        Random random = new Random();
        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread() + " " + random.nextInt());
            }
        });

        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread() + " " + random.nextInt());
            }
        });

        thread1.start();
        thread2.start();
    }

    public static void get() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
    }
}
