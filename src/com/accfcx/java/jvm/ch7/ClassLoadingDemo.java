package com.accfcx.java.jvm.ch7;

import java.util.Arrays;

/**
 * @author accfcx
 * @desc
 * 对类型初始化，主动调用的6种情况
 * 1: init phase: new/static field/method
 *
 * 被动调用不会进行初始化阶段
 * 1 static final修饰的常量
 * 2 数组类型
 */
public class ClassLoadingDemo {
    public static void main(String[] args) {
//        System.out.println(Sub1.x);
//        Super1[] array = new Super1[10];
//        Arrays.stream(array).forEach(item->{
//            System.out.println(item);
//        });
//        System.out.println(Sub1.y);
//        new ClassInit();
//        int r = C1.B;
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread() + " start");
            new DeadLoopClaInit();
            System.out.println(Thread.currentThread() + " end");
        };
        Thread thread1 = new Thread(runnable, "FFF");
        Thread thread2 = new Thread(runnable, "GGG");
        thread1.start();
        thread2.start();

    }
}
